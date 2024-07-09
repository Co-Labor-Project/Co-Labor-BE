package pelican.co_labor.domain.labor_user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pelican.co_labor.domain.chatting.Chatting;
import pelican.co_labor.domain.review.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "labor_user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class LaborUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labor_user_id;

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

}
