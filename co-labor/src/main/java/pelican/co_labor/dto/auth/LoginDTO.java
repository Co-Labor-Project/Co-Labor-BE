package pelican.co_labor.dto.auth;

import lombok.*;
import pelican.co_labor.domain.enterprise.Enterprise;
import pelican.co_labor.domain.enterprise_user.EnterpriseUser;

@Getter
@Setter
@ToString
public class LoginDTO {
    private String username;
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
