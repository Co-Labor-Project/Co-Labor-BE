package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Service
public class HospitalServiceInitializer {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalServiceInitializer(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        hospitalService.fetchAllHospitalData();
    }
}
