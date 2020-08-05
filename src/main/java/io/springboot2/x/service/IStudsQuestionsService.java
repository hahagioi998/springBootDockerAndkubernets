package io.springboot2.x.service;

import java.util.List;

import io.springboot2.x.dto.*;

public interface IStudsQuestionsService {

    boolean addQuestions(AddQuestionsDTO addQuestionsDTO);
    StudsQuestionsDTO readQuestions(long questionId);
    StudsQuestions2DTO readQuestionsV2(long questionId);
    boolean updateQuestions(AddQuestionsDTO addQuestionsDTO);
    boolean updateQuestionsV2(StudsQuestions2DTO studsQuestions2DTO);
    boolean deleteQuestionsById(long questionId);

    boolean getUpdateQuestionsId(StudsDescriptionUpdateDTO studsDescriptionUpdateDTO);

    public List<StudsQuestionsDTO> findAllQuestions();
    public List<StudsQuestionsDTO> findRandamQuestions();
    
}
