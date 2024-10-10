package pelican.co_labor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.hospital.Hospital;
import pelican.co_labor.service.HospitalService;

import java.util.List;

@Tag(name = "Hospital", description = "병원 관련 API")
@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Operation(summary = "병원 데이터 Fetch", description = "외부 API로부터 병원 데이터를 가져와 저장합니다.")
    @GetMapping("/api/fetch")
    public void fetchHospitalData() {
        hospitalService.fetchAndSaveHospitalData();
    }

    @Operation(summary = "특정 지역의 병원 데이터 Fetch", description = "주어진 지역의 병원 데이터를 외부 API로부터 가져와 저장합니다.")
    @GetMapping("/fetch/{region}")
    public void fetchHospitalDataByRegion(
            @Parameter(description = "지역 이름") @PathVariable String region) {
        hospitalService.fetchAndSaveHospitalData(region);
    }

    @Operation(summary = "모든 병원 목록 조회", description = "등록된 모든 병원 데이터를 조회합니다.")
    @GetMapping("/all")
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @Operation(summary = "특정 지역의 병원 목록 조회", description = "주어진 지역의 병원 데이터를 조회합니다.")
    @GetMapping("/region/{region}")
    public List<Hospital> getHospitalsByRegion(
            @Parameter(description = "지역 이름") @PathVariable String region) {
        return hospitalService.getHospitalsByRegion(region);
    }
}
