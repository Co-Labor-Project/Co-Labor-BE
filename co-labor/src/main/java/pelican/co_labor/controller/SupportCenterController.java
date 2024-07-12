package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pelican.co_labor.domain.support_center.SupportCenter;
import pelican.co_labor.service.SupportCenterService;

import java.util.List;

@RestController
@RequestMapping("/api/support-centers")
public class SupportCenterController {

    private final SupportCenterService supportCenterService;

    @Autowired
    public SupportCenterController(SupportCenterService supportCenterService) {
        this.supportCenterService = supportCenterService;
    }

    @GetMapping("/fetch")
    public void fetchData() {
        supportCenterService.fetchDataFromApi();
    }

    @GetMapping("/all")
    public List<SupportCenter> getAllSupportCenters() {
        return supportCenterService.getAllSupportCenters();
    }
}
