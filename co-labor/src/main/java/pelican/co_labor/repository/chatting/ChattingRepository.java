package pelican.co_labor.repository.chatting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.enterprise.Enterprise;


@Repository
public interface ChattingRepository extends JpaRepository<Enterprise, Long> {
}
