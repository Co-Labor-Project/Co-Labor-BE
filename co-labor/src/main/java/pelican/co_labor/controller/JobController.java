package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.service.JobService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{job_id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long job_id) {
        Optional<Job> job = jobService.getJobById(job_id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping("/{job_id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long job_id, @RequestBody Job jobDetails) {
        Optional<Job> updatedJob = jobService.updateJob(job_id, jobDetails);
        return updatedJob.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{job_id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long job_id) {
        jobService.deleteJob(job_id);
        return ResponseEntity.noContent().build();
    }
}