package com.springbootexercise.springbootexercise.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program {

    private String id;
    private String title;
    private String currency;

}
