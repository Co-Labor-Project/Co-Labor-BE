package pelican.co_labor.repository.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.job.Job;


@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
