package pelican.co_labor.domain.enterprise_user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pelican.co_labor.domain.enterprise.Enterprise;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "enterprise_user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class EnterpriseUser {
    @Id
    private Long enterprise_user_id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

}
