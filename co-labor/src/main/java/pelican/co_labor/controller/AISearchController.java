package pelican.co_labor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.domain.review.Review;
import pelican.co_labor.service.KeywordSearchService;
import pelican.co_labor.service.SearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AISearchController {

    private static final Logger logger = LoggerFactory.getLogger(AISearchController.class);

    @Autowired
    private KeywordSearchService keywordSearchService;

    @Autowired
    private SearchService searchService;

    @GetMapping("/ai-search")
    public Map<String, Object> search(@RequestParam String keyword) {
        Map<String, Object> response = new HashMap<>();

        List<String> similarKeywords = keywordSearchService.searchSimilarWords(keyword);
        logger.info("Similar keywords: {}", similarKeywords);

        if (similarKeywords.isEmpty()) {
            response.put("message", "No similar words found for the given keyword in the database.");
            return response;
        }

        // DB 검색
        List<Job> jobs = searchService.searchJobs(similarKeywords);
        List<Review> reviews = searchService.searchReviews(similarKeywords);
        List<Enterprise> enterprises = searchService.searchEnterprises(similarKeywords);

        response.put("jobs", jobs);
        response.put("reviews", reviews);
        response.put("enterprises", enterprises);

        return response;
    }
}
