package pelican.co_labor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;
import pelican.co_labor.repository.enterprise.EnterpriseRepository;
import pelican.co_labor.repository.enterprise_user.EnterpriseUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class EnterpriseUserDummyDataLoader implements CommandLineRunner {

    private final EnterpriseUserRepository enterpriseUserRepository;
    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public EnterpriseUserDummyDataLoader(EnterpriseUserRepository enterpriseUserRepository, EnterpriseRepository enterpriseRepository) {
        this.enterpriseUserRepository = enterpriseUserRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public void run(String... args) {
        List<Enterprise> enterprises = enterpriseRepository.findAll();

        createDummyEnterpriseUser("moonki.kim@example.com", "김문기", "password1", enterprises.get(11));
        createDummyEnterpriseUser("hanul.jung@example.com", "정한울", "password2", enterprises.get(1));
        createDummyEnterpriseUser("junhwa.cho@example.com", "조준화", "password3", enterprises.get(2));
        createDummyEnterpriseUser("dohyun.kim@example.com", "김도현", "password4", enterprises.get(3));
        createDummyEnterpriseUser("jihoon.park@example.com", "박지훈", "password5", enterprises.get(4));
        createDummyEnterpriseUser("younghee.lee@example.com", "이영희", "password6", enterprises.get(5));
        createDummyEnterpriseUser("minsu.choi@example.com", "최민수", "password7", enterprises.get(6));
        createDummyEnterpriseUser("minho.jang@example.com", "장민호", "password8", enterprises.get(7));
        createDummyEnterpriseUser("jimin.han@example.com", "한지민", "password9", enterprises.get(8));
        createDummyEnterpriseUser("sumin.park@example.com", "박수민", "password10", enterprises.get(9));
        createDummyEnterpriseUser("james.smith@example.com", "James Smith", "password11", enterprises.get(10));
        createDummyEnterpriseUser("emma.johnson@example.com", "Emma Johnson", "password12", enterprises.get(11));
        createDummyEnterpriseUser("michael.brown@example.com", "Michael Brown", "password13", enterprises.get(12));
        createDummyEnterpriseUser("david.wilson@example.com", "David Wilson", "password14", enterprises.get(13));
        createDummyEnterpriseUser("john.taylor@example.com", "John Taylor", "password15", enterprises.get(14));
        createDummyEnterpriseUser("sophie.lee@example.com", "Sophie Lee", "password16", enterprises.get(15));
        createDummyEnterpriseUser("daniel.harris@example.com", "Daniel Harris", "password17", enterprises.get(16));
        createDummyEnterpriseUser("emily.clark@example.com", "Emily Clark", "password18", enterprises.get(17));
        createDummyEnterpriseUser("olivia.lewis@example.com", "Olivia Lewis", "password19", enterprises.get(18));
        createDummyEnterpriseUser("lucas.young@example.com", "Lucas Young", "password20", enterprises.get(19));
        createDummyEnterpriseUser("alex.kim@example.com", "Alex Kim", "password21", enterprises.get(20));
        createDummyEnterpriseUser("bella.martinez@example.com", "Bella Martinez", "password22", enterprises.get(21));
        createDummyEnterpriseUser("chris.evans@example.com", "Chris Evans", "password23", enterprises.get(22));
        createDummyEnterpriseUser("daniel.parker@example.com", "Daniel Parker", "password24", enterprises.get(23));
        createDummyEnterpriseUser("ethan.lee@example.com", "Ethan Lee", "password25", enterprises.get(24));
        createDummyEnterpriseUser("fiona.green@example.com", "Fiona Green", "password26", enterprises.get(25));
        createDummyEnterpriseUser("george.king@example.com", "George King", "password27", enterprises.get(26));
        createDummyEnterpriseUser("henry.white@example.com", "Henry White", "password28", enterprises.get(27));
        createDummyEnterpriseUser("ivy.thomas@example.com", "Ivy Thomas", "password29", enterprises.get(28));
        createDummyEnterpriseUser("jack.davis@example.com", "Jack Davis", "password30", enterprises.get(29));
    }

    private void createDummyEnterpriseUser(String email, String name, String password, Enterprise enterprise) {
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        enterpriseUser.setEnterprise_user_id(UUID.randomUUID().toString());
        enterpriseUser.setEmail(email);
        enterpriseUser.setName(name);
        enterpriseUser.setPassword(password);
        enterpriseUser.setEnterprise(enterprise);
        enterpriseUser.setCreated_at(LocalDateTime.now());
        enterpriseUserRepository.save(enterpriseUser);
    }
}
