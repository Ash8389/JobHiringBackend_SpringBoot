package com.example.demo.model;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Job Post information")
@Document(collection = "jobPost")
public class JobPost {
	
	@Schema(description = "Auto generated post number", example = "101")
	private int postNo;
	 
	@Schema(description = "Job title", example = "Java Backend Developer")
	private String title;
	
	@Schema(description = "Job location", example = "Bangalore")
	private String place;
	
	@Schema(description = "Required experience in years", example = "3")
	private int exp;
	
	@Schema(description = "Skill requirements")
	private String requirements[];
	
	
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
