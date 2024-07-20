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

import java.time.LocalDate;

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
        enterprise1.setEnterprise_id("1111111111");
        enterprise1.setName("Tech Company");
        enterprise1.setAddress1("서울특별시");
        enterprise1.setAddress2("강동구");
        enterprise1.setAddress3("무슨동");
        enterprise1.setDescription("Leading tech company in the industry.");
        enterprise1.setType("유통");
        enterprise1.setPhone_number("1234-1234");
        enterpriseRepository.save(enterprise1);

        Enterprise enterprise2 = new Enterprise();
        enterprise2.setEnterprise_id("2222222222");
        enterprise2.setName("Data Corp");
        enterprise2.setAddress1("대구광역시");
        enterprise2.setAddress2("달서구");
        enterprise2.setAddress3("진천동");
        enterprise2.setDescription("Innovative data solutions provider.");
        enterprise2.setType("건설");
        enterprise2.setPhone_number("5678-5678");
        enterpriseRepository.save(enterprise2);

        // EnterpriseUser 더미 데이터 생성
        EnterpriseUser enterpriseUser1 = new EnterpriseUser();
        enterpriseUser1.setEnterprise_user_id("John Doe");
        enterpriseUser1.setName("John Doe");
        enterpriseUser1.setEmail("john.doe@techcompany.com");
        enterpriseUser1.setPassword("password123");
        enterpriseUser1.setEnterprise(enterprise1);
        enterpriseUserRepository.save(enterpriseUser1);

        EnterpriseUser enterpriseUser2 = new EnterpriseUser();
        enterpriseUser2.setEnterprise_user_id("Jane Smith");
        enterpriseUser2.setName("Jane Smith");
        enterpriseUser2.setEmail("jane.smith@datacorp.com");
        enterpriseUser2.setPassword("password123");
        enterpriseUser2.setEnterprise(enterprise2);
        enterpriseUserRepository.save(enterpriseUser2);

        // LaborUser 더미 데이터 생성
        LaborUser laborUser1 = new LaborUser();
        laborUser1.setLaborUserId("Alice Johnson");
        laborUser1.setName("Alice Johnson");
        laborUser1.setEmail("alice.johnson@example.com");
        laborUser1.setPassword("password123");
        laborUserRepository.save(laborUser1);

        LaborUser laborUser2 = new LaborUser();
        laborUser2.setLaborUserId("Bob Brown");
        laborUser2.setName("Bob Brown");
        laborUser2.setEmail("bob.brown@example.com");
        laborUser2.setPassword("password123");
        laborUserRepository.save(laborUser2);


        // Job 더미 데이터 생성
        Job job1 = new Job();
        job1.setTitle("Tech Engineer");
        job1.setViews(100);
        job1.setDead_date(LocalDate.now().plusDays(30));
        job1.setEnterprise(enterprise1);
        job1.setEnterpriseUser(enterpriseUser1);
        job1.setImageName("tech_engineer1.jpg");
        job1.setCareer("Junior");
        job1.setEmploymentType("Full-time");
        job1.setWorkLocation("Seoul, Korea");
        job1.setSkills("Java, Spring, SQL");
        job1.setMainTasks("Develop and maintain software solutions.");
        job1.setQualifications("Bachelor's degree in Computer Science or related field.");
        job1.setPreferences("Experience with cloud services.");
        job1.setHiringProcess("Online assessment, Technical interview, HR interview");
        job1.setBenefits("Health insurance, Stock options, Free lunch");
        jobRepository.save(job1);

        Job job2 = new Job();
        job2.setTitle("Data Scientist");
        job2.setViews(150);
        job2.setDead_date(LocalDate.now().plusDays(45));
        job2.setEnterprise(enterprise2);
        job2.setEnterpriseUser(enterpriseUser2);
        job2.setImageName("data_scientist1.jpg");
        job2.setCareer("Mid-level");
        job2.setEmploymentType("Part-time");
        job2.setWorkLocation("Busan, Korea");
        job2.setSkills("Python, R, SQL, Machine Learning");
        job2.setMainTasks("Analyze and interpret complex data sets.");
        job2.setQualifications("Master's degree in Data Science or related field.");
        job2.setPreferences("Experience with big data tools.");
        job2.setHiringProcess("Technical interview, HR interview, Final interview");
        job2.setBenefits("Health insurance, Flexible working hours");
        jobRepository.save(job2);

        Job job3 = new Job();
        job3.setTitle("Backend Developer");
        job3.setViews(80);
        job3.setDead_date(LocalDate.now().plusDays(20));
        job3.setEnterprise(enterprise1);
        job3.setEnterpriseUser(enterpriseUser1);
        job3.setImageName("backend_dev.jpg");
        job3.setCareer("Senior");
        job3.setEmploymentType("Full-time");
        job3.setWorkLocation("Incheon, Korea");
        job3.setSkills("Java, Spring Boot, PostgreSQL");
        job3.setMainTasks("Develop and maintain backend systems.");
        job3.setQualifications("5+ years of experience in backend development.");
        job3.setPreferences("Experience with microservices architecture.");
        job3.setHiringProcess("Coding test, Technical interview, HR interview");
        job3.setBenefits("Health insurance, Retirement plan, Free snacks");
        jobRepository.save(job3);

        Job job4 = new Job();
        job4.setTitle("Frontend Developer");
        job4.setViews(120);
        job4.setDead_date(LocalDate.now().plusDays(25));
        job4.setEnterprise(enterprise2);
        job4.setEnterpriseUser(enterpriseUser2);
        job4.setImageName("frontend_dev.jpg");
        job4.setCareer("Mid-level");
        job4.setEmploymentType("Contract");
        job4.setWorkLocation("Daejeon, Korea");
        job4.setSkills("React, JavaScript, HTML, CSS");
        job4.setMainTasks("Develop and maintain frontend systems.");
        job4.setQualifications("3+ years of experience in frontend development.");
        job4.setPreferences("Experience with responsive design.");
        job4.setHiringProcess("Portfolio review, Technical interview, HR interview");
        job4.setBenefits("Health insurance, Paid time off");
        jobRepository.save(job4);

        Job job5 = new Job();
        job5.setTitle("UX/UI Designer");
        job5.setViews(90);
        job5.setDead_date(LocalDate.now().plusDays(35));
        job5.setEnterprise(enterprise1);
        job5.setEnterpriseUser(enterpriseUser1);
        job5.setImageName("ux_ui_designer.jpg");
        job5.setCareer("Junior");
        job5.setEmploymentType("Internship");
        job5.setWorkLocation("Seoul, Korea");
        job5.setSkills("Adobe XD, Figma, Sketch");
        job5.setMainTasks("Design user-friendly interfaces.");
        job5.setQualifications("Bachelor's degree in Design or related field.");
        job5.setPreferences("Experience with prototyping tools.");
        job5.setHiringProcess("Design challenge, Technical interview, HR interview");
        job5.setBenefits("Health insurance, Free meals");
        jobRepository.save(job5);

        // 한국어 채용공고 더미 데이터 생성
        Job job6 = new Job();
        job6.setTitle("소프트웨어 엔지니어");
        job6.setViews(70);
        job6.setDead_date(LocalDate.now().plusDays(40));
        job6.setEnterprise(enterprise2);
        job6.setEnterpriseUser(enterpriseUser2);
        job6.setImageName("software_engineer.jpg");
        job6.setCareer("신입");
        job6.setEmploymentType("정규직");
        job6.setWorkLocation("서울, 한국");
        job6.setSkills("Java, Spring, MySQL");
        job6.setMainTasks("소프트웨어 애플리케이션 개발 및 유지보수.");
        job6.setQualifications("컴퓨터 공학 학사 학위 소지자.");
        job6.setPreferences("클라우드 서비스 경험 우대.");
        job6.setHiringProcess("온라인 평가, 기술 면접, 인사 면접");
        job6.setBenefits("건강 보험, 주식 옵션, 무료 점심 제공");
        jobRepository.save(job6);



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
        review2.setEnterprise(enterprise2);
        review2.setLaborUser(laborUser2);
        reviewRepository.save(review2);
    }
}
