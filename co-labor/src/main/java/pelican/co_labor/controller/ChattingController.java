package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.chatting.Chatting;
import pelican.co_labor.domain.labor_user.LaborUser;
import pelican.co_labor.service.AuthService;
import pelican.co_labor.service.ChattingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chatting")
public class ChattingController {

    private final ChattingService chattingService;
    private final AuthService authService;

    @Autowired
    public ChattingController(ChattingService chattingService, AuthService authService) {
        this.chattingService = chattingService;
        this.authService = authService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestParam String userId, @RequestParam String message) {
        Optional<LaborUser> optionalUser = authService.findLaborUserById(userId);
        if (optionalUser.isPresent()) {
            LaborUser user = optionalUser.get();
            chattingService.saveUserMessage(user, message);
            String gptResponse = chattingService.getGptResponse(message);
            chattingService.saveGptResponse(user, gptResponse);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @GetMapping("/all")
    public List<Chatting> getAllMessages(@RequestParam String userId) {
        Optional<LaborUser> optionalUser = authService.findLaborUserById(userId);
        if (optionalUser.isPresent()) {
            LaborUser user = optionalUser.get();
            return chattingService.getAllMessagesByUser(user.getLaborUserId());
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
