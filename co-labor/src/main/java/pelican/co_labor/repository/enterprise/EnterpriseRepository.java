package pelican.co_labor.repository.enterprise;

import pelican.co_labor.domain.enterprise.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
