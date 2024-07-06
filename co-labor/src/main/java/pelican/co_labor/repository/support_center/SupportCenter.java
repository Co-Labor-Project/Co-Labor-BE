package pelican.co_labor.repository.support_center;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.enterprise.Enterprise;


@Repository
public interface SupportCenter extends JpaRepository<Enterprise, Long> {
}
