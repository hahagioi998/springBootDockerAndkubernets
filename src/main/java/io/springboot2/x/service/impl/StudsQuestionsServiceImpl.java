package io.springboot2.x.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import io.springboot2.x.domain.StudsQuestions;
import io.springboot2.x.dto.StudsOptionsDTO;
import io.springboot2.x.dto.StudsDescriptionDTO;
import io.springboot2.x.dto.StudsDescriptionUpdateDTO;
import io.springboot2.x.dto.StudsQuestions2DTO;
import io.springboot2.x.dto.StudsQuestionsDTO;
import io.springboot2.x.dto.AddQuestionsDTO;
import io.springboot2.x.dto.StudsTagDTO;
import io.springboot2.x.repository.StudsQuestionsRepository;
import io.springboot2.x.service.IStudsQuestionsService;

@Service
public class StudsQuestionsServiceImpl implements IStudsQuestionsService {

    private static final String DESCRIPTION_URL="http://localhost:3333/STUDS/getSpecificDescription/{type}";
    private static final String OPTIONS_URL="http://localhost:4444/STUDS/options/{questionId}";
    private static final String TAG_URL="http://localhost:5555/STUDS/getSpecificTag/{questionId}";	
	
	
    @Autowired
    private StudsQuestionsRepository repo;
    
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public boolean addQuestions(AddQuestionsDTO addQuestionsDTO) {
        StudsQuestions studsQuestions = new StudsQuestions();
        BeanUtils.copyProperties(addQuestionsDTO, studsQuestions);
        boolean flag = repo.existsById(studsQuestions.getQuestionId());
        if (flag == true) {
            return false;
        }
        repo.save(studsQuestions);
        return true;

    }

    
    @Override
    public StudsQuestionsDTO readQuestions(long questionId) {


        Optional<StudsQuestions> opt = repo.findById(questionId);
        StudsQuestionsDTO  studsQuestionsDTO=new  StudsQuestionsDTO();
        if (opt.isPresent()) {
            StudsQuestions studsQuestions = opt.get();
            BeanUtils.copyProperties(studsQuestions, studsQuestionsDTO);
            StudsDescriptionDTO description = restTemplate.getForObject(DESCRIPTION_URL, StudsDescriptionDTO.class, studsQuestionsDTO.getType());
            studsQuestionsDTO.setDescription(description);
            //For call DEVICE_URL
            ParameterizedTypeReference<List<StudsOptionsDTO>> typeReference = new ParameterizedTypeReference<List<StudsOptionsDTO>>() {
            };
            ResponseEntity<List<StudsOptionsDTO>> re = restTemplate.exchange(OPTIONS_URL, HttpMethod.GET, null, typeReference, questionId);
            List<StudsOptionsDTO> options= re.getBody();
            studsQuestionsDTO.setOptions(options);
            //For call Tag
            StudsTagDTO tags = restTemplate.getForObject(TAG_URL, StudsTagDTO.class, questionId);
            studsQuestionsDTO.setTags(tags);
            return studsQuestionsDTO;
        }
        return studsQuestionsDTO;
    }
    

    @Override
    public StudsQuestions2DTO readQuestionsV2(long phoneNor) {
        Optional<StudsQuestions> opt = repo.findById(phoneNor);
        StudsQuestions2DTO  studsQuestions2DTO=new  StudsQuestions2DTO();
        if (opt.isPresent()) {
            StudsQuestions studsQuestions = opt.get();
            BeanUtils.copyProperties(studsQuestions, studsQuestions2DTO);
            return studsQuestions2DTO;
        }
        return studsQuestions2DTO;
    }


    @Override
    public boolean updateQuestions(AddQuestionsDTO addQuestionsDTO) {
        StudsQuestions studsQuestions = new StudsQuestions();
        BeanUtils.copyProperties(addQuestionsDTO, studsQuestions);
        boolean flag = repo.existsById(studsQuestions.getQuestionId());
        if (flag ==false ) {
            return false;
        }
        repo.saveAndFlush(studsQuestions);
        return true;

    }

    @Override
    public boolean deleteQuestionsById(long questionId) {
        if(repo.existsById(questionId)==true){
            repo.deleteById(questionId);
            return true;
        }
        return false;
    }

