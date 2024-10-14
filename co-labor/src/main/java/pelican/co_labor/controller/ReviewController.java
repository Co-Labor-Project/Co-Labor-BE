package pelican.co_labor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Review", description = "기업 리뷰 관련 API")
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

    @Operation(summary = "모든 리뷰 조회", description = "등록된 모든 기업 리뷰를 조회합니다.")
    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @Operation(summary = "특정 리뷰 조회", description = "리뷰 ID로 특정 기업 리뷰를 조회합니다.")
    @GetMapping
    public Review getReviewById(
            @Parameter(description = "리뷰 ID") @RequestParam("reviewId") String review_id) {
        return reviewService.getReviewById(review_id);
    }

    @Operation(summary = "특정 기업의 모든 리뷰 조회", description = "기업 ID로 해당 기업의 모든 리뷰를 조회합니다.")
    @GetMapping("/{enterpriseId}")
    public List<Review> getReviewsByEnterpriseId(
            @Parameter(description = "기업 ID") @PathVariable("enterpriseId") String enterprise_id) {
        return reviewService.getReviewByEnterpriseId(enterprise_id);
    }

    @Operation(summary = "리뷰 작성", description = "특정 기업에 대한 리뷰를 작성합니다.")
    @PostMapping("/{enterpriseId}")
    public Review addReview(
            @Parameter(description = "기업 ID") @PathVariable("enterpriseId") String enterprise_id,
            @RequestBody ReviewDTO reviewDTO, HttpServletRequest httpServletRequest) {
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

    @Operation(summary = "리뷰 수정", description = "특정 리뷰 ID에 해당하는 리뷰를 수정합니다.")
    @PatchMapping("/{review_id}")
    public Review updateReview(
            @Parameter(description = "리뷰 ID") @PathVariable("review_id") String review_id,
            @RequestBody Review review, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);  // 세션이 없으면 null 리턴
        String username = session.getAttribute("username").toString();

        Review existingReview = reviewService.getReviewById(review_id);
        if (!existingReview.getLaborUser().getLaborUserId().equals(username)) {
            return null;
        }

        return reviewService.updateReview(review_id, review);
    }

    @Operation(summary = "리뷰 삭제", description = "특정 리뷰 ID에 해당하는 리뷰를 삭제합니다.")
    @DeleteMapping("/{review_id}")
    public void deleteReview(
            @Parameter(description = "리뷰 ID") @PathVariable("review_id") String review_id,
            HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);  // 세션이 없으면 null 리턴
        String username = session.getAttribute("username").toString();

        Review existingReview = reviewService.getReviewById(review_id);
        if (!existingReview.getLaborUser().getLaborUserId().equals(username)) {
            return;
        }

        reviewService.deleteReview(review_id);
    }
}