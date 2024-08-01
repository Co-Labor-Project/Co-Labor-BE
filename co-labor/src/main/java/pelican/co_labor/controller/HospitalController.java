package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/fetch")
    public void fetchHospitalData() {
        hospitalService.fetchAndSaveHospitalData();
    }

    @GetMapping("/fetch/{region}")
    public void fetchHospitalDataByRegion(@PathVariable String region) {
        hospitalService.fetchAndSaveHospitalData(region);
    }

    @GetMapping("/all")
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/region/{region}")
    public List<Hospital> getHospitalsByRegion(@PathVariable String region) {
        return hospitalService.getHospitalsByRegion(region);
    }
}
