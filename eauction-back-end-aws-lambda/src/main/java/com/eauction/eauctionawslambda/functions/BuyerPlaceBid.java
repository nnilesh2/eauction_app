package com.eauction.eauctionawslambda.functions;

import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.eauction.eauctionawslambda.model.APIGatewayProxyResponse;
import com.eauction.eauctionawslambda.model.BidResponse;
import com.eauction.eauctionawslambda.model.ProductBid;
import com.eauction.eauctionawslambda.service.BuyerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Component
@Slf4j
public class BuyerPlaceBid implements Function<Map<Object, Object>, APIGatewayProxyResponse> {

    private final BuyerService buyerService;
    private final ObjectMapper objectMapper;

    @Override
    // @PostMapping("/e-auction/api/v1/buyer/place-bid")
    public APIGatewayProxyResponse apply(Map<Object, Object> event) {

        log.info("Invoking BuyerPlaceBid.apply");

        ProductBid productBid = null;
        BidResponse response = null;
        String stringResponse = "";
        try {
            if (event != null && event.get("body") != null) {
                log.info("Received Request through API Gateway Proxy ");
                productBid = objectMapper.readValue((String) event.get("body"), ProductBid.class);
            }
            log.info("Request Received for Placing Bid On Product: {}", productBid.getProductID());

            response = buyerService.placeBid(productBid);
            stringResponse = objectMapper.writeValueAsString(response);
        } catch (Exception e) {

            stringResponse = "{" + "\"message\"" + ":" + "\"" + e.getMessage() + "\"" + "}";

            return new APIGatewayProxyResponse(500,
                    Map.of("content-type", "application/json", "Access-Control-Allow-Origin", "*",
                            "Access-Control-Allow-Methods", "*", "Access-Control-Allow-Headers", "*"),
                    stringResponse, false);
        }

        return new APIGatewayProxyResponse(201,
                Map.of("content-type", "application/json", "Access-Control-Allow-Origin", "*",
                        "Access-Control-Allow-Methods", "*", "Access-Control-Allow-Headers", "*"),
                stringResponse, false);
    }

}
