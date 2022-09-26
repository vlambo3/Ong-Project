package com.alkemy.ong.security.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.springframework.security.test.context.support.WithMockUser;

import com.alkemy.ong.enums.RoleEnum;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.ForbiddenException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.security.auth.UserService;
import com.alkemy.ong.security.dto.UserDto;
import com.alkemy.ong.security.dto.UserRequestDto;
import com.alkemy.ong.security.dto.UserResponseDto;
import com.alkemy.ong.security.model.Role;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private UserService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultRequest(get("/").with(user("user").roles("ADMIN")))
                .alwaysExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .apply(springSecurity())
                .build();
    }

    @Test
    void registerOk(){
        when(service.save()).thenReturn(users);

            mockMvc.perform(get(ENDPOINT_URL)
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("ADMIN")))
                    .andExpect(jsonPath("@", hasSize(users.size())))
                    .andExpect(jsonPath("$.[0].firstName").value(userOne.getFirstName()))
                    .andExpect(jsonPath("$.[0].lastName").value(userOne.getLastName()))
                    .andExpect(jsonPath("$.[0].email").value(userOne.getEmail()))
                    .andExpect(jsonPath("$.[0].role").value(notNullValue()))
                    .andExpect(status().isOk());

            verify(service).getAll();
    }


}
