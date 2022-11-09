package com.casestudy.backend.eauctionapp.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class Product implements Serializable{
    @DynamoDBAttribute
    private String productID;
    @NotEmpty(message = "Product Name Should Not Be Null !")
    @Size(max = 30, min = 5, message = "Product Name Should be min 5 chars and max 30 chars !")
    @DynamoDBAttribute
    private String productName;
    @DynamoDBAttribute
    private String shortDescription;
    @DynamoDBAttribute
    private String detailedDescription;
    @DynamoDBAttribute
    private String category;
}