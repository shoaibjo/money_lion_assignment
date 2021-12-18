package com.moneylion.assignment.authorization.exceptionHandlling;

import lombok.Data;

@Data
public class AuthorizationException extends Exception{

    private String message;
    private int statusCode;
    private Exception exception;

    public AuthorizationException(Exception exception, String message, int statusCode){
        super(exception);
        this.message = message;
        this.statusCode = statusCode;
        this.exception = exception;
    }

    public AuthorizationException(String message, int statusCode){
        super();
        this.message = message;
        this.statusCode = statusCode;
    }
}
