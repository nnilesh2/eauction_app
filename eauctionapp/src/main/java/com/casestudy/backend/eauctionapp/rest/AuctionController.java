package com.casestudy.backend.eauctionapp.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.backend.eauctionapp.model.Product;
import com.casestudy.backend.eauctionapp.model.ProductResponse;
import com.casestudy.backend.eauctionapp.service.AuctionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/e-auction/api/v1/seller")
@AllArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/add-product")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid Product product) {
        return new ResponseEntity<>(auctionService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/get-product/{productID}")
    public ResponseEntity<Product> getProduct(@PathVariable String productID) {
        return new ResponseEntity<>(auctionService.getProduct(productID), HttpStatus.OK);
    }
}
