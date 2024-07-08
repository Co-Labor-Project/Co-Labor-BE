package pelican.co_labor.repository.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.review.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE LOWER(r.pros) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(r.cons) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Review> searchReviews(@Param("keyword") String keyword);
}

