package pelican.co_labor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.chatting.Chatting;
import pelican.co_labor.domain.labor_user.LaborUser;
import pelican.co_labor.service.AuthService;
import pelican.co_labor.service.ChattingService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Chatting", description = "법률 상담 챗봇과의 채팅 관련 API")
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

    @Operation(summary = "메시지 전송 API", description = "사용자가 챗봇과 메시지를 주고받습니다.")
    @PostMapping("/send")
    public void sendMessage(
            @Parameter(description = "사용자 ID") @RequestParam("userId") String userId,
            @Parameter(description = "사용자가 전송한 메시지") @RequestParam("message") String message) {
        Optional<LaborUser> optionalUser = authService.findLaborUserById(userId);
        if (optionalUser.isPresent()) {
            LaborUser user = optionalUser.get();
            chattingService.saveUserMessage(user, message);

            String gptResponse = chattingService.getGptResponse(user.getLaborUserId(), message);

            chattingService.saveGptResponse(user, gptResponse);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Operation(summary = "전체 메시지 조회 API", description = "특정 사용자가 주고받은 모든 메시지를 조회합니다.")
    @GetMapping("/all")
    public List<Chatting> getAllMessages(
            @Parameter(description = "사용자 ID") @RequestParam("userId") String userId) {
        Optional<LaborUser> optionalUser = authService.findLaborUserById(userId);
        if (optionalUser.isPresent()) {
            LaborUser user = optionalUser.get();
            return chattingService.getAllMessagesByUser(user.getLaborUserId());
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
