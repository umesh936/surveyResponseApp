package com.interview.market.surveyResponse.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.interview.market.surveyResponse.entity.UserResponse;
import com.interview.market.surveyResponse.services.ResponseService;

@RestController(value = "/res/v1/")
public class ResponseController {

    @Resource
    ResponseService responseService;
    
    
    @RequestMapping(value = "response", method = RequestMethod.POST)
    public ResponseEntity<Void> response (UserResponse userResponse) {
        responseService.saveRepsonse(userResponse);
        return ResponseEntity.accepted().build();
    }
    
    @RequestMapping(value = "response", method = RequestMethod.GET)
    public String aggregate (@RequestParam Integer questionId, @RequestParam Integer surveyId) throws JsonProcessingException {
        return responseService.getResponse(questionId, surveyId);
    }
}
