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
    private Long support_center_id; // "순번"을 primary key로 사용

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String center_type;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private double latitude; // 위도 추가

    @Column(nullable = false)
    private double longitude; // 경도 추가

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

}
