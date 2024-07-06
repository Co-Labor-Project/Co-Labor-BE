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

    @Column
    private String gender;

    @Column
    private String age;

    @Column(nullable = false)
    private int views;

    @Column(nullable = false)
    private LocalDateTime dead_date;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    @Column(nullable = false)
    private LocalDateTime modified_at;

    @PreUpdate
    protected void onUpdate() {
        modified_at = LocalDateTime.now();
    }

}
