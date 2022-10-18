package com.casestudy.backend.eauctionapp.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Address {
    private String addressLine;
    private String city;
    private String state;
    private String pin;
}
