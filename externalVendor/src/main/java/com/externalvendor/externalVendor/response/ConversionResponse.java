package com.externalvendor.externalVendor.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionResponse {
    private int id;
    private String externalId;
    private double amount;
    private String click;
    private List<Commision> commisions;
    private Program program;
    private Affilate affilate;
    private String createdAt;
    private String warnings;
}
