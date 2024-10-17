package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pelican.co_labor.controller.ChattingController;
import pelican.co_labor.domain.chatting.Chatting;
import pelican.co_labor.domain.labor_user.LaborUser;
import pelican.co_labor.repository.chatting.ChattingRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChattingService {

    private static final Logger logger = LoggerFactory.getLogger(ChattingService.class);

    private final ChattingRepository chattingRepository;
    private final OpenAIChatService openAIChatService;
    private final RestTemplate restTemplate;

    @Autowired
    public ChattingService(ChattingRepository chattingRepository, OpenAIChatService openAIChatService, RestTemplate restTemplate) {
        this.chattingRepository = chattingRepository;
        this.openAIChatService = openAIChatService;
        this.restTemplate = restTemplate;
    }

    // 새로운 메서드: 사용자의 모든 메시지 삭제
    @Transactional
    public void deleteAllMessagesByUser(String laborUserId) {
        List<Chatting> chats = chattingRepository.findByLaborUser_LaborUserId(laborUserId);
        chattingRepository.deleteAll(chats);  // 사용자와 관련된 모든 채팅을 삭제
    }

    @Transactional
    public Chatting saveUserMessage(LaborUser laborUser, String userMessage) {
        Chatting chatting = new Chatting();
        chatting.setLaborUser(laborUser);
        chatting.setContent(userMessage);
        chatting.setMy_message(true);
        return chattingRepository.save(chatting);
    }

    @Transactional
    public Chatting saveGptResponse(LaborUser laborUser, String gptResponse) {
        Chatting chatting = new Chatting();
        chatting.setLaborUser(laborUser);
        chatting.setContent(gptResponse);
        chatting.setMy_message(false);
        return chattingRepository.save(chatting);
    }

    // 이전 대화 기록을 아예 보내지 않고 현재 질문만 GPT로 보내도록 수정
    public String getGptResponse(String userId, String userMessage) {
        try {
            // 이전 대화 기록을 제거하고, 현재 질문만 GPT로 전달
            String summaryPrompt = "Condition 0 - The English in the prompt is the setting to be referenced in the answer, and the Korean is the actual question. \nCondition 1 - Your role is a legal chatbot consulting with a foreign worker. \nCondition 2 - Now the foreign worker will ask you a question about legal advice, and you must answer the question according to conditions 3 and 4. \nCondition 3 - First paragraph: The main answer to the question. This includes various contents such as solutions, advice, etc. Second paragraph: The law, case law, etc. related to the question. \nCondition 4 - Answer in three paragraphs using indentation and do not put subtitles before the paragraphs.\n"
                    + "Legal Advice Questions: " + userMessage + "\nPlease answer legal advice questions in Korean according to conditions 1, 2, 3, 4, and 5.";

            return openAIChatService.getGptResponse(summaryPrompt).replace("\n", "<br>");
        } catch (Exception e) {
            logger.error("Error while fetching GPT response: ", e);
            throw new RuntimeException("Failed to get GPT response", e);
        }
    }

    // 241017 최근 메시지를 가져오는 메서드 추가 (사용되지 않음 - 예시로 남겨둠)
    public List<Chatting> getRecentMessagesByUser(String laborUserId, int messageCount) {
        List<Chatting> allMessages = chattingRepository.findByLaborUser_LaborUserId(laborUserId);
        return allMessages.stream()
                .skip(Math.max(0, allMessages.size() - messageCount))
                .collect(Collectors.toList());
    }

    private String removeOverlap(String previousResponses, String userMessage) {
        // 이전 응답에서 사용자 메시지를 제거
        if (previousResponses.contains(userMessage)) {
            // 겹치는 부분이 있으면 제거
            return previousResponses.replace(userMessage, "").trim();
        }
        // 겹치는 부분이 없으면 이전 응답 반환
        return previousResponses;
    }
}
