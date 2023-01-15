package com.externalvendor.externalVendor.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commision {

    private int id;
    private double conversionSubAmount;
    private double amount;
    private String commisionType;
    private boolean approved;
    private Affilate affilate;
    private String kind;
    private String currency;
    private String createdAt;

}
