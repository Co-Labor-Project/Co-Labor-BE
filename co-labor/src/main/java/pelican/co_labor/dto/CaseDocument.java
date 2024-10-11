package pelican.co_labor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseDocument {
    private String 사건명;
    private String 사건종류명;
    private String 판시사항;
    private String 판결요지;
    private String 참조조문;
    private String 사건번호;

    // 고유 문서 이름을 생성하는 메서드
    public String generateCaseDocumentId() {
        return 사건번호;
    }
}
