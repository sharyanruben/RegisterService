package com.externalvendor.externalVendor.service;

import com.externalvendor.externalVendor.request.ConversionRequest;
import com.externalvendor.externalVendor.response.Affilate;
import com.externalvendor.externalVendor.response.Commision;
import com.externalvendor.externalVendor.response.ConversionResponse;
import com.externalvendor.externalVendor.response.Program;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class ConversionService {

    public ConversionResponse getFilledConversion(ConversionRequest conversionRequest){
        return ConversionResponse
                .builder()
                .id(2)
                .externalId(conversionRequest.getExternalId().toString())
                .amount(conversionRequest.getAmount())
                .click(conversionRequest.getClickId())
                .commisions(Collections.singletonList(getCommision(conversionRequest.getCurrency())))
                .program(getProgram())
                .affilate(getAffilate())
                .createdAt("2021-03-03T12:39:19+0000")
                .build();
    }

    private Commision getCommision(String currency){
        return Commision
                .builder()
                .id(2)
                .conversionSubAmount(100.00)
                .amount(100.00)
                .commisionType("standard")
                .approved(false)
                .affilate(getAffilate())
                .kind("regular")
                .currency(currency)
                .createdAt("2021-03-03T12:39:19+0000")
                .build();
    }

    private Affilate getAffilate(){
        return Affilate
                .builder()
                .id("janejameson")
                .firstName("Jane")
                .lastName("Jameson")
                .build();
    }

    private Program getProgram(){
        return Program
                .builder()
                .id("johns-affiliate-program")
                .title("John's affiliate program")
                .currency("USD")
                .build();
    }

}
