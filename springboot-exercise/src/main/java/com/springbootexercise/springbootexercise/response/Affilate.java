package com.springbootexercise.springbootexercise.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Affilate {

    private String id;
    private String firstName;
    private String lastName;

}
