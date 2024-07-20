package pelican.co_labor.repository.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pelican.co_labor.domain.job.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j " +
            "WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.career) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.employmentType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.workLocation) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.skills) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.mainTasks) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.qualifications) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.preferences) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.hiringProcess) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.benefits) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Job> searchJobs(@Param("keyword") String keyword);
}
