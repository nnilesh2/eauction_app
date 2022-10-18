package com.casestudy.backend.eauctionapp.service;

import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.casestudy.backend.eauctionapp.eauctiondao.ProductBidRepo;
import com.casestudy.backend.eauctionapp.eauctiondao.ProductSellRepo;
import com.casestudy.backend.eauctionapp.model.ProductBid;
import com.casestudy.backend.eauctionapp.model.ProductResponse;
import com.casestudy.backend.eauctionapp.model.ProductSell;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SellerService {

    private final ProductSellRepo productSellRepo;
    private final ProductBidRepo productBidRepo;
    // private final Map<String, ProductSell> productSellData;

    public ProductResponse addProduct(final ProductSell product) {
        if (this.getProduct(product.getProduct().getProductID()) != null) {
            throw new RuntimeException(
                    "Product Already Exists with Product ID : " + product.getProduct().getProductID());
        }
        ProductSell savedProduct = productSellRepo.save(product);
        return ProductResponse.builder().productID(savedProduct.getProduct().getProductID()).build();
    }

    public ProductSell getProduct(final String productID) {
        return productSellRepo.findByProduct_ProductID(productID);
    }

    public List<ProductBid> getBids(final String productID) {
        List<ProductBid> bidList = productBidRepo.findByProductID(productID);
        Comparator<ProductBid> comparator = (p1, p2) -> p1.getBidAmount().compareTo(p2.getBidAmount());
        if (bidList != null && bidList.size() > 1) {
            bidList.sort(comparator.reversed());
        }
        return bidList;
    }

    @Transactional
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
        if ((Date.from(Instant.now()).after(productSell.getBidEndDate()))) {
            throw new RuntimeException(
                    "Can Not Delete Product, Since Bid End date is Passed : " + productID + ",Bid End Date :"
                            + productSell.getBidEndDate());
        }
        productSellRepo.delete(productSell);
    }
}