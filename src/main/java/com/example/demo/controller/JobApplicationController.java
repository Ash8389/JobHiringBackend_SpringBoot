package com.example.demo.controller;

import com.example.demo.dto.JobApplicationResponse;
import com.example.demo.model.JobApplication;
import com.example.demo.service.JobApplicationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }
    @PostMapping("/{id}")
    public JobApplicationResponse apply(@PathVariable("id") String jobId){
        JobApplication application = jobApplicationService.apply(jobId);
        return new JobApplicationResponse(application.getId(), application.getJobId(), application.getStatus());
    }
}
