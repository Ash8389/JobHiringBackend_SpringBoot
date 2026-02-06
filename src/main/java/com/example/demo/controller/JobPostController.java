package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.service.JobPostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Job Posts API", description = "APIs for managing job posts")
@RestController
@RequestMapping("/post")
public class JobPostController {

	private final JobPostService ps;

	public JobPostController(JobPostService ps) {
		this.ps = ps;
	}

	@Operation(summary = "Get all job posts")
	@GetMapping
	public Page<JobPost> getAllPost(
			// @ParameterObject Pageable pageable
			@PageableDefault(size = 5, sort = "postNo") Pageable pageable)
	{
		return ps.getAllPost(pageable);
	}

	@Operation(summary = "Create a single job post")
	@PostMapping
	public JobPost addPost(@RequestBody JobPost jp) {
		return ps.addPost(jp);
	}

	@Operation(summary = "Create multiple job posts in bulk")
	@PostMapping("/bulk")
	public List<JobPost> addPost(@RequestBody List<JobPost> jp) {
		return ps.addManyPost(jp);
	}

	@Operation(summary = "Delete s single job posts")
	@DeleteMapping("/{postNo}")
	public JobPost deletePost(@PathVariable("postNo") int postNo) {

		return ps.deletePost(postNo);
	}
}
