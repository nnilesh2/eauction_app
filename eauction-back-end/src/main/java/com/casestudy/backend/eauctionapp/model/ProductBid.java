package com.casestudy.backend.eauctionapp.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@IdClass(ID.class)
@NoArgsConstructor
@AllArgsConstructor
public class ProductBid {
    @Id
    private String email;
    @Id
    private String productID;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email" , insertable = false,updatable = false)
    private Buyer buyer;
    private BigDecimal bidAmount;
}
