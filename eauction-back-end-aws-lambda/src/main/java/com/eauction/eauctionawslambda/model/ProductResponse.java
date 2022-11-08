package com.eauction.eauctionawslambda.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private String productID;
}
