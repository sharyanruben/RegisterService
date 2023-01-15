package com.springbootexercise.springbootexercise.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
