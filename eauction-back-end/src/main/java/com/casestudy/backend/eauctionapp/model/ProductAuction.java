package com.casestudy.backend.eauctionapp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ID.class)
public class ProductAuction {
    @Id
    private String email;
    @Id
    private String productID;
    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "email", insertable = false, updatable = false)
    private Seller seller;
    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "productID", insertable = false, updatable = false)
    private Product product;
    @Positive(message = "Start Price should be Number and should be greater than Zero !")
    @NotNull(message = "Start Price Should Not Be Null !")
    private BigDecimal startPrice;
    @Future(message = "Bid End Date Should be Future Date !")
    private Date bidEndDate;
}
