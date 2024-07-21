//package pelican.co_labor.config.test;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import pelican.co_labor.domain.enterprise.Enterprise;
//import pelican.co_labor.domain.enterprise_user.EnterpriseUser;
//import pelican.co_labor.domain.job.Job;
//import pelican.co_labor.domain.labor_user.LaborUser;
//import pelican.co_labor.domain.review.Review;
//import pelican.co_labor.repository.enterprise.EnterpriseRepository;
//import pelican.co_labor.repository.enterprise_user.EnterpriseUserRepository;
//import pelican.co_labor.repository.job.JobRepository;
//import pelican.co_labor.repository.labor_user.LaborUserRepository;
//import pelican.co_labor.repository.review.ReviewRepository;
//
//import java.time.LocalDate;
//
//@Configuration
//public class DataLoader {
//
//    @Autowired
//    private EnterpriseRepository enterpriseRepository;
//
//    @Autowired
//    private EnterpriseUserRepository enterpriseUserRepository;
//
//    @Autowired
//    private JobRepository jobRepository;
//
//    @Autowired
//    private LaborUserRepository laborUserRepository;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @PostConstruct
//    public void loadData() {
//        // Enterprise 더미 데이터 생성
//        Enterprise enterprise1 = new Enterprise();
//        enterprise1.setEnterprise_id("1111111111");
//        enterprise1.setName("Tech Company");
//        enterprise1.setAddress1("서울특별시");
//        enterprise1.setAddress2("강동구");
//        enterprise1.setAddress3("무슨동");
//        enterprise1.setDescription("Leading tech company in the industry.");
//        enterprise1.setType("유통");
//        enterprise1.setPhone_number("1234-1234");
//        enterpriseRepository.save(enterprise1);
//
//        Enterprise enterprise2 = new Enterprise();
//        enterprise2.setEnterprise_id("2222222222");
//        enterprise2.setName("Data Corp");
//        enterprise2.setAddress1("대구광역시");
//        enterprise2.setAddress2("달서구");
//        enterprise2.setAddress3("진천동");
//        enterprise2.setDescription("Innovative data solutions provider.");
//        enterprise2.setType("건설");
//        enterprise2.setPhone_number("5678-5678");
//        enterpriseRepository.save(enterprise2);
//
//        Enterprise enterprise3 = new Enterprise();
//        enterprise3.setEnterprise_id("3333333333");
//        enterprise3.setName("Web Solutions");
//        enterprise3.setAddress1("부산광역시");
//        enterprise3.setAddress2("해운대구");
//        enterprise3.setAddress3("우동");
//        enterprise3.setDescription("Providing cutting-edge web solutions.");
//        enterprise3.setType("IT");
//        enterprise3.setPhone_number("9012-9012");
//        enterpriseRepository.save(enterprise3);
//
//        Enterprise enterprise4 = new Enterprise();
//        enterprise4.setEnterprise_id("4444444444");
//        enterprise4.setName("InnovaTech");
//        enterprise4.setAddress1("인천광역시");
//        enterprise4.setAddress2("남동구");
//        enterprise4.setAddress3("간석동");
//        enterprise4.setDescription("Leading innovation in technology.");
//        enterprise4.setType("제조");
//        enterprise4.setPhone_number("3456-3456");
//        enterpriseRepository.save(enterprise4);
//
//        Enterprise enterprise5 = new Enterprise();
//        enterprise5.setEnterprise_id("5555555555");
//        enterprise5.setName("MarketPlace");
//        enterprise5.setAddress1("대전광역시");
//        enterprise5.setAddress2("유성구");
//        enterprise5.setAddress3("노은동");
//        enterprise5.setDescription("Marketplace for various products and services.");
//        enterprise5.setType("서비스");
//        enterprise5.setPhone_number("7890-7890");
//        enterpriseRepository.save(enterprise5);
//
//
//        // EnterpriseUser 더미 데이터 생성
//        EnterpriseUser enterpriseUser1 = new EnterpriseUser();
//        enterpriseUser1.setEnterprise_user_id("John Doe");
//        enterpriseUser1.setName("John Doe");
//        enterpriseUser1.setEmail("john.doe@techcompany.com");
//        enterpriseUser1.setPassword("password123");
//        enterpriseUser1.setEnterprise(enterprise1);
//        enterpriseUserRepository.save(enterpriseUser1);
//
//        EnterpriseUser enterpriseUser2 = new EnterpriseUser();
//        enterpriseUser2.setEnterprise_user_id("Jane Smith");
//        enterpriseUser2.setName("Jane Smith");
//        enterpriseUser2.setEmail("jane.smith@datacorp.com");
//        enterpriseUser2.setPassword("password123");
//        enterpriseUser2.setEnterprise(enterprise2);
//        enterpriseUserRepository.save(enterpriseUser2);
//
//        EnterpriseUser enterpriseUser3 = new EnterpriseUser();
//        enterpriseUser3.setEnterprise_user_id("james_brown");
//        enterpriseUser3.setName("James Brown");
//        enterpriseUser3.setEmail("james.brown@innovatech.com");
//        enterpriseUser3.setPassword("password123");
//        enterpriseUser3.setEnterprise(enterprise3);
//        enterpriseUserRepository.save(enterpriseUser3);
//
//        EnterpriseUser enterpriseUser4 = new EnterpriseUser();
//        enterpriseUser4.setEnterprise_user_id("susan_lee");
//        enterpriseUser4.setName("Susan Lee");
//        enterpriseUser4.setEmail("susan.lee@websolutions.com");
//        enterpriseUser4.setPassword("password123");
//        enterpriseUser4.setEnterprise(enterprise4);
//        enterpriseUserRepository.save(enterpriseUser4);
//
//        EnterpriseUser enterpriseUser5 = new EnterpriseUser();
//        enterpriseUser5.setEnterprise_user_id("peter_wang");
//        enterpriseUser5.setName("Peter Wang");
//        enterpriseUser5.setEmail("peter.wang@marketplace.com");
//        enterpriseUser5.setPassword("password123");
//        enterpriseUser5.setEnterprise(enterprise5);
//        enterpriseUserRepository.save(enterpriseUser5);
//
//
//        // Job 더미 데이터 생성
//        // Job 더미 데이터 생성 (한국어 및 상세 Description 포함)
//        Job job1 = new Job();
//        job1.setTitle("기술 엔지니어");
//        job1.setDescription("[HR Manager]\n" +
//                "[채용-온보딩-교육-평가/보상-오프보딩] 등의 여정에서 구성원의 LTV를 극대화 시킵니다.\n" +
//                "\n" +
//                "[조직 소개]\n" +
//                "버핏서울의 피플팀(HR)은 ‘따뜻하고 똑똑한 스포츠팀’이라는 한 문장을 날카롭게 다듬어가는 조직입니다. 업무의 목표 차원에서의 설명을 더하자면, 피플팀은 버핏서울의 인재밀도 (Talent Density) 를 높이는 것을 목표로 합니다. 이는 아래 세가지 영역으로 구분됩니다.\n" +
//                "-1)인재를 잘 모시고 (검증, 온보딩) :TA 고도화 / 온보딩(검증&교육)\n" +
//                "-2)인재들이 몰입하는 환경을 만들고 : 피드백 / 평가&보상 / 조직문화 / 교육\n" +
//                "-3)저성과자와 잘 이별하는 것 : 피드백 / 평가&보상 / 노무\n" +
//                "\n" +
//                "[팀 메시지]\n" +
//                "∙ 조직장 한마디\n" +
//                "‘따뜻하고 똑똑한 스포츠팀' 이라는 말과 같이 버핏서울은 팀으로서 성과를 내는 것을 지향합니다. 팀원 간 강점을 인지하고, 이를 부각시킬 수 있는 역할 배치, 상호 성장을 위한 피드백, 상향평준화를 위한 승리공식 공유 문화 등 HR-driven 하는 팀에서 주도적 성장을 원한다면 지금 지원하세요.\n" +
//                "\n" +
//                "∙ 조직원 한마디\n" +
//                "‘HR을 잘하는 기업?’ 이라는 질문에 생각나는 곳이 있나요? 버핏서울은 당당히 그렇다 라고 자부할 수 있습니다. HR 전체 퍼널에 대한 깊은 이해도를 바탕으로 전사 구성원들이 끝없는 성장 할 수 있도록 심혈을 기울이기 때문입니다. 수동적인 HR을 벗어나, 성장하는 HR에 동참하고 싶다면 지금 지원해보세요.\n" +
//                "\n" +
//                "[핵심업무]\n" +
//                "∙ 1) 인재 밀도 높이는 채용 퍼널 기획 및 관리\n" +
//                "∙ 2) 스타트업에 적합한 노무이슈 관리 프로세스 및 대응\n" +
//                "∙ 3) 평가&보상 관리 개선 및 운영\n" +
//                "\n" +
//                "자격 요건\n" +
//                "∙ 1) 스타트업 노무이슈 관리를 위한 프로세스 수립 및 관리 경험이 있는 분\n" +
//                "∙ 2) HR Process (채용 - 온보딩 - 교육 - 평가/보상 - 오프보딩) 기획 및 운영 경험이 있는 분\n" +
//                "∙ 3) 스타트업 MVC 정의 및 조직문화를 빌드업 해본 경험이 있는 분\n" +
//                "\n" +
//                "우대사항\n" +
//                "∙ 1) 산업에 대한 관심 : 피트니스 생태계를 건강하게 만드는데 관심 있는 분 (찐 운동러 Good)\n" +
//                "∙ 2) 다양한 환경에서의 업무 경험 : 대기업*스타트업 다양한 공간에서 인사 업무를 경험하신 분\n" +
//                "∙ 3) 지표기반 업무환경에 대한 이해 : HR 직무의 정량/정성적 지표 설계하고 관리하는 역량이 있는 분\n" +
//                "\n" +
//                "채용 절차\n" +
//                "[채용절차]\n" +
//                "간편 접수 → 서면 인터뷰 → 1차 인터뷰 → 2차 인터뷰 → 최종합격\n" +
//                "\n" +
//                "[간편 접수]\n" +
//                "∙ 간편접수를 통해 지원해주신 분들 중 1차 인터뷰 대상자에게 연락을 드립니다.\n" +
//                "∙ 간편접수와 1차 인터뷰 사이 서면 인터뷰를 요청 드립니다.\n" +
//                "∙ 간편접수 필요 서류 : 이력서 (포트폴리오는 선택)\n" +
//                "[1차 인터뷰]\n" +
//                "∙ 지원자의 직무소양을 확인하는 인터뷰입니다.\n" +
//                "∙ 팀 리드 및 실무자와 이야기를 나누게 됩니다.\n" +
//                "∙ 40분에서 70분간 진행됩니다.\n" +
//                "[2차 인터뷰]\n" +
//                "∙ 버핏서울과 지원자 간 구체적인 Culture Fit 을 맞춰보는 시간입니다.\n" +
//                "∙ C 레벨 및 임원과 이야기를 나누게 됩니다.\n" +
//                "∙ 40분에서 70분간 진행됩니다.\n" +
//                "[최종합격]\n" +
//                "∙ 2차 인터뷰에 합격 시 레퍼런스 체크를 거쳐 최종 처우제안을 통한 협의 후 합류하게 됩니다.\n" +
//                "\n" +
//                "복지 및 혜택\n" +
//                "[업무환경]\n" +
//                "∙ OKR 기반의 목표수립\n" +
//                "∙ CFR 통한 성과 회고 시스템\n" +
//                "∙ 분기별 Peer Feedback 통한 성장 피드백\n" +
//                "∙ 년 2회 급여인상, 아웃라이어 선정 시 초과보상\n" +
//                "∙ 수시급여 클레임 통한 수시 성과 반영\n" +
//                "\n" +
//                "[복지혜택]\n" +
//                "∙ 따뜻한 팀\n" +
//                "-패밀리 할인 쿠폰 발급\n" +
//                "-건강검진 비용 지원\n" +
//                "-1/3/5년 장기근속자 포상\n" +
//                "\n" +
//                "∙ 똑똑한 팀\n" +
//                "- 교육 및 도서 구입비 지원\n" +
//                "- 체계적인 온보딩 교육\n" +
//                "- 최고의 복지는 버핏서울 동료→ 구성원 소개\n" +
//                "\n" +
//                "∙ 스포츠 팀\n" +
//                "- 팀버핏 트레이닝 무료 수강\n" +
//                "- 버핏그라운드 지점 무료 이용\n" +
//                "- 1:1 PT 및 입점 업체 운동 상품(골프/필라테스/요가 등) 할인");
//        job1.setViews(100);
//        job1.setDeadDate(LocalDate.now().plusDays(30));
//        job1.setEnterprise(enterprise1);
//        job1.setEnterpriseUser(enterpriseUser1);
//        job1.setJobRole("Frontend Development");
//        job1.setExperience("2 ~ 5년");
//        job1.setEmploymentType("정규직");
//        job1.setLocation("서울");
//        job1.setSkills("HTML, CSS, JavaScript, React, Vue.js, 디자인 툴");
//        jobRepository.save(job1);
//
//        Job job2 = new Job();
//        job2.setTitle("데이터 과학자");
//        job2.setDescription("분석 및 복잡한 데이터 세트를 해석합니다.\n" +
//                "\n" +
//                "[조직 소개]\n" +
//                "우리 팀은 다양한 산업의 데이터를 분석하여 유의미한 인사이트를 도출하고, 데이터 기반 의사결정을 지원합니다.\n" +
//                "\n" +
//                "[핵심업무]\n" +
//                "∙ 대규모 데이터 세트를 분석 및 시각화\n" +
//                "∙ 머신러닝 모델 개발 및 최적화\n" +
//                "∙ 데이터 기반 전략 수립 및 실행\n" +
//                "\n" +
//                "자격 요건\n" +
//                "∙ 데이터 분석 및 통계학에 대한 깊은 이해\n" +
//                "∙ Python, R 등 데이터 분석 도구 사용 경험\n" +
//                "∙ 머신러닝 모델 개발 경험\n" +
//                "\n" +
//                "우대사항\n" +
//                "∙ 대규모 데이터 분석 경험\n" +
//                "∙ 클라우드 환경에서의 데이터 작업 경험\n" +
//                "∙ 데이터 시각화 도구 사용 능력\n" +
//                "\n" +
//                "복지 및 혜택\n" +
//                "∙ 유연 근무제\n" +
//                "∙ 건강 검진\n" +
//                "∙ 교육 및 자기계발 지원");
//        job2.setViews(150);
//        job2.setDeadDate(LocalDate.now().plusDays(45));
//        job2.setEnterprise(enterprise2);
//        job2.setEnterpriseUser(enterpriseUser2);
//        job2.setJobRole("Data Science");
//        job2.setExperience("3 ~ 7년");
//        job2.setEmploymentType("정규직");
//        job2.setLocation("서울");
//        job2.setSkills("Python, R, 통계, 머신러닝, 빅데이터, 클라우드");
//        jobRepository.save(job2);
//
//        Job job3 = new Job();
//        job3.setTitle("프론트엔드 개발자");
//        job3.setDescription("웹 애플리케이션의 프론트엔드를 개발하고 유지보수합니다.\n" +
//                "\n" +
//                "[조직 소개]\n" +
//                "우리 팀은 사용자 경험을 최우선으로 고려하여 인터페이스를 설계하고 구현합니다.\n" +
//                "\n" +
//                "[핵심업무]\n" +
//                "∙ 사용자 친화적인 웹 인터페이스 개발\n" +
//                "∙ 성능 최적화 및 버그 수정\n" +
//                "∙ 최신 프론트엔드 기술 적용\n" +
//                "\n" +
//                "자격 요건\n" +
//                "∙ HTML, CSS, JavaScript에 대한 깊은 이해\n" +
//                "∙ React, Vue.js 등의 프레임워크 경험\n" +
//                "∙ 크로스브라우저 호환성 이슈 해결 능력\n" +
//                "\n" +
//                "우대사항\n" +
//                "∙ UX/UI 디자인 경험\n" +
//                "∙ RESTful API 연동 경험\n" +
//                "∙ 테스트 자동화 도구 사용 능력\n" +
//                "\n" +
//                "복지 및 혜택\n" +
//                "∙ 최신 장비 제공\n" +
//                "∙ 연간 교육 지원\n" +
//                "∙ 건강 보험");
//        job3.setViews(120);
//        job3.setDeadDate(LocalDate.now().plusDays(40));
//        job3.setEnterprise(enterprise3);
//        job3.setEnterpriseUser(enterpriseUser3);
//        job3.setJobRole("Mobile App Development");
//        job3.setExperience("2 ~ 6년");
//        job3.setEmploymentType("정규직");
//        job3.setLocation("서울");
//        job3.setSkills("Kotlin, Swift, iOS, Android, React Native");
//        jobRepository.save(job3);
//
//        Job job4 = new Job();
//        job4.setTitle("백엔드 개발자");
//        job4.setDescription("서버 및 데이터베이스 개발 및 유지보수합니다.\n" +
//                "\n" +
//                "[조직 소개]\n" +
//                "우리 팀은 안정적이고 확장 가능한 백엔드 시스템을 구축합니다.\n" +
//                "\n" +
//                "[핵심업무]\n" +
//                "∙ API 설계 및 개발\n" +
//                "∙ 데이터베이스 모델링 및 최적화\n" +
//                "∙ 시스템 성능 모니터링 및 개선\n" +
//                "\n" +
//                "자격 요건\n" +
//                "∙ Java, Python, Node.js 등 백엔드 언어에 대한 깊은 이해\n" +
//                "∙ SQL 및 NoSQL 데이터베이스 경험\n" +
//                "∙ 분산 시스템 및 마이크로서비스 아키텍처 이해\n" +
//                "\n" +
//                "우대사항\n" +
//                "∙ 대규모 트래픽 처리 경험\n" +
//                "∙ 클라우드 서비스 사용 경험 (AWS, GCP 등)\n" +
//                "∙ 자동화 및 CI/CD 파이프라인 구축 경험\n" +
//                "\n" +
//                "복지 및 혜택\n" +
//                "∙ 유연 근무제\n" +
//                "∙ 건강 보험\n" +
//                "∙ 연간 성과 보너스");
//        job4.setViews(130);
//        job4.setDeadDate(LocalDate.now().plusDays(50));
//        job4.setEnterprise(enterprise4);
//        job4.setEnterpriseUser(enterpriseUser4);
//        job4.setJobRole("Backend Development");
//        job4.setExperience("3 ~ 8년");
//        job4.setEmploymentType("정규직");
//        job4.setLocation("서울");
//        job4.setSkills("Java, Python, Node.js, SQL, NoSQL, AWS, GCP, CI/CD");
//        jobRepository.save(job4);
//
//
//        Job job5 = new Job();
//        job5.setTitle("HR 매니저");
//        job5.setDescription("[HR Manager]\n" +
//                "[채용-온보딩-교육-평가/보상-오프보딩] 등의 여정에서 구성원의 LTV를 극대화 시킵니다.\n" +
//                "\n" +
//                "[조직 소개]\n" +
//                "버핏서울의 피플팀(HR)은 ‘따뜻하고 똑똑한 스포츠팀’이라는 한 문장을 날카롭게 다듬어가는 조직입니다. 업무의 목표 차원에서의 설명을 더하자면, 피플팀은 버핏서울의 인재밀도 (Talent Density) 를 높이는 것을 목표로 합니다. 이는 아래 세가지 영역으로 구분됩니다.\n" +
//                "-1)인재를 잘 모시고 (검증, 온보딩) :TA 고도화 / 온보딩(검증&교육)\n" +
//                "-2)인재들이 몰입하는 환경을 만들고 : 피드백 / 평가&보상 / 조직문화 / 교육\n" +
//                "-3)저성과자와 잘 이별하는 것 : 피드백 / 평가&보상 / 노무\n" +
//                "\n" +
//                "[팀 메시지]\n" +
//                "∙ 조직장 한마디\n" +
//                "‘따뜻하고 똑똑한 스포츠팀' 이라는 말과 같이 버핏서울은 팀으로서 성과를 내는 것을 지향합니다. 팀원 간 강점을 인지하고, 이를 부각시킬 수 있는 역할 배치, 상호 성장을 위한 피드백, 상향평준화를 위한 승리공식 공유 문화 등 HR-driven 하는 팀에서 주도적 성장을 원한다면 지금 지원하세요.\n" +
//                "\n" +
//                "∙ 조직원 한마디\n" +
//                "‘HR을 잘하는 기업?’ 이라는 질문에 생각나는 곳이 있나요? 버핏서울은 당당히 그렇다 라고 자부할 수 있습니다. HR 전체 퍼널에 대한 깊은 이해도를 바탕으로 전사 구성원들이 끝없는 성장 할 수 있도록 심혈을 기울이기 때문입니다. 수동적인 HR을 벗어나, 성장하는 HR에 동참하고 싶다면 지금 지원해보세요.\n" +
//                "\n" +
//                "[핵심업무]\n" +
//                "∙ 1) 인재 밀도 높이는 채용 퍼널 기획 및 관리\n" +
//                "∙ 2) 스타트업에 적합한 노무이슈 관리 프로세스 및 대응\n" +
//                "∙ 3) 평가&보상 관리 개선 및 운영\n" +
//                "\n" +
//                "자격 요건\n" +
//                "∙ 1) 스타트업 노무이슈 관리를 위한 프로세스 수립 및 관리 경험이 있는 분\n" +
//                "∙ 2) HR Process (채용 - 온보딩 - 교육 - 평가/보상 - 오프보딩) 기획 및 운영 경험이 있는 분\n" +
//                "∙ 3) 스타트업 MVC 정의 및 조직문화를 빌드업 해본 경험이 있는 분\n" +
//                "\n" +
//                "우대사항\n" +
//                "∙ 1) 산업에 대한 관심 : 피트니스 생태계를 건강하게 만드는데 관심 있는 분 (찐 운동러 Good)\n" +
//                "∙ 2) 다양한 환경에서의 업무 경험 : 대기업*스타트업 다양한 공간에서 인사 업무를 경험하신 분\n" +
//                "∙ 3) 지표기반 업무환경에 대한 이해 : HR 직무의 정량/정성적 지표 설계하고 관리하는 역량이 있는 분\n" +
//                "\n" +
//                "채용 절차\n" +
//                "[채용절차]\n" +
//                "간편 접수 → 서면 인터뷰 → 1차 인터뷰 → 2차 인터뷰 → 최종합격\n" +
//                "\n" +
//                "[간편 접수]\n" +
//                "∙ 간편접수를 통해 지원해주신 분들 중 1차 인터뷰 대상자에게 연락을 드립니다.\n" +
//                "∙ 간편접수와 1차 인터뷰 사이 서면 인터뷰를 요청 드립니다.\n" +
//                "∙ 간편접수 필요 서류 : 이력서 (포트폴리오는 선택)\n" +
//                "[1차 인터뷰]\n" +
//                "∙ 지원자의 직무소양을 확인하는 인터뷰입니다.\n" +
//                "∙ 팀 리드 및 실무자와 이야기를 나누게 됩니다.\n" +
//                "∙ 40분에서 70분간 진행됩니다.\n" +
//                "[2차 인터뷰]\n" +
//                "∙ 버핏서울과 지원자 간 구체적인 Culture Fit 을 맞춰보는 시간입니다.\n" +
//                "∙ C 레벨 및 임원과 이야기를 나누게 됩니다.\n" +
//                "∙ 40분에서 70분간 진행됩니다.\n" +
//                "[최종합격]\n" +
//                "∙ 2차 인터뷰에 합격 시 레퍼런스 체크를 거쳐 최종 처우제안을 통한 협의 후 합류하게 됩니다.\n" +
//                "\n" +
//                "복지 및 혜택\n" +
//                "[업무환경]\n" +
//                "∙ OKR 기반의 목표수립\n" +
//                "∙ CFR 통한 성과 회고 시스템\n" +
//                "∙ 분기별 Peer Feedback 통한 성장 피드백\n" +
//                "∙ 년 2회 급여인상, 아웃라이어 선정 시 초과보상\n" +
//                "∙ 수시급여 클레임 통한 수시 성과 반영\n" +
//                "\n" +
//                "[복지혜택]\n" +
//                "∙ 따뜻한 팀\n" +
//                "-패밀리 할인 쿠폰 발급\n" +
//                "-건강검진 비용 지원\n" +
//                "-1/3/5년 장기근속자 포상\n" +
//                "\n" +
//                "∙ 똑똑한 팀\n" +
//                "- 교육 및 도서 구입비 지원\n" +
//                "- 체계적인 온보딩 교육\n" +
//                "- 최고의 복지는 버핏서울 동료→ 구성원 소개\n" +
//                "\n" +
//                "∙ 스포츠 팀\n" +
//                "- 팀버핏 트레이닝 무료 수강\n" +
//                "- 버핏그라운드 지점 무료 이용\n" +
//                "- 1:1 PT 및 입점 업체 운동 상품(골프/필라테스/요가 등) 할인");
//        job5.setViews(100);
//        job5.setDeadDate(LocalDate.now().plusDays(60));
//        job5.setEnterprise(enterprise5);
//        job5.setEnterpriseUser(enterpriseUser5);
//        job5.setJobRole("DevOps Engineering");
//        job5.setExperience("4 ~ 9년");
//        job5.setEmploymentType("정규직");
//        job5.setLocation("서울");
//        job5.setSkills("Jenkins, Docker, Kubernetes, 서버 관리, 네트워크 관리, 클라우드 운영");
//        jobRepository.save(job5);
//
//        /* ----------------------------------------------------------------------------------------------------------------------------------------------------- */
//
//        // LaborUser 더미 데이터 생성
//        LaborUser laborUser1 = new LaborUser();
//        laborUser1.setLaborUserId("Alice Johnson");
//        laborUser1.setName("Alice Johnson");
//        laborUser1.setEmail("alice.johnson@example.com");
//        laborUser1.setPassword("password123");
//        laborUserRepository.save(laborUser1);
//
//        LaborUser laborUser2 = new LaborUser();
//        laborUser2.setLaborUserId("Bob Brown");
//        laborUser2.setName("Bob Brown");
//        laborUser2.setEmail("bob.brown@example.com");
//        laborUser2.setPassword("password123");
//        laborUserRepository.save(laborUser2);
//
//        // Review 더미 데이터 생성
//        Review review1 = new Review();
//        review1.setTitle("Tech Place to Work");
//        review1.setRating(5);
//        review1.setPromotion_rating(4);
//        review1.setSalary_rating(3);
//        review1.setBalance_rating(5);
//        review1.setCulture_rating(4);
//        review1.setManagement_rating(4);
//        review1.setPros("Great work-life balance and culture.");
//        review1.setCons("Salary could be higher.");
//        review1.setLike_count(10);
//        review1.setEnterprise(enterprise1);
//        review1.setLaborUser(laborUser1);
//        reviewRepository.save(review1);
//
//        Review review2 = new Review();
//        review2.setTitle("Challenging Environment");
//        review2.setRating(4);
//        review2.setPromotion_rating(3);
//        review2.setSalary_rating(4);
//        review2.setBalance_rating(3);
//        review2.setCulture_rating(4);
//        review2.setManagement_rating(3);
//        review2.setPros("Great learning opportunities.");
//        review2.setCons("Work-life balance can be challenging.");
//        review2.setLike_count(8);
//        review2.setEnterprise(enterprise2);
//        review2.setLaborUser(laborUser2);
//        reviewRepository.save(review2);
//    }
//}
