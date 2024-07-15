package pelican.co_labor.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnterpriseUserDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String enterpriseID;
}
