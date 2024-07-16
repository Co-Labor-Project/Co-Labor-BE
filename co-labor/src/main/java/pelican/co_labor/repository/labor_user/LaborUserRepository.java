package pelican.co_labor.repository.labor_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.labor_user.LaborUser;

import java.util.Optional;

@Repository
public interface LaborUserRepository extends JpaRepository<LaborUser, String> {
    @Query("SELECT l FROM LaborUser l WHERE l.labor_user_id = :laborUserID")
    Optional<LaborUser> findByLaborUserId(@Param("laborUserID") String laborUserID);
}
