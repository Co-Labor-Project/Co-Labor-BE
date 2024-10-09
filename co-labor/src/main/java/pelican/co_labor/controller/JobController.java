package pelican.co_labor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Tag(name = "Job", description = "채용 공고 관련 API")
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

    @Operation(summary = "모든 채용 공고 조회", description = "등록된 모든 채용 공고를 조회합니다.")
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @Operation(summary = "특정 채용 공고 조회", description = "채용 공고 ID로 특정 채용 공고를 조회합니다.")
    @GetMapping("/{job_id}")
    public ResponseEntity<Job> getJobById(
            @Parameter(description = "채용 공고 ID") @PathVariable("job_id") Long job_id) {
        Optional<Job> job = jobService.incrementAndGetJobById(job_id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "채용 공고 생성", description = "새로운 채용 공고를 생성합니다. JSON 형식의 공고 정보와 이미지 파일을 함께 업로드할 수 있습니다.")
    @PostMapping
    public Job createJob(
            @Parameter(description = "채용 공고 정보(JSON 형식)") @RequestParam("job") String jobJson,
            @Parameter(description = "채용 공고 이미지 파일") @RequestParam("image") MultipartFile image,
            @Parameter(description = "기업 회원 ID") @RequestParam("enterprise_user_id") String enterpriseUser) throws IOException {
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

    @Operation(summary = "채용 공고 수정", description = "채용 공고 ID에 해당하는 채용 공고를 수정합니다.")
    @PutMapping("/{job_id}")
    public ResponseEntity<Job> updateJob(
            @Parameter(description = "채용 공고 ID") @PathVariable Long job_id,
            @Parameter(description = "수정할 채용 공고 정보(JSON 형식)") @RequestParam("jobDetails") String jobDetailsJson,
            @Parameter(description = "수정할 이미지 파일") @RequestParam("image") MultipartFile image) throws IOException {
        Job jobDetails = jobService.mapJobFromJson(jobDetailsJson);
        Optional<Job> updatedJob = jobService.updateJob(job_id, jobDetails, image);
        return updatedJob.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "채용 공고 삭제", description = "채용 공고 ID에 해당하는 채용 공고를 삭제합니다.")
    @DeleteMapping("/{job_id}")
    public ResponseEntity<Void> deleteJob(
            @Parameter(description = "채용 공고 ID") @PathVariable Long job_id) {
        jobService.deleteJob(job_id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "채용 공고 이미지 파일 제공", description = "서버에 저장된 채용 공고 이미지를 제공합니다.")
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(
            @Parameter(description = "이미지 파일 이름") @PathVariable String filename) {
        try {
            Path filePath = Paths.get("path/to/save/images").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);

                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @Operation(summary = "영어로 모든 채용 공고 조회", description = "영어로 등록된 모든 채용 공고를 조회합니다.")
    @GetMapping("/eng")
    public List<JobEng> getAllJobsEng() {
        return jobService.getAllJobsEng();
    }

    @Operation(summary = "영어로 특정 채용 공고 조회", description = "영어로 된 채용 공고 ID로 특정 채용 공고를 조회합니다.")
    @GetMapping("/eng/{job_id}")
    public ResponseEntity<JobEng> getJobEngById(
            @Parameter(description = "영어 채용 공고 ID") @PathVariable("job_id") Long job_id) {
        Optional<JobEng> job = jobService.incrementAndGetJobEngById(job_id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
