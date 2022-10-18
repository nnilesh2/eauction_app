package com.casestudy.backend.eauctionapp.eauctiondao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.casestudy.backend.eauctionapp.model.ProductBid;
import com.casestudy.backend.eauctionapp.model.ProductResponse;
import com.casestudy.backend.eauctionapp.model.ProductSell;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuctionDAO {

    private final Map<String, ProductSell> productData;
    private final HashMap<String, List<ProductBid>> productBidData;

    public ProductResponse addProduct(final ProductSell product) {
        productData.put(product.getProduct().getProductId(), product);
        return ProductResponse.builder().productID(product.getProduct().getProductId()).build();
    }

    public ProductSell getProduct(final String productID) {
        return productData.get(productID);
    }

    public List<ProductBid> getBids(final String productID) {
        return productBidData.get(productID);
    }

    public void deleteProduct(String productID) {
        productData.remove(productID);
    }
}
