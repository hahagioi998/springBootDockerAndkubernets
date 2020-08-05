package io.springboot2.x.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.springboot2.x.dto.StudsDescriptionUpdateDTO;
import io.springboot2.x.dto.StudsQuestions2DTO;
import io.springboot2.x.dto.StudsQuestionsDTO;
import io.springboot2.x.dto.AddQuestionsDTO;
import io.springboot2.x.service.IStudsQuestionsService;

@RestController
@Validated
public class StudsQuestionsController {
   
    @Autowired
    private IStudsQuestionsService service;

    
    @GetMapping("hello")
    public String get() {
    	return "hell0";
    }
    
    
    @PostMapping("/question/add")
    public boolean addQuestion(@RequestBody @Valid AddQuestionsDTO addQuestionsDTO){
        return service.addQuestions(addQuestionsDTO);
    }
      
    
    @GetMapping("/findAllQuestions")
    public ResponseEntity<List<StudsQuestionsDTO>> findAllQuestions(){
        List<StudsQuestionsDTO> questions3DTO=service.findAllQuestions();
        return new ResponseEntity<List<StudsQuestionsDTO>>(questions3DTO, HttpStatus.OK);
    }
   

    @GetMapping("/question/viewProfile/{questionId}")
    public  ResponseEntity<Object> readQuestion(@PathVariable("questionId") long questionId){
        StudsQuestionsDTO studsQuestionsDTO=service.readQuestions(questionId);
        if(studsQuestionsDTO.getQuestionId()!=null) {  
            return new ResponseEntity<Object>(studsQuestionsDTO ,HttpStatus.OK);
        }
        return new ResponseEntity<Object>("Data is not available!", HttpStatus.OK);
    }
    @PutMapping("/question/update")
    boolean updateQuestions(@RequestBody @Valid AddQuestionsDTO addQuestionsDTO){
        return service.updateQuestions(addQuestionsDTO);
    }
    @PostMapping("/question/v2/update")
    boolean updateQuestionsV2(@RequestBody @Valid StudsQuestions2DTO studsQuestions2DTO){
        return service.updateQuestionsV2(studsQuestions2DTO);
    }
    @DeleteMapping("/question/delete")
    boolean deleteQuestionsById(@RequestParam long questionId){
        return service.deleteQuestionsById(questionId);
    }
    @PostMapping("/question/update/type")
    public boolean getUpdatePlanId(@RequestBody @Valid StudsDescriptionUpdateDTO studsDescriptionUpdateDTO) {
        return service.getUpdateQuestionsId(studsDescriptionUpdateDTO);
    }
    @GetMapping("/findRandamQuestions")
    public ResponseEntity<List<StudsQuestionsDTO>> findRandamQuestions(){
        List<StudsQuestionsDTO> questions3DTO=service.findRandamQuestions();
        return new ResponseEntity<List<StudsQuestionsDTO>>(questions3DTO, HttpStatus.OK);
    }

}
