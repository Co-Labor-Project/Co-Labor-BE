package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.domain.review.Review;
import pelican.co_labor.service.Search.SearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public Map<String, Object> search(@RequestParam String keyword) {
        Map<String, Object> response = new HashMap<>();

        List<Job> jobs = searchService.searchJobs(keyword);
        List<Review> reviews = searchService.searchReviews(keyword);
        List<Enterprise> enterprises = searchService.searchEnterprises(keyword);

        response.put("jobs", jobs);
        response.put("reviews", reviews);
        response.put("enterprises", enterprises);

        System.out.println("response = " + response);

        return response;
    }
}
