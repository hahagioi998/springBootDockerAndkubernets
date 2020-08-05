package io.springboot2.x.dto;

import java.io.Serializable;
import java.util.List;

public class StudsQuestionsDTO implements Serializable {

    private String questionId;

    private String frequency;
    private String type;
    private String difficultyType;


    private StudsDescriptionDTO description;
    
    private List<StudsOptionsDTO> options;
    
    private StudsTagDTO tags;
    
    
    
	
	public StudsTagDTO getTags() {
		return tags;
	}
	public void setTags(StudsTagDTO tags) {
		this.tags = tags;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDifficultyType() {
		return difficultyType;
	}
	public void setDifficultyType(String difficultyType) {
		this.difficultyType = difficultyType;
	}
	public StudsDescriptionDTO getDescription() {
		return description;
	}
	public void setDescription(StudsDescriptionDTO description) {
		this.description = description;
	}
	public List<StudsOptionsDTO> getOptions() {
		return options;
	}
	public void setOptions(List<StudsOptionsDTO> options) {
		this.options = options;
	}

}
