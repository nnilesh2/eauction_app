package com.casestudy.backend.eauctionapp.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class Address implements Serializable{
    @DynamoDBAttribute
    private String addressLine;
    @DynamoDBAttribute
    private String city;
    @DynamoDBAttribute
    private String state;
    @DynamoDBAttribute
    private String pin;
}
