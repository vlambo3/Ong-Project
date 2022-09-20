package com.alkemy.ong.security.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alkemy.ong.enums.RoleEnum;
import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.model.Role;
import com.alkemy.ong.utils.UserServiceStub;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @MockBean
    private UserServiceStub service;

    private AuthenticationRequest authRequest;
    private AuthenticationResponse authResponse;
    private Role role;
    private UserRequestDto userResquest;
    private UserResponseDto userResponse;
    private String ENDPOINT_URL = "/auth/login";




    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                                .alwaysExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))                                
                                .apply(springSecurity())
                                .build();

        role = new Role(1L,
                RoleEnum.ADMIN,
                "description",
                LocalDateTime.now(),
                LocalDateTime.now());

        authResponse = new AuthenticationResponse("token");
        mapper = new ObjectMapper();
    }

    

    @Test
    void whenRequestIsOk_shouldReturnToken_status200() throws Exception {
        authRequest = AuthenticationRequest.builder()
                .email("validEmail@testing.com")
                .password("validPassword")
                .build();

        when(service.authenticate(authRequest)).thenReturn(authResponse);

        mockMvc.perform(post(ENDPOINT_URL)
                .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.jwt").value(authResponse.getJwt()))
                .andExpect(status().isOk());

        verify(service).authenticate(authRequest);
    }

    @Test
    void whenRequestHasInvalidEmail_shouThrowNotFoundException_status404() {

    }

}
