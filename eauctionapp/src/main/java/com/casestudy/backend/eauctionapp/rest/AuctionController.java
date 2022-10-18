package com.casestudy.backend.eauctionapp.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.backend.eauctionapp.model.ProductBid;
import com.casestudy.backend.eauctionapp.model.ProductResponse;
import com.casestudy.backend.eauctionapp.model.ProductSell;
import com.casestudy.backend.eauctionapp.service.AuctionService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/e-auction/api/v1")
@AllArgsConstructor
@Slf4j
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/seller/add-product")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid ProductSell product) {
        return new ResponseEntity<>(auctionService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/seller/get-product/{productID}")
    public ResponseEntity<ProductSell> getProduct(@PathVariable String productID) {
        return new ResponseEntity<>(auctionService.getProduct(productID), HttpStatus.OK);
    }

    @GetMapping("/seller/show-bids/{productID}")
    public ResponseEntity<List<ProductBid>> getBids(@PathVariable String productID) {
        log.info("Invoking AuctionController.getBids >>");
        return new ResponseEntity<>(auctionService.getBids(productID), HttpStatus.OK);
    }

    @DeleteMapping("/seller/delete/{productID}")
    public ResponseEntity<String> detleteProduct(@PathVariable String productID) {
        log.info("Invoking AuctionController.detleteProduct >>");
        auctionService.deleteProduct(productID);
        return new ResponseEntity<>(productID, HttpStatus.ACCEPTED);
    }
}