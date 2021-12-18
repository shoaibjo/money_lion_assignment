package com.moneylion.assignment.authorization.model.dto;

public class AuthorizeResponse {
    String canAccess;

    public AuthorizeResponse(String canAccess) {
        this.canAccess = canAccess;
    }

    public String getCanAccess() {
        return canAccess;
    }

    public void setCanAccess(String canAccess) {
        this.canAccess = canAccess;
    }
}
