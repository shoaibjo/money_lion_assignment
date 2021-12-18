package com.moneylion.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneylion.assignment.authorization.controller.FeatureAccessController;
import com.moneylion.assignment.authorization.model.dto.AuthorizeResponse;
import com.moneylion.assignment.authorization.model.dto.GrantAccessRequest;
import com.moneylion.assignment.authorization.model.dto.GrantAccessResponse;
import com.moneylion.assignment.authorization.service.AuthorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FeatureAccessControllerTest {

    @Mock
    private AuthorizationService service;

    private GrantAccessRequest grantRequest;
    private GrantAccessResponse grantResponse;
    private AuthorizeResponse authRespones;

    @InjectMocks
    private FeatureAccessController controller;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        grantRequest = new GrantAccessRequest("Feature1", "email@gmail.com", true);
        grantResponse = new GrantAccessResponse(200);
        authRespones = new AuthorizeResponse("true");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();;

    }


    @Test
    public void canAccessTest() throws Exception {

        //Mock the Service Response
        when(service.canAccess(any(), any())).thenReturn(authRespones);
        //Assert the Service Response
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/feature", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("feature", grantRequest.getFeatureName())
                        .param("email", grantRequest.getEmail()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("canAccess").value("true"));

    }

    @Test
    public void grantAccessTest() throws Exception {


        //Mock the Service Response
        when(service.grantAccess(any())).thenReturn(grantResponse);

        mockMvc.perform(post("/api/feature").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(grantRequest))).
                andExpect(status().isOk());
        verify(service).grantAccess(any());
        verify(service, times(1)).grantAccess(any());

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
