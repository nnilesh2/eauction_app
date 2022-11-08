package com.eauction.eauctionawslambda.eauctiondao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eauction.eauctionawslambda.model.ID;
import com.eauction.eauctionawslambda.model.ProductAuction;
@Repository
public interface ProductSellRepo extends JpaRepository<ProductAuction,ID>{
    
    ProductAuction findByProduct_ProductID(String productID);
}
