package com.moneylion.assignment.authorization.service;

import com.moneylion.assignment.authorization.exceptionHandlling.AuthorizationException;
import com.moneylion.assignment.authorization.model.dto.GrantAccessRequest;
import com.moneylion.assignment.authorization.model.dto.AuthorizeResponse;
import com.moneylion.assignment.authorization.model.dto.GrantAccessResponse;

public interface AuthorizationService {
    GrantAccessResponse grantAccess(GrantAccessRequest grantAccessRequest) throws AuthorizationException;
    AuthorizeResponse canAccess(String feature, String email) throws AuthorizationException;
}
