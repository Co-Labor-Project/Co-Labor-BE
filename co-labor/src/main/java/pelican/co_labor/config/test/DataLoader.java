package pelican.co_labor.config.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.domain.review.Review;
import pelican.co_labor.repository.job.JobRepository;
import pelican.co_labor.repository.review.ReviewRepository;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(JobRepository jobRepository, ReviewRepository reviewRepository) {
        return (args) -> {
            // Job 더미 데이터 생성
            Job job1 = new Job();
            job1.setTitle("Software Engineer");
            job1.setDescription("Develop and maintain software solutions.");
            job1.setGender("Any");
            job1.setAge("Any");
            job1.setViews(100);
            job1.setDead_date(LocalDateTime.now().plusDays(30));
            job1.setCreated_at(LocalDateTime.now());
            job1.setModified_at(LocalDateTime.now());

            Job job2 = new Job();
            job2.setTitle("Data Scientist");
            job2.setDescription("Analyze and interpret complex data sets.");
            job2.setGender("Any");
            job2.setAge("Any");
            job2.setViews(150);
            job2.setDead_date(LocalDateTime.now().plusDays(45));
            job2.setCreated_at(LocalDateTime.now());
            job2.setModified_at(LocalDateTime.now());

            jobRepository.save(job1);
            jobRepository.save(job2);

            // Review 더미 데이터 생성
            Review review1 = new Review();
            review1.setTitle("Great Place to Work");
            review1.setRating(5);
            review1.setPromotion_rating(4);
            review1.setSalary_rating(3);
            review1.setBalance_rating(5);
            review1.setCulture_rating(4);
            review1.setManagement_rating(4);
            review1.setPros("Great work-life balance and culture.");
            review1.setCons("Salary could be higher.");
            review1.setLike_count(10);
            review1.setCreated_at(LocalDateTime.now());
            review1.setModified_at(LocalDateTime.now());

            Review review2 = new Review();
            review2.setTitle("Challenging Environment");
            review2.setRating(4);
            review2.setPromotion_rating(3);
            review2.setSalary_rating(4);
            review2.setBalance_rating(3);
            review2.setCulture_rating(4);
            review2.setManagement_rating(3);
            review2.setPros("Great learning opportunities.");
            review2.setCons("Work-life balance can be challenging.");
            review2.setLike_count(8);
            review2.setCreated_at(LocalDateTime.now());
            review2.setModified_at(LocalDateTime.now());

            reviewRepository.save(review1);
            reviewRepository.save(review2);
        };
    }
}
