package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pelican.co_labor.domain.chatting.Chatting;
import pelican.co_labor.domain.labor_user.LaborUser;
import pelican.co_labor.repository.chatting.ChattingRepository;

import java.util.List;

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

    public String getGptResponse(String userMessage) {
        return openAIChatService.getGptResponse(userMessage);
    }
}
