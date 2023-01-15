package com.springbootexercise.springbootexercise.enumuration;

public enum RequestType {
    CREATECLICK("createClick"),
    CREATECONVERSION("createConversion");

    private String value;
    RequestType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
