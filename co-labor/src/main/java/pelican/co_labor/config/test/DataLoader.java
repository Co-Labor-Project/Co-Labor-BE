package pelican.co_labor.config.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.repository.enterprise.EnterpriseRepository;
import pelican.co_labor.repository.enterprise_user.EnterpriseUserRepository;
import pelican.co_labor.repository.job.JobRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseUserRepository enterpriseUserRepository;
    private final JobRepository jobRepository;

    @Autowired
    public DataLoader(EnterpriseRepository enterpriseRepository, EnterpriseUserRepository enterpriseUserRepository, JobRepository jobRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.enterpriseUserRepository = enterpriseUserRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public void run(String... args) {
        createDummyEnterprises();
        createDummyEnterpriseUsers();
        createDummyJobs();
    }
    // 기업 더미데이터
    private void createDummyEnterprises() {
        createEnterprise("1018100132", "낙원상가(주)", "서울특별시", "종로구", "낙원동", "낙원상가 설명", "유통", "82-02-743-4200");
        createEnterprise("1018100205", "(주)대성합동지주", "서울특별시", "구로구", "경인로 662 (신도림동, 디큐브시티)", "대성합동지주 설명", "건설", "02-2170-2164");
        createEnterprise("1018100210", "대왕산업(주)", "서울특별시", "종로구", "우정국로2길 21 (관철동, 대왕빌딩)", "대왕산업 설명", "IT", "02-734-9188");
        createEnterprise("1018100277", "한아름금호관광주식회사", "서울특별시", "종로구", "관철동 252", "한아름금호관광 설명", "관광", "(02) 730-8805");
        createEnterprise("1018100340", "대일건설주식회사", "서울특별시", "종로구", "삼일대로 428", "대일건설 설명", "건설", "02)743-6131");
        createEnterprise("1018100452", "삼환기업(주)", "서울특별시", "강남구", "언주로 547 (역삼동)13 층 삼환기업(주)", "삼환기업 설명", "제조", "02-740-2391");
        createEnterprise("1018100700", "(주)단성사", "서울특별시", "종로구", "묘동 56", "단성사 설명", "서비스", "02-764-3745");
        createEnterprise("1018100729", "주식회사 피카디리극장", "서울특별시", "종로구", "돈의동 139번지", "피카디리극장 설명", "서비스", "02-501-5933");
        createEnterprise("1018100772", "(주)쎈추럴관광호텔", "서울특별시", "종로구", "장사동 227-1(48-3)", "쎈추럴관광호텔 설명", "관광", "02-2265-4120");
        createEnterprise("1018101107", "주식회사 축복할렐루야", "서울특별시", "종로구", "장사동 156-1", "축복할렐루야 설명", "종교", "(02)2276-1881");
        createEnterprise("1018101126", "우성개발(주)", "서울특별시", "종로구", "운니동 가든타워 98-78", "우성개발 설명", "건설", "02-765-3472");
        createEnterprise("1018101242", "주식회사 코아토탈시스템", "서울특별시", "종로구", "관철동 33-1", "코아토탈시스템 설명", "IT", "730-8196");
        createEnterprise("1018101354", "인주이앤이(주)", "서울특별시", "중구", "퇴계로18길 14 (남산동1가)", "인주이앤이 설명", "제조", "02-771-2250");
        createEnterprise("1018102940", "(주)모토닉", "서울특별시", "중구", "청계천로 100 동관 9층(수표동, 시그니쳐타워)", "모토닉 설명", "IT", "053-589-6262");
        createEnterprise("1018103406", "서울동방관광주식회사", "서울특별시", "종로구", "경운동 70", "서울동방관광 설명", "관광", "02-730-9004");
        createEnterprise("1018103518", "경진상역(주)", "서울특별시", "종로구", "관철동", "경진상역 설명", "유통", "82-02-2265-7284");
        createEnterprise("1018103594", "금용물산(주)", "서울특별시", "종로구", "관수동", "금용물산 설명", "유통", "82-02-2265-7490");
        createEnterprise("1058133940", "(주)대농", "서울특별시", "종로구", "종로1가", "대농 설명", "농업", "02-1234-5678");
        createEnterprise("2028143564", "베스트롱산업(주)", "서울특별시", "종로구", "종로2가", "베스트롱산업 설명", "제조", "02-8765-4321");
        createEnterprise("3038107177", "크로바하이텍(주)", "서울특별시", "종로구", "종로3가", "크로바하이텍 설명", "IT", "02-2468-1357");
        createEnterprise("3158120251", "한국아프라이드매그네틱스(주)", "서울특별시", "종로구", "종로4가", "한국아프라이드매그네틱스 설명", "전자", "02-1357-2468");
        createEnterprise("3158102011", "코스모촉매주식회사", "서울특별시", "종로구", "종로5가", "코스모촉매 설명", "화학", "02-2468-1357");
        createEnterprise("3158102480", "유현건설주식회사", "서울특별시", "종로구", "종로6가", "유현건설 설명", "건설", "02-5678-1234");
        createEnterprise("3158103757", "롯데네슬레코리아주식회사", "서울특별시", "종로구", "종로7가", "롯데네슬레코리아 설명", "식품", "02-8765-4321");
        createEnterprise("3158105416", "(주)킹텍스", "서울특별시", "종로구", "종로8가", "킹텍스 설명", "섬유", "02-1357-2468");
        createEnterprise("5028115309", "주식회사학산", "서울특별시", "종로구", "종로9가", "학산 설명", "출판", "02-5678-1234");
    }

    private void createEnterprise(String enterpriseId, String name, String address1, String address2, String address3, String description, String type, String phoneNumber) {
        Enterprise enterprise = new Enterprise();
        enterprise.setEnterprise_id(enterpriseId);
        enterprise.setName(name);
        enterprise.setAddress1(address1);
        enterprise.setAddress2(address2);
        enterprise.setAddress3(address3);
        enterprise.setDescription(description);
        enterprise.setType(type);
        enterprise.setPhone_number(phoneNumber);
        enterpriseRepository.save(enterprise);
    }

    // 기업 유저 더미데이터
    private void createDummyEnterpriseUsers() {
        createEnterpriseUser("moonki.kim@example.com", "김문기", "password1", enterpriseRepository.findById("1018100132").orElse(null));
        createEnterpriseUser("hanul.jung@example.com", "정한울", "password2", enterpriseRepository.findById("1018100205").orElse(null));
        createEnterpriseUser("junhwa.cho@example.com", "조준화", "password3", enterpriseRepository.findById("1018100210").orElse(null));
        createEnterpriseUser("dohyun.kim@example.com", "김도현", "password4", enterpriseRepository.findById("1018100277").orElse(null));
        createEnterpriseUser("jihoon.park@example.com", "박지훈", "password5", enterpriseRepository.findById("1018100340").orElse(null));
        createEnterpriseUser("younghee.lee@example.com", "이영희", "password6", enterpriseRepository.findById("1018100452").orElse(null));
        createEnterpriseUser("minsu.choi@example.com", "최민수", "password7", enterpriseRepository.findById("1018100700").orElse(null));
        createEnterpriseUser("minho.jang@example.com", "장민호", "password8", enterpriseRepository.findById("1018100729").orElse(null));
        createEnterpriseUser("jimin.han@example.com", "한지민", "password9", enterpriseRepository.findById("1018100772").orElse(null));
        createEnterpriseUser("sumin.park@example.com", "박수민", "password10", enterpriseRepository.findById("1018101107").orElse(null));
        createEnterpriseUser("james.smith@example.com", "James Smith", "password11", enterpriseRepository.findById("1018101126").orElse(null));
        createEnterpriseUser("emma.johnson@example.com", "Emma Johnson", "password12", enterpriseRepository.findById("1018101242").orElse(null));
        createEnterpriseUser("michael.brown@example.com", "Michael Brown", "password13", enterpriseRepository.findById("1018101354").orElse(null));
        createEnterpriseUser("david.wilson@example.com", "David Wilson", "password14", enterpriseRepository.findById("1018102940").orElse(null));
        createEnterpriseUser("john.taylor@example.com", "John Taylor", "password15", enterpriseRepository.findById("1018103406").orElse(null));
        createEnterpriseUser("sophie.lee@example.com", "Sophie Lee", "password16", enterpriseRepository.findById("1018103518").orElse(null));
        createEnterpriseUser("daniel.harris@example.com", "Daniel Harris", "password17", enterpriseRepository.findById("1018103594").orElse(null));
        createEnterpriseUser("emily.clark@example.com", "Emily Clark", "password18", enterpriseRepository.findById("1058133940").orElse(null));
        createEnterpriseUser("olivia.lewis@example.com", "Olivia Lewis", "password19", enterpriseRepository.findById("2028143564").orElse(null));
        createEnterpriseUser("lucas.young@example.com", "Lucas Young", "password20", enterpriseRepository.findById("3038107177").orElse(null));
        createEnterpriseUser("alex.kim@example.com", "Alex Kim", "password21", enterpriseRepository.findById("3158120251").orElse(null));
        createEnterpriseUser("bella.martinez@example.com", "Bella Martinez", "password22", enterpriseRepository.findById("3158102011").orElse(null));
        createEnterpriseUser("chris.evans@example.com", "Chris Evans", "password23", enterpriseRepository.findById("3158102480").orElse(null));
        createEnterpriseUser("daniel.parker@example.com", "Daniel Parker", "password24", enterpriseRepository.findById("3158103757").orElse(null));
        createEnterpriseUser("ethan.lee@example.com", "Ethan Lee", "password25", enterpriseRepository.findById("3158105416").orElse(null));
        createEnterpriseUser("fiona.green@example.com", "Fiona Green", "password26", enterpriseRepository.findById("5028115309").orElse(null));
        createEnterpriseUser("george.king@example.com", "George King", "password27", enterpriseRepository.findById("1018100132").orElse(null));
        createEnterpriseUser("henry.white@example.com", "Henry White", "password28", enterpriseRepository.findById("1018100205").orElse(null));
        createEnterpriseUser("ivy.thomas@example.com", "Ivy Thomas", "password29", enterpriseRepository.findById("1018100210").orElse(null));
        createEnterpriseUser("jack.davis@example.com", "Jack Davis", "password30", enterpriseRepository.findById("1018100277").orElse(null));
    }

    private void createEnterpriseUser(String email, String name, String password, Enterprise enterprise) {
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        enterpriseUser.setEnterprise_user_id(UUID.randomUUID().toString());
        enterpriseUser.setEmail(email);
        enterpriseUser.setName(name);
        enterpriseUser.setPassword(password);
        enterpriseUser.setEnterprise(enterprise);
        enterpriseUser.setCreated_at(LocalDateTime.now());
        enterpriseUserRepository.save(enterpriseUser);
    }

    // 채용공고 더미데이터
    private void createDummyJobs() {
        Enterprise enterprise1 = enterpriseRepository.findById("1018100132").orElse(null);
        Enterprise enterprise2 = enterpriseRepository.findById("1018100205").orElse(null);
        Enterprise enterprise3 = enterpriseRepository.findById("1018100210").orElse(null);
        Enterprise enterprise4 = enterpriseRepository.findById("1018100277").orElse(null);
        Enterprise enterprise5 = enterpriseRepository.findById("1018100340").orElse(null);
        EnterpriseUser enterpriseUser1 = enterpriseUserRepository.findByEnterprise(enterprise1).get(0);
        EnterpriseUser enterpriseUser2 = enterpriseUserRepository.findByEnterprise(enterprise2).get(0);
        EnterpriseUser enterpriseUser3 = enterpriseUserRepository.findByEnterprise(enterprise3).get(0);
        EnterpriseUser enterpriseUser4 = enterpriseUserRepository.findByEnterprise(enterprise4).get(0);
        EnterpriseUser enterpriseUser5 = enterpriseUserRepository.findByEnterprise(enterprise5).get(0);

        Job job1 = new Job();
        job1.setTitle("물류 관리자");
        job1.setDescription("물류 센터 운영 및 관리.\n업무 내용: 재고 관리, 출고 관리, 물류 프로세스 개선.\n자격 요건: 물류 관리 경험 3년 이상, 물류 시스템 이해.\n우대 사항: 물류 관련 자격증 소지자.\n복지: 건강 검진, 연간 성과 보너스.");
        job1.setViews(100);
        job1.setDeadDate(LocalDate.now().plusDays(30));
        job1.setEnterprise(enterprise1);
        job1.setEnterpriseUser(enterpriseUser1);
        job1.setJobRole("Logistics Management");
        job1.setExperience("3년 이상");
        job1.setEmploymentType("정규직");
        job1.setLocation("서울특별시 종로구 낙원동");
        job1.setSkills("재고 관리, 물류 시스템, 물류 프로세스 개선");
        jobRepository.save(job1);

        Job job2 = new Job();
        job2.setTitle("데이터 과학자");
        job2.setDescription("분석 및 복잡한 데이터 세트를 해석합니다.\n[조직 소개]\n우리 팀은 다양한 산업의 데이터를 분석하여 유의미한 인사이트를 도출하고, 데이터 기반 의사결정을 지원합니다.\n[핵심업무]\n∙ 대규모 데이터 세트를 분석 및 시각화\n∙ 머신러닝 모델 개발 및 최적화\n∙ 데이터 기반 전략 수립 및 실행\n자격 요건\n∙ 데이터 분석 및 통계학에 대한 깊은 이해\n∙ Python, R 등 데이터 분석 도구 사용 경험\n∙ 머신러닝 모델 개발 경험\n우대사항\n∙ 대규모 데이터 분석 경험\n∙ 클라우드 환경에서의 데이터 작업 경험\n∙ 데이터 시각화 도구 사용 능력\n복지 및 혜택\n∙ 유연 근무제\n∙ 건강 검진\n∙ 교육 및 자기계발 지원");
        job2.setViews(150);
        job2.setDeadDate(LocalDate.now().plusDays(45));
        job2.setEnterprise(enterprise2);
        job2.setEnterpriseUser(enterpriseUser2);
        job2.setJobRole("Data Science");
        job2.setExperience("3 ~ 7년");
        job2.setEmploymentType("정규직");
        job2.setLocation("서울");
        job2.setSkills("Python, R, 통계, 머신러닝, 빅데이터, 클라우드");
        jobRepository.save(job2);

        Job job3 = new Job();
        job3.setTitle("소프트웨어 엔지니어");
        job3.setDescription("소프트웨어 개발 및 유지보수.\n업무 내용: 소프트웨어 아키텍처 설계, 코드 작성 및 리뷰, 버그 수정.\n자격 요건: 소프트웨어 개발 경험 5년 이상, Java 및 Spring 이해.\n우대 사항: 대규모 시스템 개발 경험.\n복지: 연간 성과 보너스, 유연 근무제.");
        job3.setViews(200);
        job3.setDeadDate(LocalDate.now().plusDays(30));
        job3.setEnterprise(enterprise3);
        job3.setEnterpriseUser(enterpriseUser3);
        job3.setJobRole("Software Development");
        job3.setExperience("5년 이상");
        job3.setEmploymentType("정규직");
        job3.setLocation("서울특별시 종로구 우정국로2길");
        job3.setSkills("Java, Spring, 소프트웨어 아키텍처");
        jobRepository.save(job3);

        Job job4 = new Job();
        job4.setTitle("프로젝트 매니저");
        job4.setDescription("프로젝트 계획 수립 및 관리.\n업무 내용: 프로젝트 일정 관리, 팀 조정, 성과 보고.\n자격 요건: 프로젝트 관리 경험 7년 이상, PMP 자격증 소지자.\n우대 사항: IT 프로젝트 관리 경험.\n복지: 건강 검진, 연간 성과 보너스.");
        job4.setViews(120);
        job4.setDeadDate(LocalDate.now().plusDays(40));
        job4.setEnterprise(enterprise4);
        job4.setEnterpriseUser(enterpriseUser4);
        job4.setJobRole("Project Management");
        job4.setExperience("7년 이상");
        job4.setEmploymentType("정규직");
        job4.setLocation("서울특별시 종로구 관철동");
        job4.setSkills("프로젝트 일정 관리, 팀 조정, 성과 보고");
        jobRepository.save(job4);

        Job job5 = new Job();
        job5.setTitle("마케팅 매니저");
        job5.setDescription("마케팅 전략 수립 및 실행.\n업무 내용: 마케팅 캠페인 기획, 예산 관리, 성과 분석.\n자격 요건: 마케팅 경험 5년 이상, 디지털 마케팅 이해.\n우대 사항: 글로벌 마케팅 경험.\n복지: 유연 근무제, 연간 성과 보너스.");
        job5.setViews(130);
        job5.setDeadDate(LocalDate.now().plusDays(35));
        job5.setEnterprise(enterprise5);
        job5.setEnterpriseUser(enterpriseUser5);
        job5.setJobRole("Marketing Management");
        job5.setExperience("5년 이상");
        job5.setEmploymentType("정규직");
        job5.setLocation("서울특별시 종로구 삼일대로");
        job5.setSkills("마케팅 전략 수립, 예산 관리, 성과 분석");
        jobRepository.save(job5);

        Job job6 = new Job();
        job6.setTitle("물류 관리자");
        job6.setDescription("물류 센터 운영 및 관리.\n업무 내용: 재고 관리, 출고 관리, 물류 프로세스 개선.\n자격 요건: 물류 관리 경험 3년 이상, 물류 시스템 이해.\n우대 사항: 물류 관련 자격증 소지자.\n복지: 건강 검진, 연간 성과 보너스.");
        job6.setViews(100);
        job6.setDeadDate(LocalDate.now().plusDays(30));
        job6.setEnterprise(enterprise1);
        job6.setEnterpriseUser(enterpriseUser1);
        job6.setJobRole("Logistics Management");
        job6.setExperience("3년 이상");
        job6.setEmploymentType("정규직");
        job6.setLocation("서울특별시 종로구 낙원동");
        job6.setSkills("재고 관리, 물류 시스템, 물류 프로세스 개선");
        jobRepository.save(job6);
    }
}


