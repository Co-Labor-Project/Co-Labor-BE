package pelican.co_labor.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class OpenAiService {

    private static final Logger logger = LoggerFactory.getLogger(OpenAiService.class);

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> extractKeywords(String sentence) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://api.openai.com/v1/chat/completions")
                .build().toUri();

        try {
            boolean isKorean = Pattern.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*", sentence);
            String systemPrompt = isKorean ?
                    "당신은 데이터 추출 도우미입니다. 주어진 문장에서 주요 키워드를 추출하세요. 간결하고 관련성 있는 키워드를 제공하세요. 또한 키워드만 제공하고 추가적인 키워드도 제공해주세요. DB 검색 용도이니 키워드로 제공해야 하고 sentence에 존재하지 않는 연관된 키워드도 유추해서 추가로 제공해주세요." :
                    "You are a data extraction assistant. Extract key phrases and concepts from the provided sentences. Just provide only keywords. Provide additional keywords including input sentence's primary keywords.";
            String userPrompt = isKorean ?
                    "이 문장에서 키워드를 추출하세요: " + sentence :
                    "Extract keywords from this sentence: " + sentence;

            JSONArray messages = new JSONArray();
            messages.put(new JSONObject().put("role", "system").put("content", systemPrompt));
            messages.put(new JSONObject().put("role", "user").put("content", userPrompt));

            JSONObject request = new JSONObject();
            request.put("model", "gpt-3.5-turbo");
            request.put("messages", messages);
            request.put("temperature", 0.5);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            JSONObject jsonResponse = new JSONObject(response.getBody());
            JSONArray choices = jsonResponse.getJSONArray("choices");

            List<String> keywords = new ArrayList<>();
            for (int i = 0; i < choices.length(); i++) {
                String text = choices.getJSONObject(i).getJSONObject("message").getString("content").trim();

                // Remove "추가 키워드:" or "Additional keywords:" if present
                text = text.replaceAll("(?i)(추가 키워드:|Additional keywords:)", "").trim();

                String[] extractedKeywords = text.split(", ");
                for (String keyword : extractedKeywords) {
                    keyword = keyword.replaceAll("[^\\p{IsAlphabetic}\\p{IsHangul}]", "").trim(); // Keep only alphabetic characters and Hangul
                    if (!keyword.isEmpty()) {
                        keywords.add(keyword);
                    }
                }
            }

            logger.info("Extracted keywords: {}", keywords);
            return keywords;
        } catch (Exception e) {
            logger.error("An error occurred while calling OpenAI API", e);
            return new ArrayList<>();
        }
    }
}
