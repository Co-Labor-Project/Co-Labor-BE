package pelican.co_labor.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.dto.auth.EnterpriseUserDTO;
import pelican.co_labor.dto.auth.LaborUserDTO;
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

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        boolean authenticated = authService.authenticateUser(username, password);

        Map<String, String> response = new HashMap<>();
        if (authenticated) {
            // 로그인 성공
            session.setAttribute("username", username);

            response.put("message", "Login successful");
            response.put("redirect", "/index");
        } else {
            // 로그인 실패
            response.put("message", "Invalid username or password");
        }
        return response;
    }

    @PostMapping("/signup-labor")
    public Map<String, Object> signupLabor(@RequestBody LaborUserDTO laborUserDTO) {
        authService.registerLaborUser(laborUserDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Labor user registered successfully");
        response.put("user", laborUserDTO);
        return response;
    }

    @PostMapping("/signup-enterprise")
    public Map<String, Object> signupEnterprise(@RequestBody EnterpriseUserDTO enterpriseUserDTO) {
        authService.registerEnterpriseUser(enterpriseUserDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Enterprise user registered successfully");
        response.put("user", enterpriseUserDTO);
        return response;
    }

    @GetMapping("/current-user")
    public Map<String, Object> findById(HttpSession session) {
        if (session.getAttribute("username") == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User not found");
            return response;
        }

        Optional<?> userOptional = authService.getUser(session.getAttribute("username").toString());

        if (userOptional.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User not found");
            return response;
        }

        Object userObject = userOptional.get();
        Map<String, Object> response = new HashMap<>();

        if (userObject instanceof LaborUserDTO laborUserDTO) {
            response.put("userType", "LaborUser");
            response.put("username", laborUserDTO.getUsername());
            response.put("email", laborUserDTO.getEmail());
            response.put("name", laborUserDTO.getName());
        } else if (userObject instanceof EnterpriseUserDTO enterpriseUserDTO) {
            response.put("userType", "EnterpriseUser");
            response.put("username", enterpriseUserDTO.getUsername());
            response.put("email", enterpriseUserDTO.getEmail());
            response.put("name", enterpriseUserDTO.getName());
            response.put("enterprise", enterpriseUserDTO.getEnterprise());
        }

        return response;
    }

    @PostMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화

        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return response;
    }
}
