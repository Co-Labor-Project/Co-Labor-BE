package pelican.co_labor.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.labor_user.LaborUser;
import pelican.co_labor.domain.review.Review;
import pelican.co_labor.dto.ReviewDTO;
import pelican.co_labor.service.AuthService;
import pelican.co_labor.service.EnterpriseService;
import pelican.co_labor.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final AuthService authService;
    private final EnterpriseService enterpriseService;


    @Autowired
    public ReviewController(ReviewService reviewService, AuthService authService, EnterpriseService enterpriseService) {
        this.reviewService = reviewService;
        this.authService = authService;
        this.enterpriseService = enterpriseService;
    }


    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping
    public Review getReviewById(@RequestParam("reviewId") String review_id) {
        return reviewService.getReviewById(review_id);
    }

    @GetMapping("/{enterpriseId}")
    public List<Review> getReviewsByEnterpriseId(@PathVariable("enterpriseId") String enterprise_id) {
        return reviewService.getReviewByEnterpriseId(enterprise_id);
    }

    @PostMapping("/{enterpriseId}")
    public Review addReview(@PathVariable("enterpriseId") String enterprise_id, @RequestBody ReviewDTO reviewDTO, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);  // 세션이 없으면 null 리턴
        String username = session.getAttribute("username").toString();

        LaborUser laborUser = authService.findLaborUserById(username).get();
        Enterprise enterprise = enterpriseService.getEnterpriseById(enterprise_id).get();

        Review review = new Review();
        review.setLaborUser(laborUser);
        review.setEnterprise(enterprise);
        review.setTitle(reviewDTO.getTitle());
        review.setRating(reviewDTO.getRating());
        review.setPromotion_rating(reviewDTO.getPromotionRating());
        review.setSalary_rating(reviewDTO.getSalaryRating());
        review.setBalance_rating(reviewDTO.getBalanceRating());
        review.setCulture_rating(reviewDTO.getCultureRating());
        review.setManagement_rating(reviewDTO.getManagementRating());
        review.setPros(reviewDTO.getPros());
        review.setCons(reviewDTO.getCons());
        review.setLike_count(0); // 기본 값 설정

        return reviewService.addReview(review);
    }

    @PatchMapping("/{review_id}")
    public Review updateReview(@PathVariable("review_id") String review_id, @RequestBody Review review, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);  // 세션이 없으면 null 리턴
        String username = session.getAttribute("username").toString();

        Review existingReview = reviewService.getReviewById(review_id);
        if (!existingReview.getLaborUser().getLaborUserId().equals(username)) {
            return null;
        }

        return reviewService.updateReview(review_id, review);
    }

    @DeleteMapping("/{review_id}")
    public void deleteReview(@PathVariable("review_id") String review_id, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);  // 세션이 없으면 null 리턴
        String username = session.getAttribute("username").toString();

        Review existingReview = reviewService.getReviewById(review_id);
        if (!existingReview.getLaborUser().getLaborUserId().equals(username)) {
            return;
        }

        reviewService.deleteReview(review_id);
    }
}
