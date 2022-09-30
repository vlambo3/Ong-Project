package com.alkemy.ong.endpoints.unit.controller;

import com.alkemy.ong.dto.member.MemberBasicResponseDto;
import com.alkemy.ong.dto.member.MemberRequestDto;
import com.alkemy.ong.dto.member.MemberResponseDto;
import com.alkemy.ong.endpoints.util.member.MemberTestUtils;
import com.alkemy.ong.service.IMemberService;
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
public class MembersControllerTest {


    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @MockBean
    private IMemberService service;
    @Autowired
    private MemberTestUtils utils;
    @Autowired
    private ObjectMapper objectMapper;
    private MemberRequestDto requestDto;
    private MemberResponseDto responseDto;
    private MemberBasicResponseDto basicResponseDto;
    private MemberRequestDto requestDtoWithNullName;
    private MemberRequestDto requestDtoWithBlankName;
    private MemberRequestDto requestDtoWithNullImage;
    private MemberRequestDto requestDtoWithBlankImage;
    private MemberRequestDto requestDtoWithNullDescription;
    private MemberRequestDto requestDtoWithBlankDescription;


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
    void createMemberAdmin() throws Exception {
        when(service.create(requestDto)).thenReturn(responseDto);
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createMemberWithNullName() throws Exception {
        requestDtoWithNullName = utils.generateRequestDtoWithNullName();
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createMemberWithBlankName() throws Exception {
        requestDtoWithBlankName = utils.generateRequestDtoWithBlankName();
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createMemberWithNullImage() throws Exception {
        requestDtoWithNullImage = utils.generateRequestDtoWithNullImage();
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullImage))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createMemberWithBlankImage() throws Exception {
        requestDtoWithBlankImage = utils.generateRequestDtoWithBlankImage();
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankImage))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createMemberWithNullDescriptionText() throws Exception {
        requestDtoWithNullDescription = utils.generateRequestDtoWithNullDescriptionText();
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullDescription))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createMemberWithBlankDescriptionText() throws Exception {
        requestDtoWithNullDescription = utils.generateRequestDtoWithBlankDescriptionText();
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullDescription))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getMemberUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/members")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
}