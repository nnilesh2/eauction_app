package com.eauction.eauctionawslambda.model;

import java.io.Serializable;

import javax.persistence.Entity;
//import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(of = {"productID"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{
    @Id
    private String productID;
    @NotEmpty(message = "Product Name Should Not Be Null !")
    @Size(max = 30, min = 5, message = "Product Name Should be min 5 chars and max 30 chars !")
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private String category;
}