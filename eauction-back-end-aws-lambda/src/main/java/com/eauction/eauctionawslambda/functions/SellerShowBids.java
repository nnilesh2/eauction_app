package com.eauction.eauctionawslambda.functions;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.eauction.eauctionawslambda.model.APIGatewayProxyResponse;
import com.eauction.eauctionawslambda.model.ProductBid;
import com.eauction.eauctionawslambda.service.SellerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Component
@Slf4j
public class SellerShowBids implements Function<Map<Object, Object>, APIGatewayProxyResponse> {

    private final SellerService sellerService;
    private final ObjectMapper objectMapper;

    @Override
    // e-auction/api/v1/seller/show-bids/{productID}
    public APIGatewayProxyResponse apply(Map<Object, Object> event) {

        String productID = null;
        String stringResponse = "";
        try {
            if (event != null && event.get("pathParameters") != null) {
                log.info("Received Request through API Gateway Proxy ");
                Map<String, String> pathParametersMap = (Map<String, String>) event.get("pathParameters");
                productID = pathParametersMap.get("productID");
            }
            log.info("Received Request for showing Bids On Product ID :{}", productID);

            List<ProductBid> response = sellerService.getBids(productID);

            stringResponse = objectMapper.writeValueAsString(response);

        } catch (Exception e) {
            stringResponse = "{" + "\"message\"" + ":" + "\"" + e.getMessage() + "\"" + "}";
            return new APIGatewayProxyResponse(500,
                    Map.of("content-type", "application/json", "Access-Control-Allow-Origin", "*",
                            "Access-Control-Allow-Methods", "*", "Access-Control-Allow-Headers", "*"),
                    stringResponse, false);
        }

        return new APIGatewayProxyResponse(200,
                Map.of("content-type", "application/json", "Access-Control-Allow-Origin", "*",
                        "Access-Control-Allow-Methods", "*", "Access-Control-Allow-Headers", "*"),
                stringResponse, false);
    }

}
