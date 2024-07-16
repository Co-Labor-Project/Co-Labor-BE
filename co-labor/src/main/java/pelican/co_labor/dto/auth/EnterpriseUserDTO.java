package pelican.co_labor.dto.auth;

import lombok.*;
import pelican.co_labor.domain.enterprise.Enterprise;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnterpriseUserDTO {
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String name;
    private Enterprise enterprise;
}
