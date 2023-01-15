package com.springbootexercise.springbootexercise.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExternalConversionRequest {

    private String clickId;
    private UUID externalId;
    private double amount;
    private String currency;
    private String userAgent;

}
