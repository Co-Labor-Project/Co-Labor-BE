package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pelican.co_labor.domain.hospital.Hospital;
import pelican.co_labor.service.HospitalService;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/all")
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }
}
