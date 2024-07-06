package pelican.co_labor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.domain.review.Review;
import pelican.co_labor.repository.job.JobRepository;
import pelican.co_labor.repository.review.ReviewRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {

    private final JobRepository jobRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public MainController(JobRepository jobRepository, ReviewRepository reviewRepository) {
        this.jobRepository = jobRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public Map<String, Object> getMainPageData() {
        /*
        로그인관련 클래스 구현 후 수정 필요
         */
        Map<String, Object> response = new HashMap<>();

        List<Job> jobs = jobRepository.findAll();
        List<Review> reviews = reviewRepository.findAll();

        response.put("jobs", jobs);
        response.put("reviews", reviews);

        return response;
    }
}
