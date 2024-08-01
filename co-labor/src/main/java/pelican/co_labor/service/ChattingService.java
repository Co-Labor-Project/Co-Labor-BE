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

        String prompt = previousResponses + " " + userMessage + "방금 질문에 대해서 'html'문법을 사용해서 (예를 들어 </b> <br />, <i> </i> ) 가독성 좋게 보기좋게 최대한 문단으로 자주 끊어서 답변을 해줘.  너는 법률 챗봇이야! 또 이 질문에 대해 공감적으로 상담사처럼 답변을 해주고 한국 헌법과 관련해서 법률 명과 관련된 판례와 키워드를 자세하게 구체적으로 알려줘. 그리고 주요 키워드를 이용하고 내가 외국인 근로자인 것을 감안해서 법률적 조언을 부탁해. 그리고 자세한 방법도 알려줘.";
        String firstResponse = openAIChatService.getGptResponse(prompt);

        String summaryPrompt = firstResponse + " 이 답변을 간략하게 요약해줘.";
        String summaryResponse = openAIChatService.getGptResponse(summaryPrompt);

        return summaryResponse;
    }
}

