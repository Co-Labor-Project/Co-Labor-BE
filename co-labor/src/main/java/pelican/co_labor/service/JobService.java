package pelican.co_labor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.repository.job.JobRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }

    public Optional<Job> incrementAndGetJobById(Long jobId) {
        return jobRepository.findById(jobId).map(job -> {
            job.setViews(job.getViews() + 1);
            jobRepository.save(job);
            return job;
        });
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Optional<Job> updateJob(Long jobId, Job jobDetails) {
        return jobRepository.findById(jobId).map(job -> {
            job.setTitle(jobDetails.getTitle());
            job.setDescription(jobDetails.getDescription());
            job.setRequirement(jobDetails.getRequirement());
            job.setViews(jobDetails.getViews());
            job.setDead_date(jobDetails.getDead_date());
            job.setModified_at(jobDetails.getModified_at());
            return jobRepository.save(job);
        });
    }

    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}
