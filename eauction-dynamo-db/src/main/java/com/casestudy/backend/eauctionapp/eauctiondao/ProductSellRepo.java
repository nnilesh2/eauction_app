package com.casestudy.backend.eauctionapp.eauctiondao;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.casestudy.backend.eauctionapp.model.ProductAuction;
@EnableScan
public interface ProductSellRepo extends CrudRepository<ProductAuction,String>{
    
    ProductAuction findByProduct_ProductID(String productID);
}
