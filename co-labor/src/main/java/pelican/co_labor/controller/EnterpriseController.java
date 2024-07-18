package pelican.co_labor.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.dto.EnterpriseQueueDTO;
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

    private final EnterpriseService enterpriseService;
    private final EnterpriseFetchApiService enterpriseFetchApiService;
    private final EnterpriseRegistrationService enterpriseRegistrationService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService, EnterpriseFetchApiService enterpriseFetchApiService, EnterpriseRegistrationService enterpriseRegistrationService) {
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

    @PutMapping("/{enterprise_id}")
    public ResponseEntity<Enterprise> updateEnterprise(@PathVariable String enterprise_id, @RequestBody Enterprise enterpriseDetails) {
        Optional<Enterprise> updatedEnterprise = enterpriseService.updateEnterprise(enterprise_id, enterpriseDetails);
        return updatedEnterprise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 사업자 등록 번호 조회해서 기업 상태 확인
    @GetMapping("/status")
    public ResponseEntity<String> getBusinessStatus(@RequestParam("enterpriseId") String enterpriseId) {
        ResponseEntity<String> response = enterpriseRegistrationService.isValidEnterpriseId(enterpriseId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
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
