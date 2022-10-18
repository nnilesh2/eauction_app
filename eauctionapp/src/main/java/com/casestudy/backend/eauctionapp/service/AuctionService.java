package com.casestudy.backend.eauctionapp.service;

import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.casestudy.backend.eauctionapp.eauctiondao.AuctionDAO;
import com.casestudy.backend.eauctionapp.model.ProductBid;
import com.casestudy.backend.eauctionapp.model.ProductResponse;
import com.casestudy.backend.eauctionapp.model.ProductSell;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuctionService {

    private final AuctionDAO auctionDAO;

    public ProductResponse addProduct(final ProductSell product) {
        if (this.getProduct(product.getProduct().getProductId()) != null) {
            throw new RuntimeException(
                    "Product Already Exists with Product ID : " + product.getProduct().getProductId());
        }
        return auctionDAO.addProduct(product);
    }

    public ProductSell getProduct(final String productID) {
        return auctionDAO.getProduct(productID);
    }

    public List<ProductBid> getBids(final String productID) {
        List<ProductBid> bidList = auctionDAO.getBids(productID);
        Comparator<ProductBid> comparator = (p1, p2) -> p1.getBidAmount().compareTo(p2.getBidAmount());
        if(bidList!=null && bidList.size()>1){
            bidList.sort(comparator.reversed());
        }      
        return bidList;
    }

    public void deleteProduct(final String productID) {
        ProductSell productSell = this.getProduct(productID);
        if (productSell == null) {
            throw new RuntimeException(
                    "No Product Found with Product ID : " + productID);
        }
        List<ProductBid> bidsOnProduct = this.getBids(productID);
        if (bidsOnProduct != null && !bidsOnProduct.isEmpty()) {
            throw new RuntimeException(
                    "Can Not Delete Product, Since Bids already Exist on Product : " + productID);
        }
        if ((Date.from(Instant.now()).after(productSell.getProduct().getBidEndDate()))) {
            throw new RuntimeException(
                    "Can Not Delete Product, Since Bid End date is Passed : " + productID + ",Bid End Date :"
                            + productSell.getProduct().getBidEndDate());
        }
        auctionDAO.deleteProduct(productID);
    }
}