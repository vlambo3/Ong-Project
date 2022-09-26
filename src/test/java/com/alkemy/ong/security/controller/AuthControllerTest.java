package com.alkemy.ong.security.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.security.auth.UserService;
import static com.alkemy.ong.security.controller.utils.AuthUtilsTest.*;

import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.dto.RegisterResponseDto;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
    @Nested
    public class Register{
       
        @Test
        void registerOk() throws Exception {
            UserRequestDto requestDto = generateRequestDto();
            RegisterResponseDto responseDto = generateResponseDto();
    
            when(service.save(requestDto)).thenReturn(responseDto);
    
            mockMvc.perform(post("/auth/register")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))                                        
                    .andExpect(status().isCreated());
        }

        @Test
        void registerWhitNullFirstName() throws Exception{
            UserRequestDto requestDto = generateRequestDtoWithNullFirstName();

            when(service.save(requestDto)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/register")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))                                        
            .andExpect(status().isBadRequest());
        }

        @Test
        void registerWhitBlankFirstName() throws Exception{
            UserRequestDto requestDto = generateRequestDtoWithBlankFirstName();

            when(service.save(requestDto)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/register")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))                                        
            .andExpect(status().isBadRequest());
        }

        @Test
        void registerWhitNullLastName() throws Exception{
            UserRequestDto requestDto = generateRequestDtoWithNullLastName();

            when(service.save(requestDto)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/register")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))                                        
            .andExpect(status().isBadRequest());
        }

        @Test
        void registerWhitBlankLastName() throws Exception{
            UserRequestDto requestDto = generateRequestDtoWithBlankLastName();

            when(service.save(requestDto)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/register")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))                                        
            .andExpect(status().isBadRequest());
        }

        @Test
        void registerWhitNullPassword() throws Exception{
            UserRequestDto requestDto = generateRequestDtoWithNullPassword();

            when(service.save(requestDto)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/register")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))                                        
            .andExpect(status().isBadRequest());
        }

        @Test
        void registerWhitBlankPassword() throws Exception{
            UserRequestDto requestDto = generateRequestDtoWithBlankPassword();

            when(service.save(requestDto)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/register")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))                                        
            .andExpect(status().isBadRequest());
        }

        @Test
        void registerWhitNullEmail() throws Exception{
            UserRequestDto requestDto = generateRequestDtoWithNullEmail();

            when(service.save(requestDto)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/register")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))                                        
            .andExpect(status().isBadRequest());
        }

        @Test
        void registerWhitBlankEmail() throws Exception{
            UserRequestDto requestDto = generateRequestDtoWithBlankEmail();

            when(service.save(requestDto)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/register")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))                                        
            .andExpect(status().isBadRequest());
        }
    }

    @Nested
    public class Login {

        @Test
        void loginOK() throws Exception{
            AuthenticationRequest request = generateAuthRequest();
            AuthenticationResponse response = generateAuthResponse();

            when(service.authenticate(request)).thenReturn(response);

            mockMvc.perform(post("/auth/login")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))                                        
                    .andExpect(status().isOk());
        }

        @Test
        void loginWhitNullEmail() throws Exception{
            AuthenticationRequest request = generateRequestWhitNullEmail();

            when(service.authenticate(request)).thenThrow(BadRequestException.class);

            mockMvc.perform(post("/auth/login")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))                                        
                    .andExpect(status().isBadRequest());
        }
    }
    




}
