package com.casestudy.backend.eauctionapp.eauctiondao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.casestudy.backend.eauctionapp.model.Product;
import com.casestudy.backend.eauctionapp.model.ProductResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuctionDAO {

    private final Map<String, Product> productData;
    
    public ProductResponse addProduct(final Product product){
        if(productData.containsKey(product.getProductId())){
            throw new RuntimeException("Product Already Exists with Product ID : "+product.getProductId());
        }
        productData.put(product.getProductId(), product);
        return ProductResponse.builder().productID(product.getProductId()).build();
    }

    public Product getProduct(final String productID){
        return productData.get(productID);
    }
}
