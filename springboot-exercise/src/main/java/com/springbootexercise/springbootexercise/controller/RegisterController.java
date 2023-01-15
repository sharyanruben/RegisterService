package com.springbootexercise.springbootexercise.controller;

import com.springbootexercise.springbootexercise.dto.ClientRequestDto;
import com.springbootexercise.springbootexercise.dto.ConversionRequestDto;
import com.springbootexercise.springbootexercise.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/client")
    public UUID clientRegister(@RequestBody ClientRequestDto client) {
        return registerService.clickToExternalVendor(client);
    }

    @PostMapping("/conversion")
    public int conversationRegister(@RequestBody ConversionRequestDto conversionRequest) {
        return registerService.conversionToExternalVendor(conversionRequest);
    }
}
