package pelican.co_labor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.repository.enterprise.EnterpriseRepository;

@Component
public class EnterpriseDummyDataLoader implements CommandLineRunner {

    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public EnterpriseDummyDataLoader(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public void run(String... args) {
        createDummyEnterprises();
    }

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
}