    @Override
    public boolean getUpdateQuestionsId(StudsDescriptionUpdateDTO studsDescriptionUpdateDTO) {
        if(repo.existsById(studsDescriptionUpdateDTO.getQuestionId())==true){
            repo.updateQuestions(studsDescriptionUpdateDTO.getQuestionId(),studsDescriptionUpdateDTO.getType());
            return true;
        }
        return false;
    }
    @Override
    public boolean updateQuestionsV2(StudsQuestions2DTO studsQuestions2DTO) {
        StudsQuestions studsQuestions = new StudsQuestions();
        BeanUtils.copyProperties(studsQuestions2DTO, studsQuestions);
        boolean flag = repo.existsById(studsQuestions.getQuestionId());
        if (flag ==false ) {
            return false;
        }
        StudsQuestions netflix=repo.findById(studsQuestions.getQuestionId()).get();
        studsQuestions.setFrequency(netflix.getFrequency());
        repo.saveAndFlush(studsQuestions);
        return true;

    }


	@Override
	public List<StudsQuestionsDTO> findRandamQuestions() {
		// TODO Auto-generated method stub
		List<StudsQuestions> questions = repo.findRandamQuestions();
		List<StudsQuestions> questionsList = questions.subList(questions.size()-3, questions.size());
		  List<StudsQuestionsDTO> questions3DTOList = new ArrayList<>();
		

		for (StudsQuestions studsQuestions : questionsList) {
			long questionId=studsQuestions.getQuestionId();
			if(studsQuestions.getQuestionId()<=0){
				StudsQuestionsDTO studsQuestionsDTO =new StudsQuestionsDTO();
				BeanUtils.copyProperties(studsQuestions, studsQuestionsDTO);
				//For call paln
				 StudsDescriptionDTO description = restTemplate.getForObject(DESCRIPTION_URL, StudsDescriptionDTO.class, studsQuestionsDTO.getType());
				 studsQuestionsDTO.setDescription(description);
				 
				 
				    //For call DEVICE_URL
		            ParameterizedTypeReference<List<StudsOptionsDTO>> typeReference = new ParameterizedTypeReference<List<StudsOptionsDTO>>() {
		            };
		            ResponseEntity<List<StudsOptionsDTO>> re = restTemplate.exchange(OPTIONS_URL, HttpMethod.GET, null, typeReference, questionId);
		            List<StudsOptionsDTO> options = re.getBody();
		            studsQuestionsDTO.setOptions(options);
		            
		            //For call Tag
		            StudsTagDTO tags = restTemplate.getForObject(TAG_URL, StudsTagDTO.class, questionId);
		            studsQuestionsDTO.setTags(tags);
		            questions3DTOList.add(studsQuestionsDTO);
			}
			
		}
	      
	        return questions3DTOList;
	}


	@Override
	public List<StudsQuestionsDTO> findAllQuestions() {
		// TODO Auto-generated method stub
		List<StudsQuestions> questionsList = repo.findAll();
		  List<StudsQuestionsDTO> questions3DTOList = new ArrayList<>();
		
		for (StudsQuestions studsQuestions : questionsList) {
			long questionId=studsQuestions.getQuestionId();
			if(studsQuestions.getQuestionId()<=0){
				StudsQuestionsDTO studsQuestionsDTO =new StudsQuestionsDTO();
				BeanUtils.copyProperties(studsQuestions, studsQuestionsDTO);
				//For call paln
				 StudsDescriptionDTO description = restTemplate.getForObject(DESCRIPTION_URL, StudsDescriptionDTO.class, studsQuestionsDTO.getType());
				 studsQuestionsDTO.setDescription(description);
				 
				 
				    //For call DEVICE_URL
		            ParameterizedTypeReference<List<StudsOptionsDTO>> typeReference = new ParameterizedTypeReference<List<StudsOptionsDTO>>() {
		            };
		            ResponseEntity<List<StudsOptionsDTO>> re = restTemplate.exchange(OPTIONS_URL, HttpMethod.GET, null, typeReference, questionId);
		            List<StudsOptionsDTO> options = re.getBody();
		            studsQuestionsDTO.setOptions(options);
		            
		            //For call Tag
		            StudsTagDTO tags = restTemplate.getForObject(TAG_URL, StudsTagDTO.class, questionId);
		            studsQuestionsDTO.setTags(tags);
		            questions3DTOList.add(studsQuestionsDTO);
			}
			
		}
	      
	        return questions3DTOList;
	}



	

}






