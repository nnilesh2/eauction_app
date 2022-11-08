package com.eauction.eauctionawslambda.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BidResponse {
    private String productID;
    private BigDecimal bidAmount;
}
