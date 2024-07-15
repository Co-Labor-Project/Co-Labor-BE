package pelican.co_labor.repository.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.hospital.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
