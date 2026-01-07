package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.JobPost;

public interface PostRepository extends MongoRepository<JobPost, String> {

	Optional<JobPost> findByPostNo(int postNo);

}
