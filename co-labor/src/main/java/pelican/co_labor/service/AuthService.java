package pelican.co_labor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pelican.co_labor.dto.auth.EnterpriseUserDTO;
import pelican.co_labor.dto.auth.LaborUserDTO;
import pelican.co_labor.repository.auth.EnterpriseUserRepository;
import pelican.co_labor.repository.auth.LaborUserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final LaborUserRepository laborUserRepository;
    private final EnterpriseUserRepository enterpriseUserRepository;

    public void registerLaborUser(LaborUserDTO laborUserDTO) {
        // 사용자 등록 로직 (예: 데이터베이스에 사용자 저장)
    }

    public void registerEnterpriseUser(EnterpriseUserDTO enterpriseUserDTO) {
        // 사용자 등록 로직 (예: 데이터베이스에 사용자 저장)
    }

    public boolean authenticateUser(String username, String password) {
        // 사용자 인증 로직 (예: 데이터베이스에서 사용자 정보 확인)
        return true; // 예시로 항상 true 반환
    }
}
