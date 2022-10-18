package com.casestudy.backend.eauctionapp.model;

import javax.validation.Valid;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSell {
    @Valid
    private Product product;
    @Valid
    private Seller seller;
}
