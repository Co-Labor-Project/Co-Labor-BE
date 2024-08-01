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
    @Column(name = "id", nullable = false)
    private Long rnum;

    @Column(name = "address", nullable = false)
    private String dutyAddr;

    @Column(name = "name", nullable = false)
    private String dutyName;

    @Column(name = "phone", nullable = false)
    private String dutyTel1;

    @Column(name = "latitude", nullable = false)
    private double wgs84Lat;

    @Column(name = "longitude", nullable = false)
    private double wgs84Lon;
}
