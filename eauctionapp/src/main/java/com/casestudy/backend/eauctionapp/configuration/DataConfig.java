package com.casestudy.backend.eauctionapp.configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.casestudy.backend.eauctionapp.model.Product;
import com.casestudy.backend.eauctionapp.model.Product.Address;
import com.casestudy.backend.eauctionapp.model.Product.Category;
import com.casestudy.backend.eauctionapp.model.Product.Seller;

@Configuration
public class DataConfig {

    @Bean
    public Map<String, Product> productData() {
        var productDataMap = new HashMap<String, Product>();

        productDataMap.put("777", createDummyProduct("777"));

        return productDataMap;
    }

    private Product createDummyProduct(String productID) {
        return Product.builder().productId(productID)
                .productName("Red Painting Product")
                .shortDescription("Red Paint")
                .detailedDescription("This is a Red Painting Product !")
                .category(Category.Painting)
                .startPrice(new BigDecimal("100"))
                .bidEndDate(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)))
                .seller(Seller.builder().firstName("Nilesh").lastName("Nagare")
                        .address(Address.builder().city("London").state("London").pin("E12ABC")
                                .email("dummy@dummy.com").phone("0123456789").build())
                        .build())
                .build();
    }
}
