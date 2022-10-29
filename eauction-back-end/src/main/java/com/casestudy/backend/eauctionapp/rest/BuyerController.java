package com.casestudy.backend.eauctionapp.rest;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.backend.eauctionapp.model.BidResponse;
import com.casestudy.backend.eauctionapp.model.ProductBid;
import com.casestudy.backend.eauctionapp.service.BuyerService;

import lombok.AllArgsConstructor;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/e-auction/api/v1/buyer")
@AllArgsConstructor
public class BuyerController {
    private final BuyerService buyerService;

    @PostMapping("/place-bid")
    public ResponseEntity<BidResponse> placeBid(@RequestBody @Valid ProductBid productBid) {
        return new ResponseEntity<BidResponse>(buyerService.placeBid(productBid), HttpStatus.CREATED);
    }

    @PutMapping("/update-bid/{productId}/{buyerEmailld}/{newBidAmount}")
    public ResponseEntity<BidResponse> updateBid(@PathVariable final String productId,
            @PathVariable final String buyerEmailld, @PathVariable final BigDecimal newBidAmount) {
        return new ResponseEntity<BidResponse>(buyerService.updateBid(productId, buyerEmailld, newBidAmount),
                HttpStatus.OK);
    }
}
