//package pelican.co_labor.service;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class OpenAiService {
//
//    private static final Logger logger = LoggerFactory.getLogger(OpenAiService.class);
//
//    @Value("${openai.api.key}")
//    private String apiKey;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public List<String> extractKeywords(String sentence) {
//        String url = "https://api.openai.com/v1/engines/text-davinci-003/completions";
//
//        try {
//            JSONObject request = new JSONObject();
//            request.put("prompt", "Extract keywords from this sentence: " + sentence);
//            request.put("max_tokens", 60);
//            request.put("n", 1);
//            request.put("stop", ".");
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setBearerAuth(apiKey);
//
//            HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);
//
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//            JSONObject jsonResponse = new JSONObject(response.getBody());
//            JSONArray choices = jsonResponse.getJSONArray("choices");
//
//            List<String> keywords = new ArrayList<>();
//            for (int i = 0; i < choices.length(); i++) {
//                String text = choices.getJSONObject(i).getString("text");
//                String[] extractedKeywords = text.split(", ");
//                for (String keyword : extractedKeywords) {
//                    keywords.add(keyword.trim());
//                }
//            }
//
//            return keywords;
//        } catch (JSONException e) {
//            logger.error("An error occurred while parsing the JSON response from OpenAI", e);
//            return new ArrayList<>();
//        } catch (Exception e) {
//            logger.error("An error occurred while calling OpenAI API", e);
//            return new ArrayList<>();
//        }
//    }
//}
