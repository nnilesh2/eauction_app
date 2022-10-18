package com.casestudy.backend.eauctionapp.model;

import java.math.BigDecimal;
import java.util.Date;

//import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// @Entity
public class Product {
    @Id
    private String productId;
    @NotEmpty(message = "Product Name Should Not Be Null !")
    @Size(max = 30, min = 5, message = "Product Name Should be min 5 chars and max 30 chars !")
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private Category category;
    @Positive(message = "Start Price should be Number and should be greater than Zero !")
    @NotNull(message = "Start Price Should Not Be Null !")
    private BigDecimal startPrice;
    @Future(message = "Bid End Date Should be Future Date !")
    private Date bidEndDate;
}