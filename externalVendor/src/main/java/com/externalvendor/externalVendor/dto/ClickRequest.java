package com.externalvendor.externalVendor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClickRequest {

    private String referalCode;

    private String landingPage;

    private String userAgent;

    private String ip;

}
