package com.casestudy.backend.eauctionapp.model;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Seller {
    @Email(message = "Please Enter Valid Eamil !")
    @NotEmpty(message = "Email Should Not be Empty !")
    @Id
    private String email;
    @NotEmpty(message = "First Name Should Not Be Null !")
    @Size(max = 30, min = 5, message = "First Name Should be min 5 chars and max 30 chars !")
    private String firstName;
    @NotEmpty(message = "First Name Should Not Be Null !")
    @Size(max = 25, min = 3, message = "Last Name Should be min 3 chars and max 25 chars !")
    private String lastName;
    @Valid
    private Address address;
    @Digits(integer = 10, fraction = 0)
    @NotEmpty(message = "Phone Number Should Not be Empty !")
    @Size(max = 10, min = 10, message = "Phone Number Should be 10 Digits !")
    private String phone;
}