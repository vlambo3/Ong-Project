package com.alkemy.ong.endpoints.unit.controller;

import com.alkemy.ong.dto.organization.OrganizationBasicResponseDto;
import com.alkemy.ong.dto.organization.OrganizationRequestDto;
import com.alkemy.ong.dto.organization.OrganizationResponseDto;
import com.alkemy.ong.endpoints.util.organization.OrganizationTestUtils;
import com.alkemy.ong.service.IOrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class OrganizationControllerUnitTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @MockBean
    private IOrganizationService service;
    @Autowired
    private OrganizationTestUtils utils;
    @Autowired
    private ObjectMapper objectMapper;
    private OrganizationRequestDto requestDto;
    private OrganizationResponseDto responseDto;
    private OrganizationBasicResponseDto basicResponseDto;
    private OrganizationRequestDto requestDtoWithNullName;
    private OrganizationRequestDto requestDtoWithBlankName;
    private OrganizationRequestDto requestDtoWithNullImage;
    private OrganizationRequestDto requestDtoWithBlankImage;
    private OrganizationRequestDto requestDtoWithNullWelcomeText;
    private OrganizationRequestDto requestDtoWithBlankWelcomeText;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        requestDto = utils.generateRequestDto();
        responseDto = utils.generateResponseDTO();
        basicResponseDto = utils.generateBasicResponseDTO();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createOrganizationAdmin() throws Exception {
        when(service.update(requestDto)).thenReturn(responseDto);
        mockMvc.perform(post("/organization/public")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createOrganizationWithNullName() throws Exception {
        requestDtoWithNullName = utils.generateRequestDtoWithNullName();
        mockMvc.perform(post("/organization/public")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createOrganizationWithBlankName() throws Exception {
        requestDtoWithBlankName = utils.generateRequestDtoWithBlankName();
        mockMvc.perform(post("/organization/public")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createOrganizationWithNullImage() throws Exception {
        requestDtoWithNullImage = utils.generateRequestDtoWithNullImage();
        mockMvc.perform(post("/organization/public")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullImage))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createOrganizationWithBlankImage() throws Exception {
        requestDtoWithBlankImage = utils.generateRequestDtoWithBlankImage();
        mockMvc.perform(post("/organization/public")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankImage))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createOrganizationWithNullWelcomeText() throws Exception {
        requestDtoWithNullWelcomeText = utils.generateRequestDtoWithNullWelcomeText();
        mockMvc.perform(post("/organization/public")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullWelcomeText))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createOrganizationWithBlankWelcomeText() throws Exception {
        requestDtoWithBlankWelcomeText = utils.generateRequestDtoWithBlankWelcomeText();
        mockMvc.perform(post("/organization/public")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankWelcomeText))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getOrganizationUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/organization/public")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }



}
