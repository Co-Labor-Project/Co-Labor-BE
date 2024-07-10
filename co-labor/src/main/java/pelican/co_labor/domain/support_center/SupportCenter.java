package pelican.co_labor.domain.support_center;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "support_center")
public class SupportCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long support_center_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String center_type; // 필드 이름을 'type'에서 'center_type'으로 변경

    @Column(nullable = false)
    private String phone; // 새로운 필드 추가

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

}
