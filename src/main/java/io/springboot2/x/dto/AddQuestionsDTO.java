package io.springboot2.x.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class AddQuestionsDTO implements Serializable {
    @NotEmpty(message = "questionId is required")
    private String questionId;
    @NotEmpty(message = "question is required")
    private String question;
    @NotEmpty(message = "frequency is required")
    private String frequency;
    @NotEmpty(message = "type is required")
  
    private String type;
    @NotEmpty(message = "difficultyType is required")
    private String difficultyType;
	
	public String getQuestion() {
		return question;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public void setQuestion(String question) {
		this.question = question;
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

    
    
    
    
}
