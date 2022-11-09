package com.casestudy.backend.eauctionapp.eauctiondao;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.casestudy.backend.eauctionapp.model.ProductBid;
@EnableScan
public interface ProductBidRepo extends CrudRepository<ProductBid,String>{
    List<ProductBid> findByProductID(String productId);
}
