package com.moneylion.assignment.authorization.controller;

import com.moneylion.assignment.authorization.exceptionHandlling.AuthorizationException;
import com.moneylion.assignment.authorization.model.dao.CanAccessDto;
import com.moneylion.assignment.authorization.model.dto.GrantAccessRequest;
import com.moneylion.assignment.authorization.model.dto.AuthorizeResponse;
import com.moneylion.assignment.authorization.model.dto.GrantAccessResponse;
import com.moneylion.assignment.authorization.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/feature")
public class FeatureAccessController {

    @Autowired
    private AuthorizationService authorizationServiceImp;

    @GetMapping()
    public ResponseEntity canAccess(@Valid CanAccessDto canAccessDto){
        AuthorizeResponse authorizeResponse= null;

        try {
            authorizeResponse = authorizationServiceImp.canAccess(canAccessDto.getFeature(), canAccessDto.getEmail());
        } catch (AuthorizationException e) {
            if(e.getStatusCode() == 400){
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(authorizeResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity grantAccess(@Valid @RequestBody GrantAccessRequest authenticationDTO){
        GrantAccessResponse response = null;
        try {
            response = authorizationServiceImp.grantAccess(authenticationDTO);
        } catch (AuthorizationException e) {
            if(e.getStatusCode() == 400){
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }else if(e.getStatusCode() == 304){
                e.setMessage("Dummy thing");
                return new ResponseEntity(e.getMessage(), HttpStatus.NOT_MODIFIED);
            }else{
                return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity( HttpStatus.OK);
    }

}
