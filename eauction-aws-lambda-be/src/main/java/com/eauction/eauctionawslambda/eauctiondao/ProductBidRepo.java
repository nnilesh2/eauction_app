package com.eauction.eauctionawslambda.eauctiondao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eauction.eauctionawslambda.model.ID;
import com.eauction.eauctionawslambda.model.ProductBid;
@Repository
public interface ProductBidRepo extends JpaRepository<ProductBid,ID>{
    List<ProductBid> findByProductID(String productId);
}
