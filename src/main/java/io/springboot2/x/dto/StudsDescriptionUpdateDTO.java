package io.springboot2.x.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class StudsDescriptionUpdateDTO {
    @NotEmpty(message = "question id is required")
    private long questionId;
    @NotEmpty(message = "type is required")
    private String type;
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

   
}
