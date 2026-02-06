package com.example.demo.model;

import java.util.Arrays;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "jobPost")
public class JobPost {
	@Id
	private String id;
	private int postNo;
	@NotBlank
	private String title;
	private String place;
	private int exp;
	private String requirements[];
	private String recruiterId;

	public String getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public String[] getRequirements() {
		return requirements;
	}
	public void setRequirements(String[] requirements) {
		this.requirements = requirements;
	}
	
	@Override
	public String toString() {
		return "JobPost [title=" + title + ", place=" + place + ", exp=" + exp + ", requirements="
				+ Arrays.toString(requirements) + "]";
	}
	
}
