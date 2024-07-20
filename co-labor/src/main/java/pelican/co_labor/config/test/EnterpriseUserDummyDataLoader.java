package pelican.co_labor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;
import pelican.co_labor.repository.enterprise.EnterpriseRepository;
import pelican.co_labor.repository.enterprise_user.EnterpriseUserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
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
        createDummyEnterpriseUser("user1@example.com", "User 1", "password1", "2222222222");
        createDummyEnterpriseUser("user2@example.com", "User 2", "password2", "1111111111");
        createDummyEnterpriseUser("user3@example.com", "User 3", "password3", "3333333333");
        createDummyEnterpriseUser("user4@example.com", "User 4", "password4", "4444444444");
        createDummyEnterpriseUser("user5@example.com", "User 5", "password5", "5555555555");
        createDummyEnterpriseUser("user6@example.com", "User 6", "password6", "6666666666");
        createDummyEnterpriseUser("user7@example.com", "User 7", "password7", "7777777777");
        createDummyEnterpriseUser("user8@example.com", "User 8", "password8", "8888888888");
        createDummyEnterpriseUser("user9@example.com", "User 9", "password9", "9999999999");
        createDummyEnterpriseUser("user10@example.com", "User 10", "password10", "1010101010");
        createDummyEnterpriseUser("user11@example.com", "User 11", "password11", "1111111112");
        createDummyEnterpriseUser("user12@example.com", "User 12", "password12", "1212121212");
        createDummyEnterpriseUser("user13@example.com", "User 13", "password13", "1313131313");
        createDummyEnterpriseUser("user14@example.com", "User 14", "password14", "1414141414");
        createDummyEnterpriseUser("user15@example.com", "User 15", "password15", "1515151515");
        createDummyEnterpriseUser("user16@example.com", "User 16", "password16", "1616161616");
        createDummyEnterpriseUser("user17@example.com", "User 17", "password17", "1717171717");
        createDummyEnterpriseUser("user18@example.com", "User 18", "password18", "1818181818");
        createDummyEnterpriseUser("user19@example.com", "User 19", "password19", "1919191919");
        createDummyEnterpriseUser("user20@example.com", "User 20", "password20", "1234567890");
    }

    private void createDummyEnterpriseUser(String email, String userName, String password, String enterpriseId) {
        if (enterpriseUserRepository.existsByEmail(email)) {
            System.err.println("Email " + email + " already exists, skipping this user.");
            return;
        }

        Optional<Enterprise> enterpriseOpt = enterpriseRepository.findById(enterpriseId);
        if (enterpriseOpt.isPresent()) {
            EnterpriseUser enterpriseUser = new EnterpriseUser();
            enterpriseUser.setEnterprise_user_id(UUID.randomUUID().toString());
            enterpriseUser.setPassword(password);
            enterpriseUser.setName(userName);
            enterpriseUser.setEmail(email);
            enterpriseUser.setEnterprise(enterpriseOpt.get());
            enterpriseUser.setCreated_at(LocalDateTime.now());

            enterpriseUserRepository.save(enterpriseUser);
        } else {
            System.err.println("Enterprise with ID " + enterpriseId + " not found");
        }
    }
}
