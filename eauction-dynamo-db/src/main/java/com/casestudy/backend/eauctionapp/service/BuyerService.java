package com.casestudy.backend.eauctionapp.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.casestudy.backend.eauctionapp.eauctiondao.ProductBidRepo;
import com.casestudy.backend.eauctionapp.model.BidResponse;
import com.casestudy.backend.eauctionapp.model.ProductAuction;
import com.casestudy.backend.eauctionapp.model.ProductBid;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuyerService {

    private final ProductBidRepo productBidRepo;
    private final SellerService sellerService;

    public BidResponse placeBid(ProductBid productBid) {
        //populate buyer email for persistence 
        productBid.setEmail(productBid.getBuyer().getEmail());

        ProductAuction productForAuction = sellerService.getProduct(productBid.getProductID());
        if (productForAuction == null) {
            throw new RuntimeException("No Product Available For Bid With Product ID :" + productBid.getProductID());
        }
        ProductBid placedBid = productBidRepo.save(productBid);
        return BidResponse.builder().productID(placedBid.getProductID()).bidAmount(placedBid.getBidAmount()).build();
    }

    public BidResponse updateBid(String productId, String buyerEmailld, BigDecimal newBidAmount) {
        ProductAuction productForAuction = sellerService.getProduct(productId);
        Optional<ProductBid> productBidOptional = productBidRepo.findById(productId);
        if (productBidOptional.isEmpty() || productForAuction == null) {
            throw new RuntimeException(
                    "No Product Available For Bid With Product ID :" + productId + " Email ID :" + buyerEmailld);
        }

        if (Date.from(Instant.now()).after(productForAuction.getBidEndDate())) {
            throw new RuntimeException(
                    "Can Not Update Bid With Product ID :" + productId + ", Since Bid End Date is Passed !");
        }
        ProductBid productBid = productBidOptional.get();
        productBid.setBidAmount(newBidAmount);
        productBidRepo.save(productBid);
        return BidResponse.builder().productID(productId).bidAmount(newBidAmount).build();
    }

}
