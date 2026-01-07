package com.example.demo.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
 

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.DatabaseSequence;

@Service
public class SequenceGeneratorService {
	
	private MongoOperations mongoOperations;
	
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	public int getNextSequence(String seqName) {
		
		Query query = new Query(Criteria.where("_id").is(seqName));
		Update update = new Update().inc("seq", 1);
		
		 DatabaseSequence counter = mongoOperations.findAndModify(
	                query,
	                update,
	                options().returnNew(true).upsert(true),
	                DatabaseSequence.class
	        );

	        return counter.getSeq();
		
	}
}
