package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pelican.co_labor.domain.chatting.Chatting;
import pelican.co_labor.domain.labor_user.LaborUser;
import pelican.co_labor.repository.chatting.ChattingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final OpenAIChatService openAIChatService;

    @Autowired
    public ChattingService(ChattingRepository chattingRepository, OpenAIChatService openAIChatService) {
        this.chattingRepository = chattingRepository;
        this.openAIChatService = openAIChatService;
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

        String prompt = previousResponses + "조건 1번. 너의 역할은 외국인 근로자와 상담하는 법률 챗봇이야.\n" +
                "조건 2번. 지금부터 외국인 근로자가 너에게 법률 상담 질문을 하고, 넌 그 질문에 대한 대답을 조건 3번에 맞춰서 답변해줘.\n" +
                "조건 3번. 첫 번째 문단: 질문을 요약.  두 번째 문단: 질문에 대한 답변. 세 번째 문단: 나의 상황에 맞춘 조언 및 참고 판례.\n" +
                "법률 상담 질문: " + userMessage + "\n 위의 법률 상담 질문에 대한 답변을 조건 1, 2, 3번에 맞게 공감적으고 상담사처럼 친절하게 한국어로 작성해.";
        String firstResponse = openAIChatService.getGptResponse(prompt);

        System.out.println(prompt);

        String summaryPrompt = firstResponse + " 이 답변을 간략하게 요약해줘.";
        String summaryResponse = openAIChatService.getGptResponse(summaryPrompt);

        return summaryResponse;
    }
}

