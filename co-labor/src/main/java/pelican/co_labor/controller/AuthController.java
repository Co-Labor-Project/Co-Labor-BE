package pelican.co_labor.controller;

import lombok.RequiredArgsConstructor;
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
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        boolean authenticated = authService.authenticateUser(username, password);

        Map<String, String> response = new HashMap<>();
        if (authenticated) {
            response.put("message", "Login successful");
            response.put("redirect", "/index");
        } else {
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
}
