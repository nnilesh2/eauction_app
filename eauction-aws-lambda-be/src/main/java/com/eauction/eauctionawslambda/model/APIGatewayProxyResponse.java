package com.eauction.eauctionawslambda.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class APIGatewayProxyResponse {
    private int statusCode;
    private Map<String,String> headers;
    private String body;
    private boolean isBase64Encoded;
}
