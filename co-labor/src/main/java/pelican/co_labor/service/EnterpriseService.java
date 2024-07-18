package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.repository.enterprise.EnterpriseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public List<Enterprise> getAllEnterprises() {
        return enterpriseRepository.findAll();
    }

    public Optional<Enterprise> getEnterpriseById(String enterpriseId) {
        return enterpriseRepository.findById(enterpriseId);
    }

    public Enterprise createEnterprise(Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public Optional<Enterprise> updateEnterprise(String enterpriseId, Enterprise enterpriseDetails) {
        return enterpriseRepository.findById(enterpriseId).map(enterprise -> {
            enterprise.setName(enterpriseDetails.getName());
            enterprise.setAddress1(enterpriseDetails.getAddress1());
            enterprise.setAddress2(enterpriseDetails.getAddress2());
            enterprise.setDescription(enterpriseDetails.getDescription());
            enterprise.setType(enterpriseDetails.getType());
            enterprise.setPhone_number(enterpriseDetails.getPhone_number());
            return enterpriseRepository.save(enterprise);
        });
    }

}
