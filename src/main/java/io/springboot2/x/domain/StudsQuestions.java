package io.springboot2.x.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STUDS_QUESTIONS")
public class StudsQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionId;
    private String question;
    private String frequency;
    private String type;
    private String difficultyType;
    
    
	
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
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
