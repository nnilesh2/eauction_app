package com.casestudy.backend.eauctionapp.eauctiondao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.backend.eauctionapp.model.ID;
import com.casestudy.backend.eauctionapp.model.ProductAuction;
@Repository
public interface ProductSellRepo extends JpaRepository<ProductAuction,ID>{
    
    ProductAuction findByProduct_ProductID(String productID);
}
