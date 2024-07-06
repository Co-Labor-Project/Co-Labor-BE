package pelican.co_labor.repository.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.review.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
