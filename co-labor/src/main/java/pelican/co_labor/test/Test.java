package pelican.co_labor.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.repository.enterprise.EnterpriseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class Test implements CommandLineRunner {

    private final EnterpriseRepository enterpriseRepository;
    private final Random random = new Random();

    // 랜덤으로 넣을 type 목록
    private final List<String> types = Arrays.asList(
            "서비스업",
            "금융/은행업",
            "IT/정보통신업",
            "판매/유통업",
            "제조/생산/화학업",
            "교육업",
            "건설업",
            "의료/제약업",
            "미디어/광고업",
            "문화/예술/디자인업",
            "기관/협회"
    );

    @Autowired
    public Test(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Enterprise> enterprises = enterpriseRepository.findAll();

        for (Enterprise enterprise : enterprises) {
            // type이 null인 경우에만 업데이트
            if (enterprise.getType() == null || enterprise.getType().isEmpty()) {
                String randomType = getRandomType();
                enterprise.setType(randomType);
                enterpriseRepository.save(enterprise);
                System.out.println("Updated enterprise ID: " + enterprise.getEnterprise_id() + " with type: " + randomType);
            }
        }
    }

    // 랜덤으로 type을 선택하는 메서드
    private String getRandomType() {
        int randomIndex = random.nextInt(types.size());
        return types.get(randomIndex);
    }
}
