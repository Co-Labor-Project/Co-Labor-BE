package pelican.co_labor.domain.hospital;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dutyAddr;

    @Column(nullable = false)
    private String dutyName;

    @Column(nullable = false)
    private String dutyTel1;

    @Column(nullable = false)
    private int rnum;

    @Column(nullable = false)
    private double wgs84Lat;

    @Column(nullable = false)
    private double wgs84Lon;
}
