package com.casestudy.backend.eauctionapp.model;

import java.math.BigDecimal;

import javax.validation.Valid;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductBid {
    private Product product;
    private BigDecimal bidAmount;
    @Valid
    private Buyer buyer;
}
