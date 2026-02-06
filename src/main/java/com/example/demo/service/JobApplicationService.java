package com.example.demo.service;

import com.example.demo.model.ApplicationStatus;
import com.example.demo.model.JobApplication;
import com.example.demo.repository.JobApplicationRepository;
import com.example.demo.repository.JobPostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.model.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobApplication apply(String jobId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        if(jobApplicationRepository.existsByJobIdAndCandidateId(jobId, userDetail.getUserId())){
            throw new IllegalStateException("All ready Applied for this job");
        }

        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobId(jobId);
        jobApplication.setCandidateId(userDetail.getUserId());
        jobApplication.setStatus(ApplicationStatus.APPLIED);

        return jobApplicationRepository.save(jobApplication);
    }
}
