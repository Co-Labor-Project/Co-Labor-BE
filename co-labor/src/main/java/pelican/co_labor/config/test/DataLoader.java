package pelican.co_labor.config.test;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.domain.labor_user.LaborUser;
import pelican.co_labor.domain.review.Review;
import pelican.co_labor.repository.enterprise.EnterpriseRepository;
import pelican.co_labor.repository.enterprise_user.EnterpriseUserRepository;
import pelican.co_labor.repository.job.JobRepository;
import pelican.co_labor.repository.labor_user.LaborUserRepository;
import pelican.co_labor.repository.review.ReviewRepository;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EnterpriseUserRepository enterpriseUserRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private LaborUserRepository laborUserRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostConstruct
    public void loadData() {
        // Enterprise 더미 데이터 생성
        Enterprise enterprise1 = new Enterprise();
        enterprise1.setEnterprise_id("1234");
        enterprise1.setName("Tech Company");
        enterprise1.setAddress("123 Tech Street");
        enterprise1.setDescription("Leading tech company in the industry.");
        enterpriseRepository.save(enterprise1);

        Enterprise enterprise2 = new Enterprise();
        enterprise2.setEnterprise_id("5678");
        enterprise2.setName("Data Corp");
        enterprise2.setAddress("456 Data Avenue");
        enterprise2.setDescription("Innovative data solutions provider.");
        enterpriseRepository.save(enterprise2);

        // EnterpriseUser 더미 데이터 생성
        EnterpriseUser enterpriseUser1 = new EnterpriseUser();
        enterpriseUser1.setEnterprise_user_id("John Doe ID");
        enterpriseUser1.setName("John Doe");
        enterpriseUser1.setEmail("john.doe@techcompany.com");
        enterpriseUser1.setPassword("password123");
        enterpriseUser1.setEnterprise(enterprise1);
        enterpriseUserRepository.save(enterpriseUser1);

        EnterpriseUser enterpriseUser2 = new EnterpriseUser();
        enterpriseUser2.setEnterprise_user_id("Jane Smith ID");
        enterpriseUser2.setName("Jane Smith");
        enterpriseUser2.setEmail("jane.smith@datacorp.com");
        enterpriseUser2.setPassword("password123");
        enterpriseUser2.setEnterprise(enterprise2);
        enterpriseUserRepository.save(enterpriseUser2);

        // LaborUser 더미 데이터 생성
        LaborUser laborUser1 = new LaborUser();
        laborUser1.setLabor_user_id("Alice Johnson ID");
        laborUser1.setName("Alice Johnson");
        laborUser1.setEmail("alice.johnson@example.com");
        laborUser1.setPassword("password123");
        laborUserRepository.save(laborUser1);

        LaborUser laborUser2 = new LaborUser();
        laborUser2.setLabor_user_id("Bob Brown ID");
        laborUser2.setName("Bob Brown");
        laborUser2.setEmail("bob.brown@example.com");
        laborUser2.setPassword("password123");
        laborUserRepository.save(laborUser2);

        // Job 더미 데이터 생성
        Job job1 = new Job();
        job1.setTitle("Tech Engineer");
        job1.setDescription("Develop and maintain software solutions.");
        job1.setGender("Any");
        job1.setAge("Any");
        job1.setViews(100);
        job1.setDead_date(LocalDateTime.now().plusDays(30));
        job1.setCreated_at(LocalDateTime.now());
        job1.setModified_at(LocalDateTime.now());
        job1.setEnterprise(enterprise1);
        job1.setEnterpriseUser(enterpriseUser1);
        jobRepository.save(job1);

        Job job2 = new Job();
        job2.setTitle("Data Scientist");
        job2.setDescription("Analyze and interpret complex data sets.");
        job2.setGender("Any");
        job2.setAge("Any");
        job2.setViews(150);
        job2.setDead_date(LocalDateTime.now().plusDays(45));
        job2.setCreated_at(LocalDateTime.now());
        job2.setModified_at(LocalDateTime.now());
        job2.setEnterprise(enterprise2);
        job2.setEnterpriseUser(enterpriseUser2);
        jobRepository.save(job2);

        // Review 더미 데이터 생성
        Review review1 = new Review();
        review1.setTitle("Tech Place to Work");
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
        review1.setEnterprise(enterprise1);
        review1.setLaborUser(laborUser1);
        reviewRepository.save(review1);

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
        review2.setEnterprise(enterprise2);
        review2.setLaborUser(laborUser2);
        reviewRepository.save(review2);
    }
}
