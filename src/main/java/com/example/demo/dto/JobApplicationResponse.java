package com.example.demo.dto;

import com.example.demo.model.ApplicationStatus;

public class JobApplicationResponse {
        private String id;
        private String jobId;
        private ApplicationStatus status;

    public JobApplicationResponse(String id, String jobId, ApplicationStatus status) {
        this.id = id;
        this.jobId = jobId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getJobId() {
        return jobId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}
