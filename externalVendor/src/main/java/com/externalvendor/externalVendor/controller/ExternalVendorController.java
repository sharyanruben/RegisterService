package com.externalvendor.externalVendor.controller;

import com.externalvendor.externalVendor.dto.ClickRequest;
import com.externalvendor.externalVendor.request.ConversionRequest;
import com.externalvendor.externalVendor.response.ConversionResponse;
import com.externalvendor.externalVendor.response.UuidResponse;
import com.externalvendor.externalVendor.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/tap")
public class ExternalVendorController {

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/clicks")
    public UuidResponse clicksUUID(@RequestBody ClickRequest clickRequest){
        UuidResponse uuidResponse = new UuidResponse();
        uuidResponse.setUuid(UUID.randomUUID());
        return uuidResponse;
    }

    @PostMapping("/conversions")
    public ConversionResponse conversionsUUID(@RequestBody ConversionRequest conversionRequest){
        return conversionService.getFilledConversion(conversionRequest);
    }

}
