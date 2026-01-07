package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.JobPost;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {
	
	private final PostRepository repo;
    private final SequenceGeneratorService sequenceGenerator;

    public PostService(PostRepository repo,
                       SequenceGeneratorService sequenceGenerator) {
        this.repo = repo;
        this.sequenceGenerator = sequenceGenerator;
    }
	
	public Page<JobPost> getAllPost(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	public JobPost addPost(JobPost jp) {
		jp.setPostNo(
	            sequenceGenerator.getNextSequence("job_post_sequence")
	     );
		
		return repo.save(jp);
	}
	
	public List<JobPost> addManyPost(List<JobPost> posts) {
		
		for (JobPost jp : posts) {
            jp.setPostNo(
                sequenceGenerator.getNextSequence("job_post_sequence")
            );
        }
		
		return repo.saveAll(posts);
	}

	public JobPost deletePost(int postNo) {
		
		return repo.findByPostNo(postNo)
				.map(post -> {
					repo.delete(post);
					
					return post;
				})
				.orElseThrow(()-> new ResourceNotFoundException("Post not found of Post-No:" + postNo));
	}
}
