package com.casestudy.backend.eauctionapp.eauctiondao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.backend.eauctionapp.model.ID;
import com.casestudy.backend.eauctionapp.model.ProductBid;
@Repository
public interface ProductBidRepo extends JpaRepository<ProductBid,ID>{
    List<ProductBid> findByProductID(String productId);
}
