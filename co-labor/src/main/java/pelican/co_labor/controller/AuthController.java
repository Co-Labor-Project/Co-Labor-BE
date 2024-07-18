package pelican.co_labor.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.dto.auth.EnterpriseUserDTO;
import pelican.co_labor.dto.auth.LaborUserDTO;
import pelican.co_labor.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    // 생성자 주입
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username,
                                                     @RequestParam("password") String password) {
        try {
            boolean authenticated = authService.authenticateUser(username, password);

            Map<String, Object> response = new HashMap<>();
            if (authenticated) {
                response.put("message", "Login successful");
                response.put("redirect", "/index");
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
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        try {
            session.invalidate();
            return ResponseEntity.ok(Map.of("message", "Logout successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to logout: " + e.getMessage()));
        }
    }
}