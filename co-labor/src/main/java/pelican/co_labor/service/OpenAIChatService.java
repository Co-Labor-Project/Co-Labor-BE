package pelican.co_labor.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIChatService {

    private final RestTemplate restTemplate;
    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Autowired
    public OpenAIChatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGptResponse(String userMessage) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.set("Content-Type", "application/json");

        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", userMessage);

        JSONArray messages = new JSONArray();
        messages.put(message);

        JSONObject body = new JSONObject();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", messages);
        body.put("max_tokens", 800);

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.openai.com/v1/chat/completions",
                HttpMethod.POST,
                request,
                String.class
        );

        JSONObject responseBody = new JSONObject(response.getBody());
        return responseBody.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content").trim();
    }
}
