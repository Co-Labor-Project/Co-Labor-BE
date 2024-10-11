package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pelican.co_labor.domain.chatting.Chatting;
import pelican.co_labor.domain.labor_user.LaborUser;
import pelican.co_labor.repository.chatting.ChattingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final OpenAIChatService openAIChatService;
    private final RestTemplate restTemplate;


    @Autowired
    public ChattingService(ChattingRepository chattingRepository, OpenAIChatService openAIChatService, RestTemplate restTemplate) {
        this.chattingRepository = chattingRepository;
        this.openAIChatService = openAIChatService;
        this.restTemplate = restTemplate;
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

    public List<Chatting> getAllMessagesByUser(String laborUserId) {
        return chattingRepository.findByLaborUser_LaborUserId(laborUserId);
    }

    public String getGptResponse(String userId, String userMessage) {

        List<Chatting> userChats = getAllMessagesByUser(userId);
        String previousResponses = userChats.stream()
                .map(Chatting::getContent)
                .collect(Collectors.joining(" "));

        // previousResponses에서 userMessage와 겹치는 부분 제거
        String cleanedResponses = removeOverlap(previousResponses, userMessage);
        String prev = cleanedResponses + "\n\nThe above is a conversation I had with you before. If it's empty, ignore it, but if it is, remember it.\n\n";

        String summaryPrompt = prev + "\nCondition 0 - The English in the prompt is the setting to be referenced in the answer, and the Korean is the actual question. \nCondition 1 - Your role is a legal chatbot consulting with a foreign worker. \nCondition 2 - Now the foreign worker will ask you a question about legal advice, and you must answer the question according to conditions 3 and 4. \nCondition 3 - First paragraph: The main answer to the question. This includes various contents such as solutions, advice, etc. Second paragraph: The law, case law, etc. related to the question. \nCondition 4 - Answer in three paragraphs using indentation and do not put subtitles before the paragraphs.\n"
                + "Legal Advice Questions: " + userMessage + "\nPlease answer legal advice questions in Korean according to conditions 1, 2, 3, 4, and 5. \nIf the legal advice question is not relevant, please reply that you did not understand and that you need to ask the correct question.";

        String gptResponse = openAIChatService.getGptResponse(summaryPrompt);
        String output = gptResponse.replace("\n", "<br>");
        return output;
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
