package pelican.co_labor.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;
import pelican.co_labor.dto.EnterpriseQueueDTO;
import pelican.co_labor.service.AuthService;
import pelican.co_labor.service.EnterpriseFetchApiService;
import pelican.co_labor.service.EnterpriseRegistrationService;
import pelican.co_labor.service.EnterpriseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/enterprises")
public class EnterpriseController {

    private final AuthService authService;
    private final EnterpriseService enterpriseService;
    private final EnterpriseFetchApiService enterpriseFetchApiService;
    private final EnterpriseRegistrationService enterpriseRegistrationService;

    @Autowired
    public EnterpriseController(AuthService authService, EnterpriseService enterpriseService, EnterpriseFetchApiService enterpriseFetchApiService, EnterpriseRegistrationService enterpriseRegistrationService) {
        this.authService = authService;
        this.enterpriseService = enterpriseService;
        this.enterpriseFetchApiService = enterpriseFetchApiService;
        this.enterpriseRegistrationService = enterpriseRegistrationService;
    }

    @GetMapping
    public List<Enterprise> getAllEnterprises() {
        return enterpriseService.getAllEnterprises();
    }

    @GetMapping("/{enterprise_id}")
    public ResponseEntity<?> getEnterpriseById(@PathVariable("enterprise_id") String enterprise_id) {
        try {
            Optional<Enterprise> enterprise = enterpriseService.getEnterpriseById(enterprise_id);
            if (enterprise.isPresent()) {
                return ResponseEntity.ok(enterprise.get());
            } else {
                throw new EnterpriseNotFoundException(enterprise_id);
            }
        } catch (EnterpriseNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch")
    public String fetchAndSaveEnterpriseData() {
        try {
            enterpriseFetchApiService.fetchAndSaveEnterpriseData();
            return "Data fetched and saved successfully.";
        } catch (Exception e) {
            return "An error occurred while fetching and saving data: " + e.getMessage();
        }
    }

    /**
     * 기업 등록, 수정 코드는 보완 필요
     * @param enterprise
     * @return
     */
    @PostMapping
    public Enterprise createEnterprise(@RequestBody Enterprise enterprise) {
        return enterpriseService.createEnterprise(enterprise);
    }

    // 사업자 등록 번호 조회해서 기업 회원에 매핑
    @PostMapping("/map")
    public ResponseEntity<Map<String, Object>> mapEnterprise(@RequestParam("enterpriseId") String enterpriseId,@RequestParam("username") String username ,HttpServletRequest httpServletRequest) {
//        HttpSession session = httpServletRequest.getSession(false);  // 세션이 없으면 null 리턴

        Map<String, Object> response = new HashMap<>();
//
//        if (session == null || session.getAttribute("username") == null) {
//            response.put("status", 0);
//            response.put("message", "사용자 세션이 존재하지 않습니다.");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//
//        String username = session.getAttribute("username").toString();

        Optional<EnterpriseUser> enterpriseUserOpt = authService.findEnterpriseUserById(username);
        if (enterpriseUserOpt.isEmpty()) {
            response.put("status", 0);
            response.put("message", "기업 회원이 아닙니다.");
            return ResponseEntity.badRequest().body(response);
        }

        Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId).orElse(null);
        if (enterprise == null) {
            response.put("status", 0);
            response.put("message", "유효하지 않은 기업 ID입니다.");
            return ResponseEntity.badRequest().body(response);
        }

        EnterpriseUser enterpriseUser = enterpriseUserOpt.get();
        enterpriseUser.setEnterprise(enterprise);
        authService.saveEnterpriseUser(enterpriseUser);
        response.put("status", 1);
        response.put("message", "기업 등록이 완료되었습니다.");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{enterprise_id}")
    public ResponseEntity<Enterprise> updateEnterprise(@PathVariable String enterprise_id, @RequestBody Enterprise enterpriseDetails) {
        Optional<Enterprise> updatedEnterprise = enterpriseService.updateEnterprise(enterprise_id, enterpriseDetails);
        return updatedEnterprise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 사업자 등록 번호 조회해서 기업 상태 확인
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getBusinessStatus(@RequestParam("enterpriseId") String enterpriseId) {
        try {
            ResponseEntity<String> responseEntity = enterpriseRegistrationService.isValidEnterpriseId(enterpriseId);

            // JSON 문자열을 JsonNode로 매핑
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseData = mapper.readTree(responseEntity.getBody());

            // tax_type 값을 추출
            String taxType = responseData.get("data").get(0).get("tax_type").asText();

            // Response 생성
            Map<String, Object> response = new HashMap<>();
            if ("국세청에 등록되지 않은 사업자등록번호입니다.".equals(taxType)) {
                response.put("status", 0);
                response.put("message", "국세청에 등록되지 않은 사업자등록번호입니다.");
            } else {
                response.put("status", 1);
                response.put("message", "사업자등록번호가 확인되었습니다.");
            }

            return ResponseEntity.status(responseEntity.getStatusCode()).body(response);
        } catch (Exception e) {
            // 예외 발생 시 에러 응답
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 0);
            errorResponse.put("message", "에러 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // 기업 등록 대기열에 추가
    @PostMapping("/queue")
    public Map<String, Object> createEnterpriseQueue(@RequestBody EnterpriseQueueDTO enterpriseQueueDTO) {
        try {
            enterpriseRegistrationService.registerEnterpriseQueue(enterpriseQueueDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Enterprise queue created successfully");
            response.put("enterpriseQueue", enterpriseQueueDTO);
            return response;
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "An error occurred while creating enterprise queue: " + e.getMessage());
            return response;
        }
    }

    @Getter
    @AllArgsConstructor
    static class ErrorResponse {
        private int status;
        private String message;
    }

    static class EnterpriseNotFoundException extends RuntimeException {
        public EnterpriseNotFoundException(String enterpriseId) {
            super("Enterprise not found with id: " + enterpriseId);
        }
    }
}
