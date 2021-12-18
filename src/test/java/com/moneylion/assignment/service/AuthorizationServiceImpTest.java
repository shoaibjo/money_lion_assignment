package com.moneylion.assignment.service;

import com.moneylion.assignment.authorization.exceptionHandlling.AuthorizationException;
import com.moneylion.assignment.authorization.model.dao.Authorization;
import com.moneylion.assignment.authorization.model.dto.AuthorizeResponse;
import com.moneylion.assignment.authorization.model.dto.GrantAccessRequest;
import com.moneylion.assignment.authorization.model.dto.GrantAccessResponse;
import com.moneylion.assignment.authorization.repository.FeatureRepository;
import com.moneylion.assignment.authorization.service.AuthorizationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationServiceImpTest {


    @Mock
    private FeatureRepository repository;

    @Autowired
    @InjectMocks
    private AuthorizationServiceImpl service;

    private GrantAccessRequest request;
    private Authorization mockedAuthorized;
    private List<Authorization> authorizations;



    @Before
   public void init(){
        request = new GrantAccessRequest("Feature1", "user@gmail.com", true);
        mockedAuthorized = new Authorization("user@gmail.com", "Feature1", true);
        authorizations = new ArrayList<>();
        authorizations.add(mockedAuthorized);
    }

    @Test
    public void grantAccessTest() throws AuthorizationException {


        //Mock DB Calls
        when(repository.findFeatureByEmail(any(), any())).thenReturn(mockedAuthorized);
        when(repository.save(any())).thenReturn(mockedAuthorized);
        //Call the service
        GrantAccessResponse response =  service.grantAccess(request);
        //Assert the Response
        Assert.assertEquals(200, response.getStatus());


    }

    @Test
    public void canAccessTest() throws AuthorizationException {

        //Mock DB Calls
        when(repository.findByEmail(any())).thenReturn(authorizations);

        //Call the service
        AuthorizeResponse response = service.canAccess(request.getFeatureName(), request.getEmail());
        Assert.assertEquals("true", response.getCanAccess());
    }
}
