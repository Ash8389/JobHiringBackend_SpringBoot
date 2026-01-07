package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JobPost;
import com.example.demo.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Job Posts API", description = "APIs for managing job posts")
@RestController
@RequestMapping("/posts")
public class PostController {
	
	private final PostService ps;
	private  final Logger log = LoggerFactory.getLogger(PostController.class);
	
	public PostController(PostService ps) {
		this.ps = ps;
	}
	
	@Operation(summary = "Get all job posts")
	@GetMapping
	public Page<JobPost> getAllPost(
//			@ParameterObject Pageable pageable
			@PageableDefault(size = 5, sort = "postNo") Pageable pageable
	) {
		log.info("Fetching all the job posts with pagable: {}", pageable);
		
		Page<JobPost> posts = ps.getAllPost(pageable);
		
		log.info("Fetched {} posts", posts.getNumberOfElements());
		
		return posts;
	}
	
	@Operation(summary = "Create a single job post")
	@PostMapping
	public JobPost addPost(@RequestBody JobPost jp) {
		log.info("Adding job post: {}", jp.getTitle());

		JobPost newJob = ps.addPost(jp);
		
		log.info("Job post Added: {}", newJob.getPostNo());
		
		return newJob;
	}
	
	@Operation(summary = "Create multiple job posts in bulk")
	@PostMapping("/bulk")
	public List<JobPost> addPost(@RequestBody List<JobPost> jp) {
		
		log.info("Adding multiple job post: {}", jp.size());

		List<JobPost> newJobs = ps.addManyPost(jp);
		
		log.info("All Job post Added: {}", newJobs.size());
		
		return newJobs;
	}
	
	@Operation(summary = "Delete a single job posts")
	@DeleteMapping("/{postNo}")
	public JobPost deletePost(@PathVariable("postNo") int postNo) {
		
		log.info("Job deleting request of Post-No: {}", postNo);
		
		JobPost del = ps.deletePost(postNo);
		
		log.info("Job of Post-No: {} is deleted", del.getPostNo());
		
		return del;
	}
}
