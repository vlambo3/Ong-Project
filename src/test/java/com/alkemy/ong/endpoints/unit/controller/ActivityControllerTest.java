package com.alkemy.ong.endpoints.unit.controller;

import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.ForbiddenException;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.service.IActivityService;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class ActivityControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private IActivityService service;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private GenericMapper genericMapper;
    @Autowired
    private ObjectMapper objectMapper;
    private ActivityRequestDTO activityDto;
    private ActivityRequestDTO modified;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        activityDto = new ActivityRequestDTO("test activity", "testcontent", "urlactivitytest");
        modified = new ActivityRequestDTO(
                "test activity modified",
                "test content modified",
                "urlactivitytest/modified");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewActivity_shouldReturnCreated() throws Exception {
        ActivityResponseDTO responseDTO = genericMapper.map(activityDto, ActivityResponseDTO.class);
        responseDTO.setId(1L);
        when(service.create(activityDto)).thenReturn(responseDTO);

        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(activityDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void createNewActivity_userRol_shouldForbidden() throws Exception {
        ActivityResponseDTO responseDTO = genericMapper.map(activityDto, ActivityResponseDTO.class);
        responseDTO.setId(1L);
        when(service.create(activityDto)).thenThrow(ForbiddenException.class);

        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(activityDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewActivity_nullName_shouldReturnInternalServerError() throws Exception {
        ActivityRequestDTO nullName = new ActivityRequestDTO();
        nullName.setImage("urlimage");
        nullName.setContent("content");
        when(service.create(nullName)).thenThrow(NullPointerException.class);

        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewActivity_nullImage_shouldReturnInternalServerError() throws Exception {
        ActivityRequestDTO nullImage = new ActivityRequestDTO();
        nullImage.setName("name activity");
        nullImage.setContent("content");
        when(service.create(nullImage)).thenThrow(NullPointerException.class);

        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewActivity_nullContent_shouldReturnInternalServerError() throws Exception {
        ActivityRequestDTO nullContent = new ActivityRequestDTO();
        nullContent.setName("name activity");
        nullContent.setImage("urlimage");
        when(service.create(nullContent)).thenThrow(NullPointerException.class);

        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateActivity_shouldReturnOk() throws Exception {
        Long id = 1L;
        when(service.update(id, modified)).thenReturn(genericMapper.map(modified, ActivityResponseDTO.class));

        mockMvc.perform(put("/activities/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modified))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void updateActivity_userRol_shouldReturnForbidden() throws Exception {
        Long id = 1L;
        when(service.update(id, modified)).thenThrow(ForbiddenException.class);

        mockMvc.perform(put("/activities/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modified))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateActivity_idInvalid_shouldReturnBadRequest() throws Exception {
        Long id = -1L;
        when(service.update(id, modified)).thenThrow(BadRequestException.class);

        mockMvc.perform(put("/activities/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modified))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }
}