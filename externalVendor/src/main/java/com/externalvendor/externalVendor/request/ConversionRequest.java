package com.externalvendor.externalVendor.request;

import com.externalvendor.externalVendor.request.enumuration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRequest {

    private String clickId;
    private UUID externalId;
    private double amount;
    private String currency;
    private String userAgent;

}
