//package pelican.co_labor.config.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import pelican.co_labor.domain.enterprise.Enterprise;
//import pelican.co_labor.domain.enterprise.EnterpriseEng;
//import pelican.co_labor.domain.enterprise_user.EnterpriseUser;
//import pelican.co_labor.domain.job.Job;
//import pelican.co_labor.domain.job.JobEng;
//import pelican.co_labor.repository.enterprise.EnterpriseEngRepository;
//import pelican.co_labor.repository.enterprise.EnterpriseRepository;
//import pelican.co_labor.repository.enterprise_user.EnterpriseUserRepository;
//import pelican.co_labor.repository.job.JobEngRepository;
//import pelican.co_labor.repository.job.JobRepository;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final EnterpriseRepository enterpriseRepository;
//    private final EnterpriseUserRepository enterpriseUserRepository;
//    private final JobRepository jobRepository;
//    private final EnterpriseEngRepository enterpriseEngRepository;
//    private final JobEngRepository jobEngRepository;
//
//    @Autowired
//    public DataLoader(EnterpriseRepository enterpriseRepository, EnterpriseUserRepository enterpriseUserRepository, JobRepository jobRepository, EnterpriseEngRepository enterpriseEngRepository, JobEngRepository jobEngRepository) {
//        this.enterpriseRepository = enterpriseRepository;
//        this.enterpriseUserRepository = enterpriseUserRepository;
//        this.jobRepository = jobRepository;
//        this.enterpriseEngRepository = enterpriseEngRepository;
//        this.jobEngRepository = jobEngRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        createDummyEnterprises();
//        createDummyEnterprisesEng();
//        createDummyEnterpriseUsers();
//        createDummyJobs();
//        createDummyJobsEng();
//    }
//
//    // 기업 더미데이터 (영문)
//    private void createDummyEnterprisesEng() {
//        createEnterpriseEng("1018100132", "Nakwon Sangga Co., Ltd.", "Seoul Special City", "Jongno-gu", "Nakwon-dong", "Nakwon Sangga description", "Distribution", "82-02-743-4200");
//        createEnterpriseEng("1018100205", "Daesung Joint Holdings Co., Ltd.", "Seoul Special City", "Guro-gu", "Gyeongin-ro 662 (Sindorim-dong, D-Cube City)", "Daesung Joint Holdings description", "Construction", "02-2170-2164");
//        createEnterpriseEng("1018100210", "Daewang Industry Co., Ltd.", "Seoul Special City", "Jongno-gu", "Ujeongguk-ro 2-gil 21 (Gwancheol-dong, Daewang Building)", "Daewang Industry description", "IT", "02-734-9188");
//        createEnterpriseEng("1018100277", "Hanahreum Kumho Tourism Co., Ltd.", "Seoul Special City", "Jongno-gu", "252 Gwancheol-dong", "Hanahreum Kumho Tourism description", "Tourism", "(02) 730-8805");
//        createEnterpriseEng("1018100340", "Daeil Construction Co., Ltd.", "Seoul Special City", "Jongno-gu", "428 Samil-daero", "Daeil Construction description", "Construction", "02-743-6131");
//        createEnterpriseEng("1018100452", "Samwhan Corporation", "Seoul Special City", "Gangnam-gu", "Eonju-ro 547 (Yeoksam-dong) 13th Floor, Samwhan Corporation", "Samwhan Corporation description", "Manufacturing", "02-740-2391");
//        createEnterpriseEng("1018100700", "Daeho Construction Co., Ltd.", "Seoul Special City", "Jongno-gu", "56 Myo-dong", "Daeho Construction description", "Service", "02-764-3745");
//        createEnterpriseEng("1018100729", "Piccadilly Theater Co., Ltd.", "Seoul Special City", "Jongno-gu", "139 Donui-dong", "Piccadilly Theater description", "Service", "02-501-5933");
//        createEnterpriseEng("1018100772", "Central Tourism Hotel Co., Ltd.", "Seoul Special City", "Jongno-gu", "227-1(48-3) Jangsa-dong", "Central Tourism Hotel description", "Tourism", "02-2265-4120");
//        createEnterpriseEng("1018101107", "Blessing Hallelujah Co., Ltd.", "Seoul Special City", "Jongno-gu", "156-1 Jangsa-dong", "Blessing Hallelujah description", "Religion", "(02)2276-1881");
//        createEnterpriseEng("1018101126", "Woseong Development Co., Ltd.", "Seoul Special City", "Jongno-gu", "98-78 Unni-dong, Garden Tower", "Woseong Development description", "Construction", "02-765-3472");
//        createEnterpriseEng("1018101242", "Coa Total System Co., Ltd.", "Seoul Special City", "Jongno-gu", "33-1 Gwancheol-dong", "Coa Total System description", "IT", "730-8196");
//        createEnterpriseEng("1018101354", "Inju E&E Co., Ltd.", "Seoul Special City", "Jung-gu", "14 Toegye-ro 18-gil (Namsan-dong 1-ga)", "Inju E&E description", "Manufacturing", "02-771-2250");
//        createEnterpriseEng("1018102940", "Motonic Co., Ltd.", "Seoul Special City", "Jung-gu", "100 Cheonggyecheon-ro, Donggwan 9th Floor (Supyo-dong, Signature Tower)", "Motonic description", "IT", "053-589-6262");
//        createEnterpriseEng("1018103406", "Seoul Dongbang Tourism Co., Ltd.", "Seoul Special City", "Jongno-gu", "70 Gyeongun-dong", "Seoul Dongbang Tourism description", "Tourism", "02-730-9004");
//        createEnterpriseEng("1018103518", "Kyungjin Trading Co., Ltd.", "Seoul Special City", "Jongno-gu", "Gwancheol-dong", "Kyungjin Trading description", "Distribution", "82-02-2265-7284");
//        createEnterpriseEng("1018103594", "Geumyong Trading Co., Ltd.", "Seoul Special City", "Jongno-gu", "Gwansoo-dong", "Geumyong Trading description", "Distribution", "82-02-2265-7490");
//        createEnterpriseEng("1058133940", "Dae-Nong Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 1-ga", "Dae-Nong description", "Agriculture", "02-1234-5678");
//        createEnterpriseEng("2028143564", "Best Long Industry Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 2-ga", "Best Long Industry description", "Manufacturing", "02-8765-4321");
//        createEnterpriseEng("3038107177", "Clover Hi-Tech Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 3-ga", "Clover Hi-Tech description", "IT", "02-2468-1357");
//        createEnterpriseEng("3158120251", "Korea Applied Magnetics Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 4-ga", "Korea Applied Magnetics description", "Electronics", "02-1357-2468");
//        createEnterpriseEng("3158102011", "Cosmo Catalyst Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 5-ga", "Cosmo Catalyst description", "Chemicals", "02-2468-1357");
//        createEnterpriseEng("3158102480", "Yuhyun Construction Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 6-ga", "Yuhyun Construction description", "Construction", "02-5678-1234");
//        createEnterpriseEng("3158103757", "Lotte Nestle Korea Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 7-ga", "Lotte Nestle Korea description", "Food", "02-8765-4321");
//        createEnterpriseEng("3158105416", "Kingtex Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 8-ga", "Kingtex description", "Textiles", "02-1357-2468");
//        createEnterpriseEng("5028115309", "Haksan Co., Ltd.", "Seoul Special City", "Jongno-gu", "Jongno 9-ga", "Haksan description", "Publishing", "02-5678-1234");
//    }
//
//    private void createEnterpriseEng(String enterpriseId, String name, String address1, String address2, String address3, String description, String type, String phoneNumber) {
//        EnterpriseEng enterprise = new EnterpriseEng();
//        enterprise.setEnterprise_id(enterpriseId);
//        enterprise.setName(name);
//        enterprise.setAddress1(address1);
//        enterprise.setAddress2(address2);
//        enterprise.setAddress3(address3);
//        enterprise.setDescription(description);
//        enterprise.setType(type);
//        enterprise.setPhone_number(phoneNumber);
//        enterpriseEngRepository.save(enterprise);
//    }
//
//    // 기업 더미데이터
//    private void createDummyEnterprises() {
//        createEnterprise("1018100132", "낙원상가(주)", "서울특별시", "종로구", "낙원동", "낙원상가 설명", "유통", "82-02-743-4200");
//        createEnterprise("1018100205", "(주)대성합동지주", "서울특별시", "구로구", "경인로 662 (신도림동, 디큐브시티)", "대성합동지주 설명", "건설", "02-2170-2164");
//        createEnterprise("1018100210", "대왕산업(주)", "서울특별시", "종로구", "우정국로2길 21 (관철동, 대왕빌딩)", "대왕산업 설명", "IT", "02-734-9188");
//        createEnterprise("1018100277", "한아름금호관광주식회사", "서울특별시", "종로구", "관철동 252", "한아름금호관광 설명", "관광", "(02) 730-8805");
//        createEnterprise("1018100340", "대일건설주식회사", "서울특별시", "종로구", "삼일대로 428", "대일건설 설명", "건설", "02)743-6131");
//        createEnterprise("1018100452", "삼환기업(주)", "서울특별시", "강남구", "언주로 547 (역삼동)13 층 삼환기업(주)", "삼환기업 설명", "제조", "02-740-2391");
//        createEnterprise("1018100700", "(주)단성사", "서울특별시", "종로구", "묘동 56", "단성사 설명", "서비스", "02-764-3745");
//        createEnterprise("1018100729", "주식회사 피카디리극장", "서울특별시", "종로구", "돈의동 139번지", "피카디리극장 설명", "서비스", "02-501-5933");
//        createEnterprise("1018100772", "(주)쎈추럴관광호텔", "서울특별시", "종로구", "장사동 227-1(48-3)", "쎈추럴관광호텔 설명", "관광", "02-2265-4120");
//        createEnterprise("1018101107", "주식회사 축복할렐루야", "서울특별시", "종로구", "장사동 156-1", "축복할렐루야 설명", "종교", "(02)2276-1881");
//        createEnterprise("1018101126", "우성개발(주)", "서울특별시", "종로구", "운니동 가든타워 98-78", "우성개발 설명", "건설", "02-765-3472");
//        createEnterprise("1018101242", "주식회사 코아토탈시스템", "서울특별시", "종로구", "관철동 33-1", "코아토탈시스템 설명", "IT", "730-8196");
//        createEnterprise("1018101354", "인주이앤이(주)", "서울특별시", "중구", "퇴계로18길 14 (남산동1가)", "인주이앤이 설명", "제조", "02-771-2250");
//        createEnterprise("1018102940", "(주)모토닉", "서울특별시", "중구", "청계천로 100 동관 9층(수표동, 시그니쳐타워)", "모토닉 설명", "IT", "053-589-6262");
//        createEnterprise("1018103406", "서울동방관광주식회사", "서울특별시", "종로구", "경운동 70", "서울동방관광 설명", "관광", "02-730-9004");
//        createEnterprise("1018103518", "경진상역(주)", "서울특별시", "종로구", "관철동", "경진상역 설명", "유통", "82-02-2265-7284");
//        createEnterprise("1018103594", "금용물산(주)", "서울특별시", "종로구", "관수동", "금용물산 설명", "유통", "82-02-2265-7490");
//        createEnterprise("1058133940", "(주)대농", "서울특별시", "종로구", "종로1가", "대농 설명", "농업", "02-1234-5678");
//        createEnterprise("2028143564", "베스트롱산업(주)", "서울특별시", "종로구", "종로2가", "베스트롱산업 설명", "제조", "02-8765-4321");
//        createEnterprise("3038107177", "크로바하이텍(주)", "서울특별시", "종로구", "종로3가", "크로바하이텍 설명", "IT", "02-2468-1357");
//        createEnterprise("3158120251", "한국아프라이드매그네틱스(주)", "서울특별시", "종로구", "종로4가", "한국아프라이드매그네틱스 설명", "전자", "02-1357-2468");
//        createEnterprise("3158102011", "코스모촉매주식회사", "서울특별시", "종로구", "종로5가", "코스모촉매 설명", "화학", "02-2468-1357");
//        createEnterprise("3158102480", "유현건설주식회사", "서울특별시", "종로구", "종로6가", "유현건설 설명", "건설", "02-5678-1234");
//        createEnterprise("3158103757", "롯데네슬레코리아주식회사", "서울특별시", "종로구", "종로7가", "롯데네슬레코리아 설명", "식품", "02-8765-4321");
//        createEnterprise("3158105416", "(주)킹텍스", "서울특별시", "종로구", "종로8가", "킹텍스 설명", "섬유", "02-1357-2468");
//        createEnterprise("5028115309", "주식회사학산", "서울특별시", "종로구", "종로9가", "학산 설명", "출판", "02-5678-1234");
//    }
//
//    private void createEnterprise(String enterpriseId, String name, String address1, String address2, String address3, String description, String type, String phoneNumber) {
//        Enterprise enterprise = new Enterprise();
//        enterprise.setEnterprise_id(enterpriseId);
//        enterprise.setName(name);
//        enterprise.setAddress1(address1);
//        enterprise.setAddress2(address2);
//        enterprise.setAddress3(address3);
//        enterprise.setDescription(description);
//        enterprise.setType(type);
//        enterprise.setPhone_number(phoneNumber);
//        enterpriseRepository.save(enterprise);
//    }
//
//    // 기업 유저 더미데이터
//    private void createDummyEnterpriseUsers() {
//        createEnterpriseUser("moonki.kim@example.com", "김문기", "password1", enterpriseRepository.findById("1018100132").orElse(null), enterpriseEngRepository.findById("1018100132").orElse(null));
//        createEnterpriseUser("hanul.jung@example.com", "정한울", "password2", enterpriseRepository.findById("1018100205").orElse(null), enterpriseEngRepository.findById("1018100205").orElse(null));
//        createEnterpriseUser("junhwa.cho@example.com", "조준화", "password3", enterpriseRepository.findById("1018100210").orElse(null), enterpriseEngRepository.findById("1018100210").orElse(null));
//        createEnterpriseUser("dohyun.kim@example.com", "김도현", "password4", enterpriseRepository.findById("1018100277").orElse(null), enterpriseEngRepository.findById("1018100277").orElse(null));
//        createEnterpriseUser("jihoon.park@example.com", "박지훈", "password5", enterpriseRepository.findById("1018100340").orElse(null), enterpriseEngRepository.findById("1018100340").orElse(null));
//        createEnterpriseUser("younghee.lee@example.com", "이영희", "password6", enterpriseRepository.findById("1018100452").orElse(null), enterpriseEngRepository.findById("1018100452").orElse(null));
//        createEnterpriseUser("minsu.choi@example.com", "최민수", "password7", enterpriseRepository.findById("1018100700").orElse(null), enterpriseEngRepository.findById("1018100700").orElse(null));
//        createEnterpriseUser("minho.jang@example.com", "장민호", "password8", enterpriseRepository.findById("1018100729").orElse(null), enterpriseEngRepository.findById("1018100729").orElse(null));
//        createEnterpriseUser("jimin.han@example.com", "한지민", "password9", enterpriseRepository.findById("1018100772").orElse(null), enterpriseEngRepository.findById("1018100772").orElse(null));
//        createEnterpriseUser("sumin.park@example.com", "박수민", "password10", enterpriseRepository.findById("1018101107").orElse(null), enterpriseEngRepository.findById("1018101107").orElse(null));
//        createEnterpriseUser("james.smith@example.com", "James Smith", "password11", enterpriseRepository.findById("1018101126").orElse(null), enterpriseEngRepository.findById("1018101126").orElse(null));
//        createEnterpriseUser("emma.johnson@example.com", "Emma Johnson", "password12", enterpriseRepository.findById("1018101242").orElse(null), enterpriseEngRepository.findById("1018101242").orElse(null));
//        createEnterpriseUser("michael.brown@example.com", "Michael Brown", "password13", enterpriseRepository.findById("1018101354").orElse(null), enterpriseEngRepository.findById("1018101354").orElse(null));
//        createEnterpriseUser("david.wilson@example.com", "David Wilson", "password14", enterpriseRepository.findById("1018102940").orElse(null), enterpriseEngRepository.findById("1018102940").orElse(null));
//        createEnterpriseUser("john.taylor@example.com", "John Taylor", "password15", enterpriseRepository.findById("1018103406").orElse(null), enterpriseEngRepository.findById("1018103406").orElse(null));
//        createEnterpriseUser("sophie.lee@example.com", "Sophie Lee", "password16", enterpriseRepository.findById("1018103518").orElse(null), enterpriseEngRepository.findById("1018103518").orElse(null));
//        createEnterpriseUser("daniel.harris@example.com", "Daniel Harris", "password17", enterpriseRepository.findById("1018103594").orElse(null), enterpriseEngRepository.findById("1018103594").orElse(null));
//        createEnterpriseUser("emily.clark@example.com", "Emily Clark", "password18", enterpriseRepository.findById("1058133940").orElse(null), enterpriseEngRepository.findById("1058133940").orElse(null));
//        createEnterpriseUser("olivia.lewis@example.com", "Olivia Lewis", "password19", enterpriseRepository.findById("2028143564").orElse(null), enterpriseEngRepository.findById("2028143564").orElse(null));
//        createEnterpriseUser("lucas.young@example.com", "Lucas Young", "password20", enterpriseRepository.findById("3038107177").orElse(null), enterpriseEngRepository.findById("3038107177").orElse(null));
//        createEnterpriseUser("alex.kim@example.com", "Alex Kim", "password21", enterpriseRepository.findById("3158120251").orElse(null), enterpriseEngRepository.findById("3158120251").orElse(null));
//        createEnterpriseUser("bella.martinez@example.com", "Bella Martinez", "password22", enterpriseRepository.findById("3158102011").orElse(null), enterpriseEngRepository.findById("3158102011").orElse(null));
//        createEnterpriseUser("chris.evans@example.com", "Chris Evans", "password23", enterpriseRepository.findById("3158102480").orElse(null), enterpriseEngRepository.findById("3158102480").orElse(null));
//        createEnterpriseUser("daniel.parker@example.com", "Daniel Parker", "password24", enterpriseRepository.findById("3158103757").orElse(null), enterpriseEngRepository.findById("3158103757").orElse(null));
//        createEnterpriseUser("ethan.lee@example.com", "Ethan Lee", "password25", enterpriseRepository.findById("3158105416").orElse(null), enterpriseEngRepository.findById("3158105416").orElse(null));
//        createEnterpriseUser("fiona.green@example.com", "Fiona Green", "password26", enterpriseRepository.findById("5028115309").orElse(null), enterpriseEngRepository.findById("5028115309").orElse(null));
//        createEnterpriseUser("george.king@example.com", "George King", "password27", enterpriseRepository.findById("1018100132").orElse(null), enterpriseEngRepository.findById("1018100132").orElse(null));
//        createEnterpriseUser("henry.white@example.com", "Henry White", "password28", enterpriseRepository.findById("1018100205").orElse(null), enterpriseEngRepository.findById("1018100205").orElse(null));
//        createEnterpriseUser("ivy.thomas@example.com", "Ivy Thomas", "password29", enterpriseRepository.findById("1018100210").orElse(null), enterpriseEngRepository.findById("1018100210").orElse(null));
//        createEnterpriseUser("jack.davis@example.com", "Jack Davis", "password30", enterpriseRepository.findById("1018100277").orElse(null), enterpriseEngRepository.findById("1018100277").orElse(null));
//    }
//
//    private void createEnterpriseUser(String email, String name, String password, Enterprise enterprise, EnterpriseEng enterpriseEng) {
//        EnterpriseUser enterpriseUser = new EnterpriseUser();
//        enterpriseUser.setEnterprise_user_id(UUID.randomUUID().toString());
//        enterpriseUser.setEmail(email);
//        enterpriseUser.setName(name);
//        enterpriseUser.setPassword(password);
//        enterpriseUser.setEnterprise(enterprise);
//        enterpriseUser.setEnterpriseEng(enterpriseEng);
//        enterpriseUser.setCreated_at(LocalDateTime.now());
//        enterpriseUserRepository.save(enterpriseUser);
//    }
//
//    // 채용공고 더미데이터 (영문)
//    private void createDummyJobsEng() {
//        EnterpriseEng enterprise1 = enterpriseEngRepository.findById("1018100132").orElse(null);
//        EnterpriseEng enterprise2 = enterpriseEngRepository.findById("1018100205").orElse(null);
//        EnterpriseEng enterprise3 = enterpriseEngRepository.findById("1018100210").orElse(null);
//        EnterpriseEng enterprise4 = enterpriseEngRepository.findById("1018100277").orElse(null);
//        EnterpriseEng enterprise5 = enterpriseEngRepository.findById("1018100340").orElse(null);
//        EnterpriseUser enterpriseUser1 = enterpriseUserRepository.findByEnterpriseEng(enterprise1).get(0);
//        EnterpriseUser enterpriseUser2 = enterpriseUserRepository.findByEnterpriseEng(enterprise2).get(0);
//        EnterpriseUser enterpriseUser3 = enterpriseUserRepository.findByEnterpriseEng(enterprise3).get(0);
//        EnterpriseUser enterpriseUser4 = enterpriseUserRepository.findByEnterpriseEng(enterprise4).get(0);
//        EnterpriseUser enterpriseUser5 = enterpriseUserRepository.findByEnterpriseEng(enterprise5).get(0);
//
//        JobEng job1 = new JobEng();
//        job1.setTitle("Logistics Manager");
//        job1.setDescription("Logistics Manager\n" +
//                "\n" +
//                "Key Responsibilities:\n" +
//                "The Logistics Manager is responsible for smooth operation and management of the logistics center, handling inventory management, shipment management, and improvement of logistics processes. Our goal is to enhance customer satisfaction through efficient logistics operations and maximize operational efficiency along with cost reduction.\n" +
//                "\n" +
//                "Responsibilities:\n" +
//                "\n" +
//                "Inventory Management:\n" +
//                "\n" +
//                "Continuous monitoring and optimization of inventory levels.\n" +
//                "Conduct periodic inventory checks and audits to maintain inventory accuracy.\n" +
//                "Optimize the movement and placement of inventory within the warehouse.\n" +
//                "Shipment Management:\n" +
//                "\n" +
//                "Accurate processing and prompt shipment of customer orders.\n" +
//                "Address and resolve issues during the shipment process.\n" +
//                "Plan shipping schedules and routes using logistics systems.\n" +
//                "Process Improvement:\n" +
//                "\n" +
//                "Analyze current logistics processes and suggest improvements.\n" +
//                "Implement new technologies and optimize logistics systems to increase operational efficiency.\n" +
//                "Maintain smooth communication and relationships with suppliers.\n" +
//                "Qualifications:\n" +
//                "\n" +
//                "Over 3 years of experience in logistics management.\n" +
//                "Deep understanding of logistics systems and processes.\n" +
//                "Ability to analyze logistics data and prepare reports.\n" +
//                "Strong problem-solving skills and excellent communication skills.\n" +
//                "Experience in team collaboration and project management.\n" +
//                "Preferred Qualifications:\n" +
//                "\n" +
//                "Certification in logistics (e.g., Logistics Manager, International Logistics Certification, etc.).\n" +
//                "Experience in various logistics environments in large enterprises or startups.\n" +
//                "Experience in logistics automation and new technology adoption.\n" +
//                "Interest in the fitness and sports industry.\n" +
//                "Benefits:\n" +
//                "\n" +
//                "Work Environment:\n" +
//                "\n" +
//                "OKR-based goal setting:\n" +
//                "Maximize job performance through clear goal setting.\n" +
//                "Performance review system through CFR:\n" +
//                "Support individual and team growth through regular performance reviews.\n" +
//                "Growth feedback through peer feedback each quarter:\n" +
//                "Promote mutual development through team feedback.\n" +
//                "Salary increase twice a year and additional compensation for outliers:\n" +
//                "Provide additional compensation for outstanding performers.\n" +
//                "Reflecting performance through occasional salary claims:\n" +
//                "Operate a reward system based on achievements.\n" +
//                "Welfare Benefits:\n" +
//                "\n" +
//                "Warm team:\n" +
//                "Family discount coupons issued.\n" +
//                "Health check-up cost support.\n" +
//                "Awards for long-term employees (1/3/5 years).\n" +
//                "Smart team:\n" +
//                "Support for education and book purchases.\n" +
//                "Structured onboarding training.\n" +
//                "The best welfare is the Buffett Seoul colleague → introduce a member.\n" +
//                "Sports team:\n" +
//                "Free participation in team buffet training.\n" +
//                "Free use of the Buffet Ground branch.\n" +
//                "Discounts on 1:1 PT and exercise products from our partners (golf/pilates/yoga, etc.).\n" +
//                "Recruitment Process:\n" +
//                "\n" +
//                "Simple application:\n" +
//                "Submit your resume (portfolio is optional).\n" +
//                "Request for written interview.\n" +
//                "First interview:\n" +
//                "Interview to assess job competence (conversation with team lead and practitioners).\n" +
//                "Takes about 40 to 70 minutes.\n" +
//                "Second interview:\n" +
//                "Verify cultural fit (conversation with C-level and executives).\n" +
//                "Takes about 40 to 70 minutes.\n" +
//                "Final acceptance:\n" +
//                "Final compensation negotiation and onboarding upon passing the second interview.");
//        job1.setViews(100);
//        job1.setDeadDate(LocalDate.now().plusDays(30));
//        job1.setEnterprise(enterprise1);
//        job1.setEnterpriseUser(enterpriseUser1);
//        job1.setJobRole("Logistics Management");
//        job1.setExperience("3+ years");
//        job1.setEmploymentType("Full-time");
//        job1.setLocation("Jongno-gu, Seoul");
//        job1.setSkills("Inventory management, logistics systems, process improvement");
//        job1.setImageName("company2.jpg");
//        jobEngRepository.save(job1);
//
//        JobEng job2 = new JobEng();
//        job2.setTitle("Data Scientist");
//        job2.setDescription("Data Scientist\n" +
//                "\n" +
//                "Key Responsibilities:\n" +
//                "As a Data Scientist, you will analyze and interpret complex data sets from various industries to derive meaningful insights. Additionally, you will support data-driven decision-making to optimize business strategies and drive innovation.\n" +
//                "\n" +
//                "Team Introduction:\n" +
//                "Our team analyzes data from various industries to derive important insights and support data-driven decision-making. We use the latest data analysis techniques and machine learning models to help clients and internal teams derive maximum value from data. Our goal is to lead business success through the establishment of a data-centric culture and continuous growth.\n" +
//                "\n" +
//                "Core Responsibilities:\n" +
//                "\n" +
//                "Analyzing and visualizing large data sets:\n" +
//                "\n" +
//                "Collect and preprocess large data sets from various sources.\n" +
//                "Derive insights using advanced data analysis techniques.\n" +
//                "Convey results clearly and effectively using data visualization tools.\n" +
//                "Developing and optimizing machine learning models:\n" +
//                "\n" +
//                "Develop machine learning models to solve business problems.\n" +
//                "Improve accuracy and efficiency through model performance evaluation and optimization.\n" +
//                "Real-time operation and maintenance of models.\n" +
//                "Developing and executing data-driven strategies:\n" +
//                "\n" +
//                "Develop business strategies based on data analysis results.\n" +
//                "Support data-driven decision-making to improve business performance.\n" +
//                "Provide feedback and measure performance after strategy execution.\n" +
//                "Qualifications:\n" +
//                "\n" +
//                "Deep understanding of data analysis and statistics.\n" +
//                "Experience using data analysis tools like Python, R.\n" +
//                "Experience developing machine learning models.\n" +
//                "Proficiency in database management and SQL.\n" +
//                "Strong problem-solving and logical thinking skills.\n" +
//                "Preferred Qualifications:\n" +
//                "\n" +
//                "Experience analyzing large data sets.\n" +
//                "Experience working with data in cloud environments (AWS, GCP, Azure, etc.).\n" +
//                "Proficiency in data visualization tools (Tableau, Power BI, etc.).\n" +
//                "Understanding of business and industry.\n" +
//                "Experience with big data technologies (Hadoop, Spark, etc.).\n" +
//                "Benefits:\n" +
//                "\n" +
//                "Work Environment:\n" +
//                "\n" +
//                "Flexible working hours:\n" +
//                "\n" +
//                "Support for flexible work arrangements and remote work.\n" +
//                "Provide a flexible work environment tailored to individual work styles.\n" +
//                "Health Check-up:\n" +
//                "\n" +
//                "Support for regular health check-ups.\n" +
//                "Manage employee health through comprehensive health check-ups.\n" +
//                "Education and Self-Development Support:\n" +
//                "\n" +
//                "Support for job-related education expenses.\n" +
//                "Support for self-development through books and lectures.\n" +
//                "Team Building and Refreshment:\n" +
//                "\n" +
//                "Various activities and events for team unity.\n" +
//                "Provide vacation and refresh time for rest and recharge.\n" +
//                "Recruitment Process:\n" +
//                "\n" +
//                "Application Review:\n" +
//                "Review resumes and portfolios.\n" +
//                "Online Test:\n" +
//                "Conduct a technical skills assessment.\n" +
//                "Interview:\n" +
//                "Interview for skills, experience, and cultural fit (may include multiple rounds).\n" +
//                "Final Interview:\n" +
//                "Conducted by executives or managers.\n" +
//                "Final Acceptance:\n" +
//                "Onboarding upon successful final interview.");
//        job2.setViews(150);
//        job2.setDeadDate(LocalDate.now().plusDays(30));
//        job2.setEnterprise(enterprise2);
//        job2.setEnterpriseUser(enterpriseUser2);
//        job2.setJobRole("Data Science");
//        job2.setExperience("Experience developing machine learning models");
//        job2.setEmploymentType("Full-time");
//        job2.setLocation("Gangnam-gu, Seoul");
//        job2.setSkills("Python, R, SQL, machine learning");
//        job2.setImageName("company3.jpg");
//        jobEngRepository.save(job2);
//
//        JobEng job3 = new JobEng();
//        job3.setTitle("Software Engineer");
//        job3.setDescription("Software development and maintenance.\nJob Description: Design software architecture, write and review code, fix bugs.\nQualifications: Over 5 years of experience in software development, understanding of Java and Spring.\nPreferred: Experience in developing large-scale systems.\nBenefits: Annual performance bonuses, flexible working hours.");
//        job3.setViews(200);
//        job3.setDeadDate(LocalDate.now().plusDays(30));
//        job3.setEnterprise(enterprise3);
//        job3.setEnterpriseUser(enterpriseUser3);
//        job3.setJobRole("Software Development");
//        job3.setExperience("Over 5 years");
//        job3.setEmploymentType("Full-time");
//        job3.setLocation("2 Ujeongguk-ro, Jongno-gu, Seoul");
//        job3.setSkills("Java, Spring, Software Architecture");
//        job3.setImageName("company1.png");
//        jobEngRepository.save(job3);
//
//        JobEng job4 = new JobEng();
//        job4.setTitle("Project Manager");
//        job4.setDescription("Planning and managing projects.\nJob Description: Manage project schedules, coordinate teams, report performance.\nQualifications: Over 7 years of project management experience, PMP certification.\nPreferred: Experience in IT project management.\nBenefits: Health checkups, annual performance bonuses.");
//        job4.setViews(120);
//        job4.setDeadDate(LocalDate.now().plusDays(40));
//        job4.setEnterprise(enterprise4);
//        job4.setEnterpriseUser(enterpriseUser4);
//        job4.setJobRole("Project Management");
//        job4.setExperience("Over 7 years");
//        job4.setEmploymentType("Full-time");
//        job4.setLocation("Gwanchul-dong, Jongno-gu, Seoul");
//        job4.setSkills("Project scheduling, team coordination, performance reporting");
//        job4.setImageName("company4.jpg");
//        jobEngRepository.save(job4);
//
//        JobEng job5 = new JobEng();
//        job5.setTitle("Marketing Manager");
//        job5.setDescription("Planning and executing marketing strategies.\nJob Description: Plan marketing campaigns, manage budgets, analyze performance.\nQualifications: Over 5 years of marketing experience, understanding of digital marketing.\nPreferred: Experience in global marketing.\nBenefits: Flexible working hours, annual performance bonuses.");
//        job5.setViews(130);
//        job5.setDeadDate(LocalDate.now().plusDays(35));
//        job5.setEnterprise(enterprise5);
//        job5.setEnterpriseUser(enterpriseUser5);
//        job5.setJobRole("Marketing Management");
//        job5.setExperience("Over 5 years");
//        job5.setEmploymentType("Full-time");
//        job5.setLocation("Samil-daero, Jongno-gu, Seoul");
//        job5.setSkills("Marketing strategy planning, budget management, performance analysis");
//        job5.setImageName("company5.jpeg");
//        jobEngRepository.save(job5);
//
//        JobEng job6 = new JobEng();
//        job6.setTitle("Construction Worker");
//        job6.setDescription(
//                "Company Overview\n" +
//                        "CFS (Coupang Fulfillment Services) is a subsidiary of Coupang that oversees logistics. CFS is responsible for operating fulfillment centers (FCs), driving innovation to grow into a global e-commerce company by enhancing operational efficiency and expertise.\n" +
//                        "Coupang’s FCs employ experts across various fields, handling tasks such as receiving, storing, packing, shipping, and returns. Unlike typical warehouses, FCs utilize factory automation solutions and advanced technology. They specialize tasks similar to manufacturing plants and optimize logistics systems by managing space and time efficiently based on data.\n" +
//                        "CFS aims to create a world where customers wonder how they lived without Coupang by driving logistics innovation. Join us to build our dynamic future together.\n" +
//                        "\n" +
//                        "For more details on this position, please visit our website using the 'Apply on Company Website' button on the right :)\n" +
//                        "\n" +
//                        "Main Responsibilities\n" +
//                        "Perform various tasks at construction sites. Key responsibilities include:\n" +
//                        "- Transport construction materials\n" +
//                        "- Clean and organize the site\n" +
//                        "- Assist with basic construction work\n" +
//                        "\n" +
//                        "Qualifications\n" +
//                        "- Related experience is preferred\n" +
//                        "- Physical fitness and reliability are essential\n" +
//                        "\n" +
//                        "Preferred Qualifications\n" +
//                        "- Experience working at construction sites\n" +
//                        "\n" +
//                        "Benefits\n" +
//                        "- Snacks provided\n" +
//                        "- Daily wages paid\n" +
//                        "\n" +
//                        "Recruitment Process\n" +
//                        "Document screening - Phone interview - In-person interview - Final acceptance\n" +
//                        "The recruitment process may vary by role and can change based on circumstances. Applicants will be informed individually via the email provided in their application.\n" +
//                        "\n" +
//                        "Additional Information\n" +
//                        "This job posting may close early if the position is filled. A 12-week probationary period is included. Any false information in the application may result in disqualification. Veteran and disabled status will not affect the recruitment process. The job grade and responsibilities may be adjusted based on the candidate's overall experience and qualifications. If such changes are necessary, they will be communicated to the candidate before the final offer.\n" +
//                        "\n" +
//                        "Work Location: Jung-gu, Daejeon\n" +
//                        "\n" +
//                        "Privacy Policy\n" +
//                        "Coupang Group collects and processes your personal information in accordance with our Applicant Privacy Policy (link below).\n" +
//                        "https://www.coupang.jobs/kr/privacy-policy/\n" +
//                        "\n" +
//                        "Document Return Policy\n" +
//                        "This notice complies with Article 11, Paragraph 6 of the 'Act on the Fairness of Recruitment Procedures'. Applicants who do not receive an offer can request the return of their submitted documents according to the 'Act on the Fairness of Recruitment Procedures'. However, this does not apply if the documents were submitted electronically or voluntarily submitted without our request. If the documents are lost due to reasons beyond our control, they are considered returned. To request the return of submitted documents, please complete the Document Return Request Form [Attachment 3, Enforcement Rules of the Act on the Fairness of Recruitment Procedures] and send it to our recruitment team (Coupang Recruiting Team, Tower 730, 570 Songpa-daero, Songpa-gu, Seoul). Upon confirmation of receipt, we will mail the documents to the designated address within 14 days. We will retain the original documents submitted by applicants for 180 days from the confirmation of recruitment status and will destroy all documents without delay under the 'Personal Information Protection Act' if no request for return is made within this period.\n" +
//                        "Employment may be restricted if required legal qualifications are not met."
//        );
//        job6.setViews(80);
//        job6.setDeadDate(LocalDate.now().plusDays(30));
//        job6.setEnterprise(enterprise1);
//        job6.setEnterpriseUser(enterpriseUser1);
//        job6.setJobRole("Construction Worker");
//        job6.setExperience("No experience required");
//        job6.setEmploymentType("Temporary");
//        job6.setLocation("Jung-gu, Daejeon");
//        job6.setSkills("Physical strength, reliability, basic construction knowledge");
//        job6.setImageName("company6.jpg");
//        jobEngRepository.save(job6);
//
//        JobEng job7 = new JobEng();
//        job7.setTitle("Kitchen Assistant");
//        job7.setDescription(
//                "Company Overview\n" +
//                        "CFS (Coupang Fulfillment Services) is a subsidiary of Coupang that oversees logistics. CFS is responsible for operating fulfillment centers (FCs), driving innovation to grow into a global e-commerce company by enhancing operational efficiency and expertise.\n" +
//                        "Coupang’s FCs employ experts across various fields, handling tasks such as receiving, storing, packing, shipping, and returns. Unlike typical warehouses, FCs utilize factory automation solutions and advanced technology. They specialize tasks similar to manufacturing plants and optimize logistics systems by managing space and time efficiently based on data.\n" +
//                        "CFS aims to create a world where customers wonder how they lived without Coupang by driving logistics innovation. Join us to build our dynamic future together.\n" +
//                        "\n" +
//                        "For more details on this position, please visit our website using the 'Apply on Company Website' button on the right :)\n" +
//                        "\n" +
//                        "Main Responsibilities\n" +
//                        "Responsible for food preparation and kitchen cleaning. Key tasks include:\n" +
//                        "- Preparing ingredients\n" +
//                        "- Assisting with simple cooking tasks\n" +
//                        "- Organizing and cleaning the kitchen\n" +
//                        "\n" +
//                        "Qualifications\n" +
//                        "- Related experience is preferred\n" +
//                        "- Physical fitness and reliability are essential\n" +
//                        "\n" +
//                        "Preferred Qualifications\n" +
//                        "- Experience working in the food service industry\n" +
//                        "\n" +
//                        "Benefits\n" +
//                        "- Meals provided\n" +
//                        "- Daily wages paid\n" +
//                        "\n" +
//                        "Recruitment Process\n" +
//                        "Document screening - Phone interview - In-person interview - Final acceptance\n" +
//                        "The recruitment process may vary by role and can change based on circumstances. Applicants will be informed individually via the email provided in their application.\n" +
//                        "\n" +
//                        "Additional Information\n" +
//                        "This job posting may close early if the position is filled. A 12-week probationary period is included. Any false information in the application may result in disqualification. Veteran and disabled status will not affect the recruitment process. The job grade and responsibilities may be adjusted based on the candidate's overall experience and qualifications. If such changes are necessary, they will be communicated to the candidate before the final offer.\n" +
//                        "\n" +
//                        "Work Location: Gangnam-gu, Seoul\n" +
//                        "\n" +
//                        "Privacy Policy\n" +
//                        "Coupang Group collects and processes your personal information in accordance with our Applicant Privacy Policy (link below).\n" +
//                        "https://www.coupang.jobs/kr/privacy-policy/\n" +
//                        "\n" +
//                        "Document Return Policy\n" +
//                        "This notice complies with Article 11, Paragraph 6 of the 'Act on the Fairness of Recruitment Procedures'. Applicants who do not receive an offer can request the return of their submitted documents according to the 'Act on the Fairness of Recruitment Procedures'. However, this does not apply if the documents were submitted electronically or voluntarily submitted without our request. If the documents are lost due to reasons beyond our control, they are considered returned. To request the return of submitted documents, please complete the Document Return Request Form [Attachment 3, Enforcement Rules of the Act on the Fairness of Recruitment Procedures] and send it to our recruitment team (Coupang Recruiting Team, Tower 730, 570 Songpa-daero, Songpa-gu, Seoul). Upon confirmation of receipt, we will mail the documents to the designated address within 14 days. We will retain the original documents submitted by applicants for 180 days from the confirmation of recruitment status and will destroy all documents without delay under the 'Personal Information Protection Act' if no request for return is made within this period.\n" +
//                        "Employment may be restricted if required legal qualifications are not met."
//        );
//        job7.setViews(100);
//        job7.setDeadDate(LocalDate.now().plusDays(30));
//        job7.setEnterprise(enterprise2);
//        job7.setEnterpriseUser(enterpriseUser2);
//        job7.setJobRole("Kitchen Assistant");
//        job7.setExperience("No experience required");
//        job7.setEmploymentType("Temporary");
//        job7.setLocation("Gangnam-gu, Seoul");
//        job7.setSkills("Ingredient preparation, cleaning, basic cooking knowledge");
//        job7.setImageName("company7.png");
//        jobEngRepository.save(job7);
//    }
//
//    // 채용공고 더미데이터
//    private void createDummyJobs() {
//        Enterprise enterprise1 = enterpriseRepository.findById("1018100132").orElse(null);
//        Enterprise enterprise2 = enterpriseRepository.findById("1018100205").orElse(null);
//        Enterprise enterprise3 = enterpriseRepository.findById("1018100210").orElse(null);
//        Enterprise enterprise4 = enterpriseRepository.findById("1018100277").orElse(null);
//        Enterprise enterprise5 = enterpriseRepository.findById("1018100340").orElse(null);
//        EnterpriseUser enterpriseUser1 = enterpriseUserRepository.findByEnterprise(enterprise1).get(0);
//        EnterpriseUser enterpriseUser2 = enterpriseUserRepository.findByEnterprise(enterprise2).get(0);
//        EnterpriseUser enterpriseUser3 = enterpriseUserRepository.findByEnterprise(enterprise3).get(0);
//        EnterpriseUser enterpriseUser4 = enterpriseUserRepository.findByEnterprise(enterprise4).get(0);
//        EnterpriseUser enterpriseUser5 = enterpriseUserRepository.findByEnterprise(enterprise5).get(0);
//
//        Job job1 = new Job();
//        job1.setTitle("물류 관리자");
//        job1.setDescription("물류 관리자 (Logistics Manager)\n" +
//                "\n" +
//                "주요 업무:\n" +
//                "물류 관리자는 물류 센터의 원활한 운영과 관리를 책임지며, 재고 관리, 출고 관리, 물류 프로세스 개선 등의 다양한 업무를 수행합니다. 우리의 목표는 효율적인 물류 운영을 통해 고객 만족도를 높이고, 비용 절감과 함께 운영 효율성을 극대화하는 것입니다.\n" +
//                "\n" +
//                "업무 내용:\n" +
//                "\n" +
//                "재고 관리:\n" +
//                "\n" +
//                "재고 수준의 지속적인 모니터링과 최적화.\n" +
//                "재고 정확성을 유지하기 위한 주기적인 재고 조사 및 감사를 수행.\n" +
//                "물류 창고 내의 재고 이동과 배치 최적화.\n" +
//                "출고 관리:\n" +
//                "\n" +
//                "고객 주문의 정확한 처리와 신속한 출고.\n" +
//                "출고 과정에서 발생하는 문제 해결 및 개선.\n" +
//                "물류 시스템을 활용한 출고 일정 및 경로 계획.\n" +
//                "물류 프로세스 개선:\n" +
//                "\n" +
//                "현재 물류 프로세스의 분석 및 개선 방안 제시.\n" +
//                "신기술 도입 및 물류 시스템 최적화를 통한 운영 효율성 증대.\n" +
//                "협력 업체와의 원활한 소통과 관계 유지.\n" +
//                "자격 요건:\n" +
//                "\n" +
//                "물류 관리 경험 3년 이상.\n" +
//                "물류 시스템 및 프로세스에 대한 깊은 이해.\n" +
//                "물류 데이터 분석 및 보고서 작성 능력.\n" +
//                "문제 해결 능력과 뛰어난 커뮤니케이션 스킬.\n" +
//                "팀 협업 능력 및 프로젝트 관리 경험.\n" +
//                "우대 사항:\n" +
//                "\n" +
//                "물류 관련 자격증 소지자 (예: 물류관리사, 국제물류자격증 등).\n" +
//                "대기업 또는 스타트업에서의 다양한 물류 환경 경험.\n" +
//                "물류 자동화 및 신기술 도입 경험.\n" +
//                "피트니스 및 스포츠 산업에 대한 관심.\n" +
//                "복지 및 혜택:\n" +
//                "\n" +
//                "업무 환경:\n" +
//                "\n" +
//                "OKR 기반의 목표 수립:\n" +
//                "명확한 목표 설정을 통해 업무 성과를 극대화.\n" +
//                "CFR 통한 성과 회고 시스템:\n" +
//                "정기적인 성과 리뷰를 통해 개인 및 팀의 성장을 지원.\n" +
//                "분기별 Peer Feedback 통한 성장 피드백:\n" +
//                "팀원 간 피드백을 통해 상호 발전을 도모.\n" +
//                "연 2회 급여인상 및 아웃라이어 선정 시 초과 보상:\n" +
//                "탁월한 성과를 보인 직원에게는 추가 보상 제공.\n" +
//                "수시급여 클레임 통한 수시 성과 반영:\n" +
//                "실적에 따른 보상 체계 운영.\n" +
//                "복지 혜택:\n" +
//                "\n" +
//                "따뜻한 팀:\n" +
//                "패밀리 할인 쿠폰 발급.\n" +
//                "건강 검진 비용 지원.\n" +
//                "1/3/5년 장기 근속자 포상.\n" +
//                "똑똑한 팀:\n" +
//                "교육 및 도서 구입비 지원.\n" +
//                "체계적인 온보딩 교육.\n" +
//                "최고의 복지는 버핏서울 동료 → 구성원 소개.\n" +
//                "스포츠 팀:\n" +
//                "팀버핏 트레이닝 무료 수강.\n" +
//                "버핏그라운드 지점 무료 이용.\n" +
//                "1:1 PT 및 입점 업체 운동 상품(골프/필라테스/요가 등) 할인.\n" +
//                "채용 절차:\n" +
//                "\n" +
//                "간편 접수:\n" +
//                "이력서 제출 (포트폴리오는 선택).\n" +
//                "서면 인터뷰 요청.\n" +
//                "1차 인터뷰:\n" +
//                "직무 소양 확인 인터뷰 (팀 리드 및 실무자와의 대화).\n" +
//                "40분에서 70분간 진행.\n" +
//                "2차 인터뷰:\n" +
//                "구체적인 Culture Fit 확인 (C 레벨 및 임원과의 대화).\n" +
//                "40분에서 70분간 진행.\n" +
//                "최종합격:\n" +
//                "2차 인터뷰 합격 시 레퍼런스 체크 후 최종 처우 협의 및 합류.");
//        job1.setViews(100);
//        job1.setDeadDate(LocalDate.now().plusDays(30));
//        job1.setEnterprise(enterprise1);
//        job1.setEnterpriseUser(enterpriseUser1);
//        job1.setJobRole("Logistics Management");
//        job1.setExperience("3년 이상");
//        job1.setEmploymentType("정규직");
//        job1.setLocation("서울특별시 종로구 낙원동");
//        job1.setSkills("재고 관리, 물류 시스템, 물류 프로세스 개선");
//        job1.setImageName("company2.jpg");
//        jobRepository.save(job1);
//
//        Job job2 = new Job();
//        job2.setTitle("데이터 과학자");
//        job2.setDescription("데이터 과학자 (Data Scientist)\n" +
//                "\n" +
//                "주요 업무:\n" +
//                "데이터 과학자는 다양한 산업에서 생성되는 복잡한 데이터 세트를 분석하고 해석하며, 이를 통해 유의미한 인사이트를 도출합니다. 또한, 데이터 기반 의사결정을 지원하여 비즈니스 전략을 최적화하고 혁신을 도모합니다.\n" +
//                "\n" +
//                "조직 소개:\n" +
//                "우리 팀은 다양한 산업의 데이터를 분석하여 중요한 인사이트를 도출하고, 데이터 기반 의사결정을 지원합니다. 우리는 최신 데이터 분석 기법과 머신러닝 모델을 활용하여 고객과 내부 팀이 데이터로부터 최대한의 가치를 얻을 수 있도록 돕습니다. 우리의 목표는 데이터 중심의 문화 구축과 지속적인 성장을 통해 비즈니스 성공을 이끄는 것입니다.\n" +
//                "\n" +
//                "핵심 업무:\n" +
//                "\n" +
//                "대규모 데이터 세트 분석 및 시각화:\n" +
//                "\n" +
//                "다양한 데이터 소스에서 대규모 데이터 세트를 수집하고 전처리.\n" +
//                "고급 데이터 분석 기법을 사용하여 인사이트 도출.\n" +
//                "데이터 시각화 도구를 사용하여 결과를 명확하고 효과적으로 전달.\n" +
//                "머신러닝 모델 개발 및 최적화:\n" +
//                "\n" +
//                "비즈니스 문제 해결을 위한 머신러닝 모델 개발.\n" +
//                "모델 성능 평가 및 최적화를 통해 정확도와 효율성 향상.\n" +
//                "모델의 실시간 운영 및 유지보수.\n" +
//                "데이터 기반 전략 수립 및 실행:\n" +
//                "\n" +
//                "데이터 분석 결과를 바탕으로 비즈니스 전략 수립.\n" +
//                "데이터 기반 의사결정을 지원하여 비즈니스 성과 향상.\n" +
//                "전략 실행 후 성과 측정 및 피드백 제공.\n" +
//                "자격 요건:\n" +
//                "\n" +
//                "데이터 분석 및 통계학에 대한 깊은 이해.\n" +
//                "Python, R 등 데이터 분석 도구 사용 경험.\n" +
//                "머신러닝 모델 개발 경험.\n" +
//                "데이터베이스 관리 및 SQL 활용 능력.\n" +
//                "문제 해결 능력과 논리적 사고 능력.\n" +
//                "우대 사항:\n" +
//                "\n" +
//                "대규모 데이터 분석 경험.\n" +
//                "클라우드 환경에서의 데이터 작업 경험 (AWS, GCP, Azure 등).\n" +
//                "데이터 시각화 도구 사용 능력 (Tableau, Power BI 등).\n" +
//                "비즈니스 및 산업에 대한 이해.\n" +
//                "빅데이터 기술 경험 (Hadoop, Spark 등).\n" +
//                "복지 및 혜택:\n" +
//                "\n" +
//                "업무 환경:\n" +
//                "\n" +
//                "유연 근무제:\n" +
//                "\n" +
//                "자율 출퇴근제 및 원격 근무 지원.\n" +
//                "개인의 업무 스타일에 맞춘 유연한 근무 환경 제공.\n" +
//                "건강 검진:\n" +
//                "\n" +
//                "정기 건강 검진 지원.\n" +
//                "종합 건강 검진을 통한 직원 건강 관리.\n" +
//                "교육 및 자기계발 지원:\n" +
//                "\n" +
//                "직무 관련 교육비 지원.\n" +
//                "자기계발을 위한 도서 및 강의 지원.\n" +
//                "팀빌딩 및 리프레시:\n" +
//                "\n" +
//                "팀 단합을 위한 다양한 활동 및 이벤트.\n" +
//                "주기적인 리프레시 휴가 제공.\n" +
//                "채용 절차:\n" +
//                "\n" +
//                "간편 접수:\n" +
//                "이력서 제출 (포트폴리오는 선택).\n" +
//                "서면 인터뷰 요청.\n" +
//                "1차 인터뷰:\n" +
//                "직무 소양 확인 인터뷰 (팀 리드 및 실무자와의 대화).\n" +
//                "40분에서 70분간 진행.\n" +
//                "2차 인터뷰:\n" +
//                "구체적인 Culture Fit 확인 (C 레벨 및 임원과의 대화).\n" +
//                "40분에서 70분간 진행.\n" +
//                "최종합격:\n" +
//                "2차 인터뷰 합격 시 레퍼런스 체크 후 최종 처우 협의 및 합류.");
//        job2.setViews(150);
//        job2.setDeadDate(LocalDate.now().plusDays(45));
//        job2.setEnterprise(enterprise2);
//        job2.setEnterpriseUser(enterpriseUser2);
//        job2.setJobRole("Data Science");
//        job2.setExperience("3 ~ 7년");
//        job2.setEmploymentType("정규직");
//        job2.setLocation("서울");
//        job2.setSkills("Python, R, 통계, 머신러닝, 빅데이터, 클라우드");
//        job2.setImageName("company3.jpg");
//        jobRepository.save(job2);
//
//        Job job3 = new Job();
//        job3.setTitle("소프트웨어 엔지니어");
//        job3.setDescription("소프트웨어 개발 및 유지보수.\n업무 내용: 소프트웨어 아키텍처 설계, 코드 작성 및 리뷰, 버그 수정.\n자격 요건: 소프트웨어 개발 경험 5년 이상, Java 및 Spring 이해.\n우대 사항: 대규모 시스템 개발 경험.\n복지: 연간 성과 보너스, 유연 근무제.");
//        job3.setViews(200);
//        job3.setDeadDate(LocalDate.now().plusDays(30));
//        job3.setEnterprise(enterprise3);
//        job3.setEnterpriseUser(enterpriseUser3);
//        job3.setJobRole("Software Development");
//        job3.setExperience("5년 이상");
//        job3.setEmploymentType("정규직");
//        job3.setLocation("서울특별시 종로구 우정국로2길");
//        job3.setSkills("Java, Spring, 소프트웨어 아키텍처");
//        job3.setImageName("company1.png");
//        jobRepository.save(job3);
//
//        Job job4 = new Job();
//        job4.setTitle("프로젝트 매니저");
//        job4.setDescription("프로젝트 계획 수립 및 관리.\n업무 내용: 프로젝트 일정 관리, 팀 조정, 성과 보고.\n자격 요건: 프로젝트 관리 경험 7년 이상, PMP 자격증 소지자.\n우대 사항: IT 프로젝트 관리 경험.\n복지: 건강 검진, 연간 성과 보너스.");
//        job4.setViews(120);
//        job4.setDeadDate(LocalDate.now().plusDays(40));
//        job4.setEnterprise(enterprise4);
//        job4.setEnterpriseUser(enterpriseUser4);
//        job4.setJobRole("Project Management");
//        job4.setExperience("7년 이상");
//        job4.setEmploymentType("정규직");
//        job4.setLocation("서울특별시 종로구 관철동");
//        job4.setSkills("프로젝트 일정 관리, 팀 조정, 성과 보고");
//        job4.setImageName("company4.jpg");
//        jobRepository.save(job4);
//
//        Job job5 = new Job();
//        job5.setTitle("마케팅 매니저");
//        job5.setDescription("마케팅 전략 수립 및 실행.\n업무 내용: 마케팅 캠페인 기획, 예산 관리, 성과 분석.\n자격 요건: 마케팅 경험 5년 이상, 디지털 마케팅 이해.\n우대 사항: 글로벌 마케팅 경험.\n복지: 유연 근무제, 연간 성과 보너스.");
//        job5.setViews(130);
//        job5.setDeadDate(LocalDate.now().plusDays(35));
//        job5.setEnterprise(enterprise5);
//        job5.setEnterpriseUser(enterpriseUser5);
//        job5.setJobRole("Marketing Management");
//        job5.setExperience("5년 이상");
//        job5.setEmploymentType("정규직");
//        job5.setLocation("서울특별시 종로구 삼일대로");
//        job5.setSkills("마케팅 전략 수립, 예산 관리, 성과 분석");
//        job5.setImageName("company5.jpeg");
//        jobRepository.save(job5);
//
//        Job job6 = new Job();
//        job6.setTitle("건설 현장 작업자");
//        job6.setDescription(
//                "기업 소개\n" +
//                        "CFS (Coupang Fulfillment Services) 는 쿠팡의 물류를 총괄하는 쿠팡의 계열사입니다. CFS는 물류센터 또는 FC (Fulfillment Center) 운영을 책임지고 있으며, 업무 효율성과 전문성 제고를 통해 글로벌 이커머스 기업으로 성장하기 위한 혁신을 이끌어 가고 있습니다.\n" +
//                        "쿠팡의 FC는 상품의 입고, 적재, 포장, 출고 및 반품 등 사후 처리에 이르는 다양한 분야의 전문가들이 함께 일하고 있습니다. FC는 단순한 물류 창고와 다르게 공장 자동화 (Factory Automation) 솔루션과 첨단 기술을 활용하고 있습니다. 제조업 생산 공장과 같이 각각의 업무를 전문화하고, 입고부터 출고까지 데이터를 기반으로 시공간을 효율적으로 관리하여 낭비를 최소화하는 등 최적의 물류 시스템을 구현해 나가고 있습니다.\n" +
//                        "CFS는 물류 혁신을 통해 고객이 “쿠팡 없이 어떻게 살았을까?” 라고 말하는 세상을 만들고자 합니다. 우리의 역동적인 미래를 함께 만들고 싶다면 CFS와 함께하세요.\n" +
//                        "\n" +
//                        "해당 직무에 관련한 자세한 사항은 우측 '홈페이지 지원' 버튼을 통해 자사 홈페이지에 접속하여 확인해주세요 : )\n" +
//                        "\n" +
//                        "주요 업무\n" +
//                        "건설 현장에서의 다양한 작업을 수행합니다. 주요 업무는 다음과 같습니다:\n" +
//                        "- 건설 자재 운반\n" +
//                        "- 현장 청소 및 정리\n" +
//                        "- 간단한 건설 작업 지원\n" +
//                        "\n" +
//                        "자격 요건\n" +
//                        "- 관련 경험이 있으면 우대\n" +
//                        "- 체력과 성실함 필수\n" +
//                        "\n" +
//                        "우대 사항\n" +
//                        "- 건설 현장에서의 일 경험\n" +
//                        "\n" +
//                        "복지 및 혜택\n" +
//                        "- 간식 제공\n" +
//                        "- 일일 급여 지급\n" +
//                        "\n" +
//                        "채용 절차\n" +
//                        "서류전형 - 전화면접 - 대면면접 - 최종 합격\n" +
//                        "전형 절차는 직무별로 다르게 운영될 수 있으며, 일정 및 상황에 따라 변동될 수 있습니다. 전형 일정 및 결과는 지원서에 등록하신 이메일로 개별 안내드립니다.\n" +
//                        "\n" +
//                        "참고 사항\n" +
//                        "본 공고는 모집 완료 시 조기 마감될 수 있습니다. 해당 채용은 수습기간 12주를 포함합니다. 지원서 내용 중 허위사실이 있는 경우에는 합격이 취소될 수 있습니다. 보훈대상자 및 장애인 여부는 채용 과정에서 어떠한 불이익도 미치지 않습니다. 직급과 담당 업무 범위는 후보자의 전반적인 경력과 경험 등 제반 사정을 고려하여 변경될 수 있습니다. 이러한 변경이 필요할 경우, 최종 합격 통지 전 적절한 시기에 후보자와 커뮤니케이션 될 예정입니다.\n" +
//                        "\n" +
//                        "근무지 : 서울특별시 강남구\n" +
//                        "\n" +
//                        "개인정보 처리방침\n" +
//                        "쿠팡 그룹은 입사지원자 개인정보 처리방침(아래 링크)에 따라 귀하의 개인정보를 수집하여 처리합니다.\n" +
//                        "https://www.coupang.jobs/kr/privacy-policy/\n" +
//                        "\n" +
//                        "서류 반환 정책\n" +
//                        "본 고지는 『채용절차의 공정화에 관한 법률』 제11조 제6항에 따른 것입니다. 당사 채용에 응시한 구직자 중 최종합격이 되지 못한 구직자는 『채용절차의 공정화에 관한 법률』에 따라 제출한 채용서류의 반환을 청구할 수 있음을 알려 드립니다. 다만, 홈페이지 또는 전자우편으로 제출된 경우나 구직자가 당사의 요구 없이 자발적으로 제출한 경우에는 그러하지 아니하며, 천재지변이나 그 밖에 당사에게 책임 없는 사유로 채용서류가 멸실된 경우에는 반환한 것으로 봅니다. 위 2항 본문에 따라 채용서류 반환 청구를 하는 구직자는 채용서류 반환청구서 [채용절차의 공정화에 관한 법률 시행규칙 별지 제3호 서식]를 작성하여 당사 채용팀 (서울시 송파구 송파대로 570 타워730 쿠팡채용팀) 으로 제출하면, 제출이 확인된 날로부터 14일 이내에 지정한 주소지로 등기우편을 통하여 발송해 드립니다. 당사는 위 2항 본문에 따른 구직자의 반환 청구에 대비하여 채용 여부가 확정된 날로부터 180일간 구직자가 제출한 채용서류 원본을 보관하게 되며, 그때까지 채용서류의 반환을 청구하지 아니할 경우에는 『개인정보 보호법』에 따라 지체 없이 채용서류 일체를 파기할 예정입니다.\n" +
//                        "채용 및 업무 수행과 관련하여 요구되는 법령상 자격이 갖추어지지 않은 경우 채용이 제한될 수 있습니다."
//        );
//        job6.setViews(80);
//        job6.setDeadDate(LocalDate.now().plusDays(30));
//        job6.setEnterprise(enterprise1);
//        job6.setEnterpriseUser(enterpriseUser1);
//        job6.setJobRole("Construction Worker");
//        job6.setExperience("경험 무관");
//        job6.setEmploymentType("일용직");
//        job6.setLocation("대전광역시 중구");
//        job6.setSkills("체력, 성실함, 기본 건설 지식");
//        job6.setImageName("company6.jpg");
//        jobRepository.save(job6);
//
//        Job job7 = new Job();
//        job7.setTitle("주방 보조");
//        job7.setDescription(
//                "기업 소개\n" +
//                        "CFS (Coupang Fulfillment Services) 는 쿠팡의 물류를 총괄하는 쿠팡의 계열사입니다. CFS는 물류센터 또는 FC (Fulfillment Center) 운영을 책임지고 있으며, 업무 효율성과 전문성 제고를 통해 글로벌 이커머스 기업으로 성장하기 위한 혁신을 이끌어 가고 있습니다.\n" +
//                        "쿠팡의 FC는 상품의 입고, 적재, 포장, 출고 및 반품 등 사후 처리에 이르는 다양한 분야의 전문가들이 함께 일하고 있습니다. FC는 단순한 물류 창고와 다르게 공장 자동화 (Factory Automation) 솔루션과 첨단 기술을 활용하고 있습니다. 제조업 생산 공장과 같이 각각의 업무를 전문화하고, 입고부터 출고까지 데이터를 기반으로 시공간을 효율적으로 관리하여 낭비를 최소화하는 등 최적의 물류 시스템을 구현해 나가고 있습니다.\n" +
//                        "CFS는 물류 혁신을 통해 고객이 “쿠팡 없이 어떻게 살았을까?” 라고 말하는 세상을 만들고자 합니다. 우리의 역동적인 미래를 함께 만들고 싶다면 CFS와 함께하세요.\n" +
//                        "\n" +
//                        "해당 직무에 관련한 자세한 사항은 우측 '홈페이지 지원' 버튼을 통해 자사 홈페이지에 접속하여 확인해주세요 : )\n" +
//                        "\n" +
//                        "주요 업무\n" +
//                        "음식 준비와 주방 청소를 담당합니다. 주요 업무는 다음과 같습니다:\n" +
//                        "- 재료 손질\n" +
//                        "- 간단한 요리 준비\n" +
//                        "- 주방 정리 및 청소\n" +
//                        "\n" +
//                        "자격 요건\n" +
//                        "- 관련 경험이 있으면 우대\n" +
//                        "- 체력과 성실함 필수\n" +
//                        "\n" +
//                        "우대 사항\n" +
//                        "- 요식업에서의 일 경험\n" +
//                        "\n" +
//                        "복지 및 혜택\n" +
//                        "- 식사 제공\n" +
//                        "- 일일 급여 지급\n" +
//                        "\n" +
//                        "채용 절차\n" +
//                        "서류전형 - 전화면접 - 대면면접 - 최종 합격\n" +
//                        "전형 절차는 직무별로 다르게 운영될 수 있으며, 일정 및 상황에 따라 변동될 수 있습니다. 전형 일정 및 결과는 지원서에 등록하신 이메일로 개별 안내드립니다.\n" +
//                        "\n" +
//                        "참고 사항\n" +
//                        "본 공고는 모집 완료 시 조기 마감될 수 있습니다. 해당 채용은 수습기간 12주를 포함합니다. 지원서 내용 중 허위사실이 있는 경우에는 합격이 취소될 수 있습니다. 보훈대상자 및 장애인 여부는 채용 과정에서 어떠한 불이익도 미치지 않습니다. 직급과 담당 업무 범위는 후보자의 전반적인 경력과 경험 등 제반 사정을 고려하여 변경될 수 있습니다. 이러한 변경이 필요할 경우, 최종 합격 통지 전 적절한 시기에 후보자와 커뮤니케이션 될 예정입니다.\n" +
//                        "\n" +
//                        "근무지 : 서울특별시 강남구\n" +
//                        "\n" +
//                        "개인정보 처리방침\n" +
//                        "쿠팡 그룹은 입사지원자 개인정보 처리방침(아래 링크)에 따라 귀하의 개인정보를 수집하여 처리합니다.\n" +
//                        "https://www.coupang.jobs/kr/privacy-policy/\n" +
//                        "\n" +
//                        "서류 반환 정책\n" +
//                        "본 고지는 『채용절차의 공정화에 관한 법률』 제11조 제6항에 따른 것입니다. 당사 채용에 응시한 구직자 중 최종합격이 되지 못한 구직자는 『채용절차의 공정화에 관한 법률』에 따라 제출한 채용서류의 반환을 청구할 수 있음을 알려 드립니다. 다만, 홈페이지 또는 전자우편으로 제출된 경우나 구직자가 당사의 요구 없이 자발적으로 제출한 경우에는 그러하지 아니하며, 천재지변이나 그 밖에 당사에게 책임 없는 사유로 채용서류가 멸실된 경우에는 반환한 것으로 봅니다. 위 2항 본문에 따라 채용서류 반환 청구를 하는 구직자는 채용서류 반환청구서 [채용절차의 공정화에 관한 법률 시행규칙 별지 제3호 서식]를 작성하여 당사 채용팀 (서울시 송파구 송파대로 570 타워730 쿠팡채용팀) 으로 제출하면, 제출이 확인된 날로부터 14일 이내에 지정한 주소지로 등기우편을 통하여 발송해 드립니다. 당사는 위 2항 본문에 따른 구직자의 반환 청구에 대비하여 채용 여부가 확정된 날로부터 180일간 구직자가 제출한 채용서류 원본을 보관하게 되며, 그때까지 채용서류의 반환을 청구하지 아니할 경우에는 『개인정보 보호법』에 따라 지체 없이 채용서류 일체를 파기할 예정입니다.\n" +
//                        "채용 및 업무 수행과 관련하여 요구되는 법령상 자격이 갖추어지지 않은 경우 채용이 제한될 수 있습니다."
//        );
//        job7.setViews(100);
//        job7.setDeadDate(LocalDate.now().plusDays(30));
//        job7.setEnterprise(enterprise2);
//        job7.setEnterpriseUser(enterpriseUser2);
//        job7.setJobRole("Kitchen Assistant");
//        job7.setExperience("경험 무관");
//        job7.setEmploymentType("일용직");
//        job7.setLocation("서울특별시 강남구");
//        job7.setSkills("재료 손질, 청소, 기본 요리 지식");
//        job7.setImageName("company7.png");
//        jobRepository.save(job7);
//
//
//    }
//}
//
//
