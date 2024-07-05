package pelican.co_labor.repository.labor_user;

import pelican.co_labor.domain.labor_user.LaborUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaborUserRepository extends JpaRepository<LaborUser, Long> {
}
