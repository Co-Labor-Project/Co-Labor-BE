package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.service.SupportCenterService;

@RestController
@RequestMapping("/api/support-centers")
public class SupportCenterController {

    private final SupportCenterService supportCenterService;

    @Autowired
    public SupportCenterController(SupportCenterService supportCenterService) {
        this.supportCenterService = supportCenterService;
    }

    @PostMapping("/fetch")
    public ResponseEntity<String> fetchSupportCenters() {
        try {
            supportCenterService.fetchAndSaveSupportCenters();
            return ResponseEntity.status(HttpStatus.OK).body("Data fetched and saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch data.");
        }
    }
}
