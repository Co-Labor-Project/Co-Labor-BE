package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class OpenAIChatService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public OpenAIChatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGptResponse(String userMessage) {
        String prompt = userMessage + " 이 질문에 대해 공감적으로 상담사처럼 답변을 해주고 한국 헌법과 관련해서 법률 명과 관련된 판례와 키워드를 자세하게 구체적으로 알려줘. 그리고 주요 키워드를 이용하고 내가 외국인 근로자인 것을 감안해서 법률적 조언을 부탁해. 그리고 자세한 방법도 알려줘.";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.set("Content-Type", "application/json");

        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

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
