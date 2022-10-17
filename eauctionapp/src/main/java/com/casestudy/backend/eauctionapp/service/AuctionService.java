package com.casestudy.backend.eauctionapp.service;

import org.springframework.stereotype.Service;

import com.casestudy.backend.eauctionapp.eauctiondao.AuctionDAO;
import com.casestudy.backend.eauctionapp.model.Product;
import com.casestudy.backend.eauctionapp.model.ProductResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuctionService {
    
    private final AuctionDAO auctionDAO;

    public ProductResponse addProduct(final Product product){
        return auctionDAO.addProduct(product);
    }

    public Product getProduct(final String productID) {
        return auctionDAO.getProduct(productID);
    }
}
