package pelican.co_labor.controller;

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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    // 생성자 주입
    private final AuthService authService;

    private final EnterpriseUserRepository enterpriseUserRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username,
                                                     @RequestParam("password") String password,
                                                     HttpServletRequest httpServletRequest) {
        try {
            Cookie[] cookies = httpServletRequest.getCookies();
            boolean authenticated = authService.authenticateUser(username, password);

            Map<String, Object> response = new HashMap<>();
            if (authenticated) {
                // 로그인 성공 -> 세션 생성

                // 세션을 생성하기 전에 기존의 세션 파기
                httpServletRequest.getSession().invalidate();
                HttpSession session = httpServletRequest.getSession(true);  // 세션 없으면 생성

                // 세션에 username, 일반 or 기업 회원 넣어줌
                session.setAttribute("username", username);
                if (authService.getUserType(username).equals("labor")) {
                    session.setAttribute("userType", "labor");
                } else if (authService.getUserType(username).equals("enterprise")) {
                    session.setAttribute("userType", "enterprise");
                }

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

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);  // 세션이 없으면 null 리턴

        try {
            if (session != null) {
                session.invalidate();
            }

            return ResponseEntity.ok(Map.of("message", "Logout successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to logout: " + e.getMessage()));
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpServletRequest httpServletRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            HttpSession session = httpServletRequest.getSession(false); // 세션이 없으면 null 리턴

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

    @GetMapping("/hasEnterprise")
    public ResponseEntity<Boolean> hasEnterprise(@RequestParam("username") String userName) {
        EnterpriseUser user = enterpriseUserRepository.findByEnterpriseUserId(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(user.getEnterprise() != null);
    }
}