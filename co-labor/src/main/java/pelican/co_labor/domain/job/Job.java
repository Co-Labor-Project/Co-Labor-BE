package pelican.co_labor.domain.job;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long job_id;

    @ManyToOne
    @JoinColumn(name = "enterprise_user_id")
    private EnterpriseUser enterpriseUser;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String requirement;

    @Column(nullable = false)
    private int views;

    @Column(nullable = false)
    private LocalDateTime dead_date;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    // 현재 Job 엔티티에는 modified_at 필드가 null일 수 있는 문제를 해결하기 위해
    // @PrePersist 메서드에서 modified_at을 설정하도록 수정
    @Column(nullable = false)
    private LocalDateTime modified_at;

    @Column(nullable = true)
    private String imageName;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        created_at = now;
        modified_at = now;
    }

    @PreUpdate
    protected void onUpdate() {
        modified_at = LocalDateTime.now();
    }

}