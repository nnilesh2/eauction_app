package com.casestudy.backend.eauctionapp.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.backend.eauctionapp.model.BidResponse;
import com.casestudy.backend.eauctionapp.model.ProductBid;
import com.casestudy.backend.eauctionapp.service.BuyerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/e-auction/api/v1/buyer")
@AllArgsConstructor
public class BuyerController {
    private final BuyerService buyerService;

    @PostMapping("/place-bid")
    public ResponseEntity<BidResponse> placeBid(@RequestBody @Valid ProductBid productBid) {
        return new ResponseEntity<>(buyerService.addBid(productBid), HttpStatus.CREATED);
    }
}
