package com.alkemy.ong.endpoints.unit.controller;

import com.alkemy.ong.dto.contact.ContactRequestDto;
import com.alkemy.ong.dto.contact.ContactResponseDto;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.service.IContactService;
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

import java.util.ArrayList;
import java.util.List;

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
public class ContactControllerUnitTest {

    @Autowired
    private WebApplicationContext context;
    protected MockMvc mockMvc;

    @MockBean
    private IContactService service;
    @Autowired
    private GenericMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;

    private ContactRequestDto contactDto;

    List<ContactRequestDto> contactDtoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        contactDto = new ContactRequestDto();
        contactDto.setName("name");
        contactDto.setPhone("9876543210");
        contactDto.setEmail("otherexample@gmail.com");
        contactDto.setMessage("message");

        contactDtoList.add(contactDto);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createContactAdmin201() throws Exception {
        when(service.save(contactDto)).thenReturn(mapper.map(contactDto, ContactResponseDto.class));
        mockMvc.perform(post("/contacts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void createContactUser201() throws Exception {
        when(service.save(contactDto)).thenReturn(mapper.map(contactDto, ContactResponseDto.class));
        mockMvc.perform(post("/contacts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    void createContactFailBecauseUserNotAuthenticated() throws Exception {
        mockMvc.perform(post("/contacts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createContactAdminWithIncorrectName500() throws Exception {
        ContactRequestDto contactDto1 = new ContactRequestDto(null, "864534", "ab1@example.com", "message");
        when(service.save(contactDto1)).thenReturn(mapper.map(contactDto1, ContactResponseDto.class));
        mockMvc.perform(post("/contacts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto1))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "USER")
    void createContactUserWithIncorrectName500() throws Exception {
        ContactRequestDto contactDto1 = new ContactRequestDto(null, "864534", "ab1@example.com", "message");
        when(service.save(contactDto1)).thenReturn(mapper.map(contactDto1, ContactResponseDto.class));
        mockMvc.perform(post("/contacts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto1))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createContactAdminWithIncorrectEmail500() throws Exception {
        ContactRequestDto contactDto2 = new ContactRequestDto("name", "8783435", "35/4@a|d/$#j", "message2");
        when(service.save(contactDto2)).thenReturn(mapper.map(contactDto2, ContactResponseDto.class));
        mockMvc.perform(post("/contacts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto2))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "USER")
    void createContactUserWithIncorrectEmail500() throws Exception {
        ContactRequestDto contactDto2 = new ContactRequestDto("name", "8783435", "35/4@a|d/$#j", "message2");
        when(service.save(contactDto2)).thenReturn(mapper.map(contactDto2, ContactResponseDto.class));
        mockMvc.perform(post("/contacts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto2))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllContactsAdmin200() throws Exception {
        when(service.findAll()).thenReturn(mapper.mapAll(contactDtoList, ContactResponseDto.class));
        mockMvc.perform(get("/contacts")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllContactsBeingUser403() throws Exception {
        when(service.findAll()).thenReturn(mapper.mapAll(contactDtoList, ContactResponseDto.class));
        mockMvc.perform(get("/contacts")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllContactsAdminFailBecauseListIsEmpty200() throws Exception {
        contactDtoList = null;
        mockMvc.perform(get("/contacts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDtoList))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void getAllContactsFailBecauseUserNotAuthenticated403() throws Exception {
        mockMvc.perform(get("/contacts")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
}
