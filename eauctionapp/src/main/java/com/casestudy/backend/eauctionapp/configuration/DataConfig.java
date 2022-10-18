package com.casestudy.backend.eauctionapp.configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.casestudy.backend.eauctionapp.model.Address;
import com.casestudy.backend.eauctionapp.model.Buyer;
import com.casestudy.backend.eauctionapp.model.Category;
import com.casestudy.backend.eauctionapp.model.Product;
import com.casestudy.backend.eauctionapp.model.ProductBid;
import com.casestudy.backend.eauctionapp.model.ProductSell;
import com.casestudy.backend.eauctionapp.model.Seller;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataConfig {
    HashMap<String, ProductSell> productDataMap = new HashMap<String, ProductSell>();
    HashMap<String, List<ProductBid>> productBidMap = new HashMap<String, List<ProductBid>>();

    @Bean
    public Map<String, ProductSell> productData() {
        productDataMap.put("777", createDummyProduct("777"));

        createBidOnProduct("777");

        return productDataMap;
    }

    @Bean
    @DependsOn(value = "productData")
    public HashMap<String, List<ProductBid>> productBidData() {
        log.info("Invoking DataConfig.productBitData");
        productBidMap.put("777", createBidOnProduct("777"));
        return productBidMap;
    }

    private List<ProductBid> createBidOnProduct(String productID) {

        final Buyer buyer1 = Buyer.builder().email("buyer1@eauction.com")
                .firstName("Buyer_1")
                .lastName("Buyer_1_Last_Name")
                .phone("1111111111")
                .address(getAddress())
                .build();
        final Buyer buyer2 = Buyer.builder().email("buyer2@eauction.com")
                .firstName("Buyer_2")
                .lastName("Buyer_2_Last_Name")
                .phone("2222222222")
                .address(getAddress())
                .build();
        final ProductBid bid_1 = ProductBid.builder()
                .product(productDataMap.get(productID).getProduct())
                .bidAmount(new BigDecimal("120"))
                .buyer(buyer1).build();
        final ProductBid bid_2 = ProductBid.builder()
                .product(productDataMap.get(productID).getProduct())
                .bidAmount(new BigDecimal("130"))
                .buyer(buyer2).build();

        List<ProductBid> bids = new ArrayList<>();
        bids.add(bid_1);
        bids.add(bid_2);
        return bids;
    }

    private ProductSell createDummyProduct(String productID) {
        return ProductSell.builder().product(Product.builder().productId(productID)
                .productName("Red Painting Product")
                .shortDescription("Red Paint")
                .detailedDescription("This is a Red Painting Product !")
                .category(Category.Painting)
                .startPrice(new BigDecimal("100"))
                .bidEndDate(Date.from(Instant.now().plus(2, ChronoUnit.DAYS))).build())
                .seller(Seller.builder().firstName("Nilesh").lastName("Nagare").email("dummy@dummy.com")
                        .phone("7777777777")
                        .address(getAddress())
                        .build())
                .build();
    }

    private Address getAddress() {
        return Address.builder().addressLine("Block1,Dummy Road").city("London").state("London")
                .pin("E12ABC")
                .build();
    }
}
