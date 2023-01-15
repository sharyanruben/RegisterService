package com.springbootexercise.springbootexercise.enumuration;

public enum TransactionType {
    NEW("New"),
    RESET("Reset"),
    EXTEND("Extend");

    private String value;

    TransactionType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
