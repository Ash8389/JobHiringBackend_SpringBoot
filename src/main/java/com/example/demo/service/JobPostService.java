package com.example.demo.service;

import java.util.List;

import com.example.demo.security.model.CustomUserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.JobPost;
import com.example.demo.repository.JobPostRepository;

@Service
public class JobPostService {
	
	private final JobPostRepository repo;
//    private final SequenceGeneratorService sequenceGenerator;

    public JobPostService(JobPostRepository repo,
						  SequenceGeneratorService sequenceGenerator) {
        this.repo = repo;
//        this.sequenceGenerator = sequenceGenerator;
    }
	
	public Page<JobPost> getAllPost(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	public JobPost addPost(JobPost jp) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
		jp.setRecruiterId(userDetail.getUserId());

//		jp.setPostNo(
//	            sequenceGenerator.getNextSequence("job_post_sequence")
//	     );
		
		return repo.save(jp);
	}
	
	public List<JobPost> addManyPost(List<JobPost> posts) {
		
//		for (JobPost jp : posts) {
//            jp.setPostNo(
//                sequenceGenerator.getNextSequence("job_post_sequence")
//            );
//        }
		
		return repo.saveAll(posts);
	}

	public JobPost deletePost(int postNo) {
		
		return repo.deleteByPostNo(postNo);
	}
}
