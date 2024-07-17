package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.service.EnterpriseFetchApiService;
import pelican.co_labor.service.EnterpriseRegistrationService;
import pelican.co_labor.service.EnterpriseService;

import java.util.List;
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
    public ResponseEntity<Enterprise> getEnterpriseById(@PathVariable Long enterprise_id) {
        Optional<Enterprise> enterprise = enterpriseService.getEnterpriseById(enterprise_id);
        return enterprise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
    public ResponseEntity<Enterprise> updateEnterprise(@PathVariable Long enterprise_id, @RequestBody Enterprise enterpriseDetails) {
        Optional<Enterprise> updatedEnterprise = enterpriseService.updateEnterprise(enterprise_id, enterpriseDetails);
        return updatedEnterprise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 사업자 등록 번호 조회해서 기업 상태 확인
    @GetMapping("/status")
    public ResponseEntity<String> getBusinessStatus(@RequestParam("enterpriseId") String enterpriseId) {
        ResponseEntity<String> response = enterpriseRegistrationService.isValidEnterpriseId(enterpriseId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
