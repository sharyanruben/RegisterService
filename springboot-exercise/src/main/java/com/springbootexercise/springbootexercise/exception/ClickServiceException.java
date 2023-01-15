package com.springbootexercise.springbootexercise.exception;

public class ClickServiceException extends RuntimeException{

    private static final long serialVersionUID = -1328469745129547879L;

    public ClickServiceException(String message){
        super(message);
    }

}
