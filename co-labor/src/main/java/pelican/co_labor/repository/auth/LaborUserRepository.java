package pelican.co_labor.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import pelican.co_labor.domain.labor_user.LaborUser;

public interface LaborUserRepository extends JpaRepository<LaborUser, Long> {
}
