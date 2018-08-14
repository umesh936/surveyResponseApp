package com.interview.market.surveyResponse.services;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.market.surveyResponse.entity.UserResponse;
import com.interview.market.surveyResponse.entity.UserResponseRepository;

@Service
public class ResponseService {

    @Resource
    private UserResponseRepository userResponseRepository;
    
    @Autowired
    MongoTemplate mongoTemplate;
    
	public void saveRepsonse (UserResponse userResponse) {
	    userResponseRepository.save(userResponse);
	}
	
	public String getResponse(Integer questionId, Integer surveyId) throws JsonProcessingException {
	    String mapFunction = "function() {" 
	            + "if (this.questionId == " + questionId + " && this.surveyId == '" + surveyId + "') {"
	                     + "emit(this.optionId, 1);"
	                 + "}" 
	            + "}";
	    
	    String reduceFunction = "function (optionId, value) {return Array.sum(value);}";
	    MapReduceResults<Object> mapReduce = mongoTemplate.mapReduce("responses", mapFunction, reduceFunction, Object.class);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    System.out.println(mapper.writeValueAsString(mapReduce.getRawResults()));
	    return null;
	}
}
