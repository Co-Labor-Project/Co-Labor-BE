package pelican.co_labor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;
import pelican.co_labor.dto.auth.EnterpriseUserDTO;
import pelican.co_labor.dto.auth.LaborUserDTO;
import pelican.co_labor.repository.enterprise_user.EnterpriseUserRepository;
import pelican.co_labor.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Authentication", description = "사용자 인증 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final EnterpriseUserRepository enterpriseUserRepository;

    @Operation(summary = "로그인 API", description = "사용자가 제공한 자격 증명을 통해 로그인합니다.")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @Parameter(description = "사용자 이름") @RequestParam("username") String username,
            @Parameter(description = "사용자 비밀번호") @RequestParam("password") String password,
            HttpServletRequest httpServletRequest) {
        try {
            Cookie[] cookies = httpServletRequest.getCookies();
            boolean authenticated = authService.authenticateUser(username, password);

            Map<String, Object> response = new HashMap<>();
            if (authenticated) {
                httpServletRequest.getSession().invalidate();
                HttpSession session = httpServletRequest.getSession(true);  // 세션 없으면 생성

                session.setAttribute("username", username);
                session.setAttribute("userType", authService.getUserType(username));
                session.setMaxInactiveInterval(1800); // 세션 만료 시간 30분

                response.put("message", "Login successful");
                response.put("redirect", "/index");
                response.put("userType", session.getAttribute("userType"));
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Invalid username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Failed to login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "일반 사용자 회원가입 API", description = "일반 사용자가 회원가입을 진행합니다.")
    @PostMapping("/signup-labor")
    public ResponseEntity<Map<String, Object>> signupLabor(@RequestBody LaborUserDTO laborUserDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            authService.registerLaborUser(laborUserDTO);
            response.put("message", "Labor user registered successfully");
            response.put("user", laborUserDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Failed to register labor user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "기업 사용자 회원가입 API", description = "기업 사용자가 회원가입을 진행합니다.")
    @PostMapping("/signup-enterprise")
    public ResponseEntity<Map<String, Object>> signupEnterprise(@RequestBody EnterpriseUserDTO enterpriseUserDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            authService.registerEnterpriseUser(enterpriseUserDTO);
            response.put("message", "Enterprise user registered successfully");
            response.put("user", enterpriseUserDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Failed to register enterprise user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "로그아웃 API", description = "사용자의 세션을 종료합니다.")
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        try {
            if (session != null) {
                session.invalidate();
            }
            return ResponseEntity.ok(Map.of("message", "Logout successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to logout: " + e.getMessage()));
        }
    }

    @Operation(summary = "현재 로그인한 사용자 정보 조회 API", description = "현재 세션에 로그인된 사용자 정보를 조회합니다.")
    @GetMapping("/current-user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpServletRequest httpServletRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpSession session = httpServletRequest.getSession(false);
            if (session != null && session.getAttribute("username") != null) {
                response.put("message", "Current user found");
                response.put("username", session.getAttribute("username"));
                response.put("userType", session.getAttribute("userType"));
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "No user logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Failed to retrieve current user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "기업 정보 여부 확인 API", description = "주어진 사용자 이름으로 기업 정보가 있는지 확인합니다.")
    @GetMapping("/hasEnterprise")
    public ResponseEntity<Boolean> hasEnterprise(
            @Parameter(description = "사용자 이름") @RequestParam("username") String userName) {
        EnterpriseUser user = enterpriseUserRepository.findByEnterpriseUserId(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(user.getEnterprise() != null);
    }
}
