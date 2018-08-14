package com.interview.market.surveyResponse.entity;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "responses")
public class UserResponse {

    private UUID _id;
    
    private Integer surveryId;
    
    private Integer questionId;
    
    private Integer optionId;
}
