package pelican.co_labor.domain.enterprise_queue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pelican.co_labor.dto.EnterpriseQueueDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "enterprise_queue")
public class EnterpriseQueue {

    @Id
    @Column(unique = true)
    //사업자 등록 번호
    private String enterprise_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address1;

    @Column(nullable = false)
    private String address2;

    @Column(nullable = true)
    private String address3;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String phone_number;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    public static EnterpriseQueue toEnterpriseQueue(EnterpriseQueueDTO enterpriseQueueDTO) {
        EnterpriseQueue enterpriseQueue = new EnterpriseQueue();
        enterpriseQueue.setEnterprise_id(enterpriseQueueDTO.getEnterpriseId());
        enterpriseQueue.setName(enterpriseQueueDTO.getName());
        enterpriseQueue.setAddress1(enterpriseQueueDTO.getAddress1());
        enterpriseQueue.setAddress2(enterpriseQueueDTO.getAddress2());
        enterpriseQueue.setAddress3(enterpriseQueueDTO.getAddress3());
        enterpriseQueue.setType(enterpriseQueueDTO.getType());
        enterpriseQueue.setPhone_number(enterpriseQueueDTO.getPhoneNumber());
        enterpriseQueue.setDescription(enterpriseQueueDTO.getDescription());

        return enterpriseQueue;
    }

}
