package com.eauction.eauctionawslambda.functions;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.eauction.eauctionawslambda.model.APIGatewayProxyResponse;
import com.eauction.eauctionawslambda.model.BidResponse;
import com.eauction.eauctionawslambda.service.BuyerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class BuyerUpdateBid implements Function<Map<Object, Object>, APIGatewayProxyResponse> {

    private final BuyerService buyerService;
    private final ObjectMapper objectMapper;

    @Override
    // e-auction/api/v1/buyer/update-bid/{productId}/{buyerEmailld}/{newBidAmount}
    public APIGatewayProxyResponse apply(Map<Object, Object> event) {
        String buyerEmailld = null;
        String productId = null;
        BigDecimal newBidAmount = null;
        BidResponse response = null;
        String stringResponse = "";
        try {
            if (event != null && event.get("pathParameters") != null) {
                log.info("Received Request through API Gateway Proxy ");
                Map<String, String> pathParametersMap = (Map<String, String>) event.get("pathParameters");

                buyerEmailld = pathParametersMap.get("buyerEmailld");
                productId = pathParametersMap.get("productId");
                newBidAmount = new BigDecimal(pathParametersMap.get("newBidAmount"));
            }

            log.info("Request Received for Updating Bid On Product: {}", productId);

            response = buyerService.updateBid(productId, buyerEmailld, newBidAmount);
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
                        "Access-Control-Allow-Methods", "GET,PUT,UPDATE,DELETE,OPTIONS", "Access-Control-Allow-Headers",
                        "Origin, Content-Type, Accept"),
                stringResponse, false);
    }

}
