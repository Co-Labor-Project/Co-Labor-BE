package pelican.co_labor.repository.enterprise_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.enterprise.Enterprise;


@Repository
public interface EnterpriseUserRepository extends JpaRepository<Enterprise, Long> {
}
