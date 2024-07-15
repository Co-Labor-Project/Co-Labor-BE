package pelican.co_labor.domain.labor_user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pelican.co_labor.dto.auth.LaborUserDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "labor_user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class LaborUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String labor_user_id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    public static LaborUser toLaborUser(LaborUserDTO laborUserDTO) {
        LaborUser laborUser = new LaborUser();
        laborUser.setLabor_user_id(laborUserDTO.getUsername());
        laborUser.setPassword(laborUserDTO.getPassword());
        laborUser.setName(laborUserDTO.getName());
        laborUser.setEmail(laborUserDTO.getEmail());
        return laborUser;
    }

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }
}
