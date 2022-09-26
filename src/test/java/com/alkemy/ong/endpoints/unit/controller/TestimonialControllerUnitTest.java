package com.alkemy.ong.endpoints.unit.controller;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;
import com.alkemy.ong.endpoints.util.testimonial.TestimonialTestUtils;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.ITestimonialService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class TestimonialControllerUnitTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @MockBean
    private ITestimonialService service;
    @Autowired
    private TestimonialTestUtils utils;
    @Autowired
    private ObjectMapper objectMapper;
    private TestimonialRequestDto requestDto;
    private TestimonialResponseDto responseDto;
    private TestimonialRequestDto requestDtoWithNullName;
    private TestimonialRequestDto requestDtoWithBlankName;
    private TestimonialRequestDto requestDtoWithNullContent;
    private TestimonialRequestDto requestDtoWithBlankContent;
    private PageDto<TestimonialResponseDto> pageDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        requestDto = utils.generateRequestDto();
        responseDto = utils.generateResponseDto();
        requestDtoWithNullName = utils.generateRequestDtoWithNullName();
        requestDtoWithBlankName = utils.generateRequestDtoWithBlankName();
        requestDtoWithNullContent = utils.generateRequestDtoWithNullContent();
        requestDtoWithBlankContent = utils.generateRequestDtoWithBlankContent();
        pageDto = utils.generatePageDto();
    }

    @Test
    void createTestimonialUserNotAuthenticated() throws Exception {
        mockMvc.perform(post("/testimonials")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void createTestimonialUser() throws Exception {
        mockMvc.perform(post("/testimonials")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createTestimonialAdmin() throws Exception {
        when(service.save(requestDto)).thenReturn(responseDto);
        mockMvc.perform(post("/testimonials")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createTestimonialWithNullName() throws Exception {
        mockMvc.perform(post("/testimonials")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createTestimonialWithBlankName() throws Exception {
        mockMvc.perform(post("/testimonials")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createTestimonialWithNullContent() throws Exception {
        mockMvc.perform(post("/testimonials")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullContent))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createTestimonialWithBlankContent() throws Exception {
        mockMvc.perform(post("/testimonials")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankContent))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    void getTestimonialPageUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/testimonials?page=1")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getTestimonialPageUser() throws Exception {
        when(service.getPage(1)).thenReturn(pageDto);
        mockMvc.perform(get("/testimonials?page=1")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getTestimonialPageAdmin() throws Exception {
        when(service.getPage(1)).thenReturn(pageDto);
        mockMvc.perform(get("/testimonials?page=1")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getTestimonialPageNegativeIndex() throws Exception {
        when(service.getPage(-1)).thenThrow(BadRequestException.class);
        mockMvc.perform(get("/testimonials?page=-1")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                        .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getTestimonialPageEmptyList() throws Exception {
        when(service.getPage(0)).thenThrow(EmptyListException.class);
        mockMvc.perform(get("/testimonials?page=0")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getTestimonialPageNotFound() throws Exception {
        when(service.getPage(10)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/testimonials?page=10")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                        .andExpect(status().isNotFound());
    }

    @Test
    void updateTestimonialUserNotAuthenticated() throws Exception {
        mockMvc.perform(put("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void updateTestimonialUser() throws Exception {
        mockMvc.perform(put("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateTestimonialAdmin() throws Exception {
        when(service.update(requestDto, 1L)).thenReturn(responseDto);
        mockMvc.perform(put("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateTestimonialWithNullName() throws Exception {
        mockMvc.perform(put("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateTestimonialWithBlankName() throws Exception {
        mockMvc.perform(put("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateTestimonialWithNullContent() throws Exception {
        mockMvc.perform(put("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullContent))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateTestimonialWithBlankContent() throws Exception {
        mockMvc.perform(put("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankContent))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteTestimonialUserNotAuthenticated() throws Exception {
        mockMvc.perform(delete("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void deleteTestimonialUser() throws Exception {
        mockMvc.perform(delete("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteTestimonialAdmin() throws Exception {
        mockMvc.perform(delete("/testimonials/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isOk());
    }

}
