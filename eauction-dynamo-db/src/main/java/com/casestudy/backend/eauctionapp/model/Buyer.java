package com.casestudy.backend.eauctionapp.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(of = {"email"})
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class Buyer implements Serializable{
    @Email(message = "Please Enter Valid Eamil !")
    @NotEmpty(message = "Email Should Not be Empty !")
    @DynamoDBAttribute
    private String email;
    
    @NotEmpty(message = "First Name Should Not Be Null !")
    @Size(max = 30, min = 5, message = "First Name Should be min 5 chars and max 30 chars !")
    @DynamoDBAttribute
    private String firstName;
   
    @NotEmpty(message = "First Name Should Not Be Null !")
    @Size(max = 25, min = 3, message = "Last Name Should be min 3 chars and max 25 chars !")
    @DynamoDBAttribute
    private String lastName;
    
    @Valid
    @DynamoDBAttribute
    private Address address;
    
    @Digits(integer = 10, fraction = 0)
    @NotEmpty(message = "Phone Number Should Not be Empty !")
    @Size(max = 10, min = 10, message = "Phone Number Should be 10 Digits !")
    @DynamoDBAttribute 
    private String phone;
}
