package pelican.co_labor.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;

public interface EnterpriseUserRepository extends JpaRepository<EnterpriseUser, Long> {
}
