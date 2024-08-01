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
        String prompt = "조건 1번. 너의 역할은 외국인 근로자와 상담하는 법률 챗봇이야.\n" +
                "조건 2번. 지금부터 외국인 근로자가 너에게 법률 상담 질문을 하고, 넌 그 질문에 대한 대답을 조건 3번에 맞춰서 답변해줘.\n" +
                "조건 3번. 첫 번째 문단: 질문을 요약.  두 번째 문단: 질문에 대한 답변. 세 번째 문단: 나의 상황에 맞춘 조언 및 참고 판례.\n" +
                "법률 상담 질문: " + userMessage + "\n 위의 법률 상담 질문에 대한 답변을 조건 1, 2, 3번에 맞게 공감적으고 상담사처럼 친절하게 한국어로 작성해.";

        System.out.println(prompt);

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
        body.put("max_tokens", 1300);

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
