package com.casestudy.backend.eauctionapp.model;

import java.math.BigDecimal;

import javax.validation.Valid;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "ProductBid")
public class ProductBid {
    @DynamoDBHashKey (attributeName = "productID")
    private String productID;
    @DynamoDBAttribute
    private String email;
    @Valid
    @DynamoDBAttribute
    private Buyer buyer;
    @DynamoDBAttribute
    private BigDecimal bidAmount;
}
