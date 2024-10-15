package pelican.co_labor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pelican.co_labor.domain.job.Job;
import pelican.co_labor.domain.job.JobEng;
import pelican.co_labor.dto.JobUpdatedDTO;
import pelican.co_labor.repository.job.JobEngRepository;
import pelican.co_labor.repository.job.JobRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class JobService {

    private final JobRepository jobRepository;
    private final ObjectMapper objectMapper;
    private final JobEngRepository jobEngRepository;

    @Autowired
    public JobService(JobRepository jobRepository, ObjectMapper objectMapper, JobEngRepository jobEngRepository) {
        this.jobRepository = jobRepository;
        this.objectMapper = objectMapper;
        this.jobEngRepository = jobEngRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<JobEng> getAllJobsEng() {
        return jobEngRepository.findAll();
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

    public Optional<JobEng> incrementAndGetJobEngById(Long jobId) {
        return jobEngRepository.findById(jobId).map(job -> {
            job.setViews(job.getViews() + 1);
            jobEngRepository.save(job);
            return job;
        });
    }


    @Transactional
    public Job createJob(Job job, MultipartFile image) {
        try {
            if (image != null && !image.isEmpty()) {
                String imagePath = saveImage(image);
                job.setImageName(imagePath);
            }
            return jobRepository.save(job);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

    @Transactional
    public Optional<Job> updateJob(Long jobId, JobUpdatedDTO jobUpdatedDTO, MultipartFile image) {
        return jobRepository.findById(jobId).map(job -> {
            if (jobUpdatedDTO.getTitle() != null) {
                job.setTitle(jobUpdatedDTO.getTitle());
            }
            if (jobUpdatedDTO.getDescription() != null) {
                job.setDescription(jobUpdatedDTO.getDescription());
            }
            if (jobUpdatedDTO.getRequirement() != null) {
                job.setRequirement(jobUpdatedDTO.getRequirement());
            }
            if (jobUpdatedDTO.getJobRole() != null) {
                job.setJobRole(jobUpdatedDTO.getJobRole());
            }
            if (jobUpdatedDTO.getExperience() != null) {
                job.setExperience(jobUpdatedDTO.getExperience());
            }
            if (jobUpdatedDTO.getEmploymentType() != null) {
                job.setEmploymentType(jobUpdatedDTO.getEmploymentType());
            }
            if (jobUpdatedDTO.getAddress1() != null) {
                job.setAddress1(jobUpdatedDTO.getAddress1());
            }
            if (jobUpdatedDTO.getAddress2() != null) {
                job.setAddress2(jobUpdatedDTO.getAddress2());
            }
            if (jobUpdatedDTO.getAddress3() != null) {
                job.setAddress3(jobUpdatedDTO.getAddress3());
            }
            if (jobUpdatedDTO.getSkills() != null) {
                job.setSkills(jobUpdatedDTO.getSkills());
            }
            if (jobUpdatedDTO.getDeadDate() != null) {
                job.setDeadDate(jobUpdatedDTO.getDeadDate());
            }

            job.setModified_at(LocalDateTime.now());

            if (image != null && !image.isEmpty()) {
                try {
                    if (job.getImageName() != null) {
                        Files.deleteIfExists(Paths.get(job.getImageName()));
                    }
                    String imagePath = saveImage(image);
                    job.setImageName(imagePath);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to save image", e);
                }
            }

            return jobRepository.save(job);
        });
    }

    public void deleteJob(Long jobId) {
        jobRepository.findById(jobId).ifPresent(job -> {
            try {
                Files.deleteIfExists(Paths.get(job.getImageName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            jobRepository.deleteById(jobId);
        });
    }

    private String saveImage(MultipartFile image) throws IOException {
        String imageDirectory = "path/to/save/images"; // 원하는 이미지 저장 경로 설정
        String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path imagePath = Paths.get(imageDirectory, imageName);
        Files.createDirectories(imagePath.getParent());
        Files.write(imagePath, image.getBytes());
        return imageName; // 전체 경로 대신 파일 이름만 반환
    }

    public Job mapJobFromJson(String jobJson) throws IOException {
        return objectMapper.readValue(jobJson, Job.class);
    }

    @Transactional
    public void deleteExpiredJobs() {
        LocalDate today = LocalDate.now();
        jobRepository.deleteByDeadDateBefore(today);
    }
}
