package com.moneylion.assignment.authorization.service;

import com.moneylion.assignment.authorization.exceptionHandlling.AuthorizationException;
import com.moneylion.assignment.authorization.model.dao.Authorization;
import com.moneylion.assignment.authorization.model.dto.GrantAccessRequest;
import com.moneylion.assignment.authorization.model.dto.AuthorizeResponse;
import com.moneylion.assignment.authorization.model.dto.GrantAccessResponse;
import com.moneylion.assignment.authorization.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    @Autowired
   private FeatureRepository authorizationRepository;

    @Override
    public GrantAccessResponse grantAccess(GrantAccessRequest grantAccessRequest) throws AuthorizationException {
        try{
            Authorization authUser = authorizationRepository.findFeatureByEmail(grantAccessRequest.getEmail(), grantAccessRequest.getFeatureName());

            if (authUser != null) {
                authUser.setEnable(grantAccessRequest.getEnable());
                authorizationRepository.save(authUser);
                return new GrantAccessResponse(200);
            }
            Authorization authorized = new Authorization(grantAccessRequest.getEmail(), grantAccessRequest.getFeatureName(), grantAccessRequest.getEnable());
            authorized = authorizationRepository.save(authorized);
            if(authorized.getEmail().equals(grantAccessRequest.getEmail()))
                return new GrantAccessResponse(200);
        }catch (Exception e){
            throw new AuthorizationException(e, "Error Accessing Database", 304);
        }
        throw new AuthorizationException("Error Granting Access to the User", 500);
    }

    @Override
    public AuthorizeResponse canAccess(String feature, String email) throws AuthorizationException {

        try{
            List<Authorization> authList = authorizationRepository.findByEmail(email);
            if (authList.size()!=0) {
                for (Authorization auth : authList) {
                    if (auth.getFeature().equals(feature)) {
                        if (auth.getEnable())
                            return new AuthorizeResponse("true");
                    }
                }
                return new AuthorizeResponse("false");
            }
            return new AuthorizeResponse("User Not Found");
        }catch (Exception e){
            throw new AuthorizationException(e, "Error while getting info from DB", 500);
        }
    }

}
