package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pelican.co_labor.domain.support_center.SupportCenter;
import pelican.co_labor.repository.support_center.SupportCenterRepository;

import java.util.List;
import java.util.Map;

@Service
public class SupportCenterService {

    @Value("${public.data.api.key}")
    private String apiKey;

    private final SupportCenterRepository supportCenterRepository;

    @Autowired
    public SupportCenterService(SupportCenterRepository supportCenterRepository) {
        this.supportCenterRepository = supportCenterRepository;
    }

    public void fetchAndSaveSupportCenters() {
        String url = "https://api.odcloud.kr/api/3038226/v1/uddi:6c52a2c3-b1ed-407c-ada3-8c14769beec1?page=1&perPage=100&serviceKey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        List<Map<String, String>> records = (List<Map<String, String>>) response.getBody().get("data");

        for (Map<String, String> record : records) {
            SupportCenter supportCenter = new SupportCenter();
            supportCenter.setCenter_type(record.get("유형"));
            supportCenter.setName(record.get("기관명(거점센터 운영기관)"));
            supportCenter.setAddress(record.get("소재지"));
            supportCenter.setPhone(record.get("연락처"));

            supportCenterRepository.save(supportCenter);
        }
    }
}
