package com.casestudy.backend.eauctionapp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
@DynamoDBTable(tableName = "ProductAuction")
public class ProductAuction {
    @DynamoDBHashKey (attributeName = "productID")
    private String productID;
    @DynamoDBAttribute
    private String email;
    @Valid
    @DynamoDBAttribute
    private Seller seller;
    @Valid
    @DynamoDBAttribute
    private Product product;
    @Positive(message = "Start Price should be Number and should be greater than Zero !")
    @NotNull(message = "Start Price Should Not Be Null !")
    @DynamoDBAttribute
    private BigDecimal startPrice;
    @Future(message = "Bid End Date Should be Future Date !")
    @DynamoDBAttribute
    private Date bidEndDate;
}
