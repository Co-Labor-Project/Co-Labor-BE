package pelican.co_labor.repository.labor_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.labor_user.LaborUser;

@Repository
public interface LaborUserRepository extends JpaRepository<LaborUser, Long> {
}
