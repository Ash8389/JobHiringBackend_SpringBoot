package com.example.demo.repository;

import com.example.demo.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {
    boolean existsByJobIdAndCandidateId(String jobId, String userId);
}
