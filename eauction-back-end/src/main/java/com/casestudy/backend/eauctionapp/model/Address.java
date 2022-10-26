package com.casestudy.backend.eauctionapp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable{
    private String addressLine;
    private String city;
    private String state;
    private String pin;
}
