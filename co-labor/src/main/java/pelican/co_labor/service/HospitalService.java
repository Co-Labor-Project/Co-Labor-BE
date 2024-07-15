package pelican.co_labor.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pelican.co_labor.domain.hospital.Hospital;
import pelican.co_labor.domain.hospital.HospitalResponse;
import pelican.co_labor.repository.hospital.HospitalRepository;

import java.io.IOException;
import java.util.List;

@Service
public class HospitalService {

    @Value("${public.data.api.hospital.key.decoded}")
    private String hospitalApiKeyDecoded;

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public void fetchAllHospitalData() {
        int pageNo = 1;
        int numOfRows = 100;  // 한 페이지당 데이터 개수
        int totalCount = 0;  // 전체 데이터 개수

        // 첫 번째 호출로 totalCount를 가져옴
        String url = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncLcinfoInqire"
                + "?serviceKey=" + hospitalApiKeyDecoded
                + "&pageNo=" + pageNo
                + "&numOfRows=" + numOfRows;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> firstResponse = restTemplate.getForEntity(url, String.class);

        if (firstResponse.getStatusCode().is2xxSuccessful() && firstResponse.getBody() != null) {
            System.out.println("First Response Body: " + firstResponse.getBody()); // 응답 출력

            XmlMapper xmlMapper = new XmlMapper();
            try {
                HospitalResponse hospitalResponse = xmlMapper.readValue(firstResponse.getBody(), HospitalResponse.class);
                if (hospitalResponse != null && hospitalResponse.getBody() != null) {
                    System.out.println("Parsed Hospital Response: " + hospitalResponse);
                    if (hospitalResponse.getBody().getItems() != null) {
                        List<HospitalResponse.Item> items = hospitalResponse.getBody().getItems().getItemList();
                        saveHospitals(items);

                        // 전체 데이터 개수 확인
                        totalCount = hospitalResponse.getBody().getTotalCount();
                    } else {
                        System.out.println("Items are null or empty.");
                    }
                } else {
                    System.out.println("Hospital Response or its body is null.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Failed to fetch data from API");
        }

        // 나머지 페이지 데이터를 가져옴
        for (pageNo = 2; pageNo <= (totalCount / numOfRows) + 1; pageNo++) {
            url = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncLcinfoInqire"
                    + "?serviceKey=" + hospitalApiKeyDecoded
                    + "&pageNo=" + pageNo
                    + "&numOfRows=" + numOfRows;

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                System.out.println("Response Body for page " + pageNo + ": " + response.getBody()); // 각 페이지 응답 출력

                XmlMapper xmlMapper = new XmlMapper();
                try {
                    HospitalResponse hospitalResponse = xmlMapper.readValue(response.getBody(), HospitalResponse.class);
                    if (hospitalResponse != null && hospitalResponse.getBody() != null) {
                        if (hospitalResponse.getBody().getItems() != null) {
                            List<HospitalResponse.Item> items = hospitalResponse.getBody().getItems().getItemList();
                            saveHospitals(items);
                        } else {
                            System.out.println("Items are null or empty for page " + pageNo);
                        }
                    } else {
                        System.out.println("Hospital Response or its body is null for page " + pageNo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new RuntimeException("Failed to fetch data from API for page " + pageNo);
            }
        }
    }

    private void saveHospitals(List<HospitalResponse.Item> items) {
        if (items == null) {
            System.out.println("Items list is null");
            return;
        }

        for (HospitalResponse.Item item : items) {
            Hospital hospital = new Hospital();
            hospital.setDutyAddr(item.getDutyAddr());
            hospital.setDutyName(item.getDutyName());
            hospital.setDutyTel1(item.getDutyTel1());
            hospital.setRnum(item.getRnum());
            hospital.setWgs84Lat(item.getWgs84Lat());
            hospital.setWgs84Lon(item.getWgs84Lon());

            hospitalRepository.save(hospital);
        }
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}
