package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.domain.job.JobEng;
import pelican.co_labor.service.AuthService;
import pelican.co_labor.service.JobService;
import pelican.co_labor.repository.enterprise_user.EnterpriseUserRepository;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;
    private final AuthService authService;
    private final EnterpriseUserRepository enterpriseUserRepository;

    @Autowired
    public JobController(JobService jobService, AuthService authService, EnterpriseUserRepository enterpriseUserRepository) {
        this.jobService = jobService;
        this.authService = authService;
        this.enterpriseUserRepository = enterpriseUserRepository;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{job_id}")
    public ResponseEntity<Job> getJobById(@PathVariable("job_id") Long job_id) {
        Optional<Job> job = jobService.incrementAndGetJobById(job_id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Job createJob(@RequestParam("job") String jobJson,
                         @RequestParam("image") MultipartFile image,
                         @RequestParam("enterprise_user_id") String enterpriseUser) throws IOException {
        System.out.println("Received enterprise_user_id: " + enterpriseUser);

        Job job = jobService.mapJobFromJson(jobJson);
        authService.findEnterpriseUserById(enterpriseUser).ifPresentOrElse(job::setEnterpriseUser, () -> {
            throw new IllegalArgumentException("Invalid enterprise user ID: " + enterpriseUser);
        });

        String enterpriseId = enterpriseUserRepository.findEnterpriseIDByUserId(enterpriseUser);
        System.out.println("Found enterprise_id: " + enterpriseId);

        authService.findEnterpriseById(enterpriseId).ifPresent(job::setEnterprise);
        return jobService.createJob(job, image);
    }



    @PutMapping("/{job_id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long job_id,
                                         @RequestParam("jobDetails") String jobDetailsJson,
                                         @RequestParam("image") MultipartFile image) throws IOException {
        Job jobDetails = jobService.mapJobFromJson(jobDetailsJson);
        Optional<Job> updatedJob = jobService.updateJob(job_id, jobDetails, image);
        return updatedJob.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{job_id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long job_id) {
        jobService.deleteJob(job_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("path/to/save/images").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 영어 채용 공고 조회 엔드포인트 추가

    @GetMapping("/eng")
    public List<JobEng> getAllJobsEng() {
        return jobService.getAllJobsEng();
    }

    @GetMapping("/eng/{job_id}")
    public ResponseEntity<JobEng> getJobEngById(@PathVariable("job_id") Long job_id) {
        Optional<JobEng> job = jobService.incrementAndGetJobEngById(job_id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
