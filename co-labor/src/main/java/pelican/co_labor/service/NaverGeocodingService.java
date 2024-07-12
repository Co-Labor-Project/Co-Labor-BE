package pelican.co_labor.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NaverGeocodingService {

    @Value("${naver.map.client.id}")
    private String clientId;

    @Value("${naver.map.client.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    public NaverGeocodingService() {
        this.restTemplate = new RestTemplate();
    }

    public String getCoordinates(String address) {
        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + address;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONObject addressObject = jsonResponse.getJSONArray("addresses").getJSONObject(0);

        String latitude = addressObject.getString("y");
        String longitude = addressObject.getString("x");

        return latitude + "," + longitude;
    }
}
