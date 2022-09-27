package com.alkemy.ong.endpoints.unit.controller;


import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.category.CategoryNameDto;
import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import com.alkemy.ong.endpoints.util.news.CategoriesTestUtils;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.ICategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class CategoryControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService categoryService;
    @Autowired
    private CategoriesTestUtils utils;
    @Autowired
    private ObjectMapper objectMapper;
    private CategoryRequestDto categoryDto;

    List<CategoryRequestDto> categoryDtoList = new ArrayList<>();

    private CategoryRequestDto requestDto;
    private CategoryResponseDto responseDto;
    private CategoryRequestDto requestDtoWithNullDescription;
    private CategoryRequestDto requestDtoWithBlankDescription;
    private CategoryRequestDto requestDtoWithNullName;
    private CategoryRequestDto requestDtoWithBlankName;
    private PageDto<CategoryResponseDto> pageDto;
    private List<CategoryNameDto> categoryList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        requestDto = utils.generateRequestDto();
        responseDto = utils.generateResponseDto();
        requestDtoWithNullName = utils.generateRequestDtoWithNullName();
        requestDtoWithBlankName = utils.generateRequestDtoWithBlankName();
        pageDto = utils.generatePageDto();
        categoryList = utils.generateCategoryNameList();
    }
    @Test
    void createCategoryUserNotAuthenticated() throws Exception {
        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }
    @Test
    @WithMockUser(roles = "USER")
    void createCategoryUser() throws Exception {
        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void createCategoryAdmin() throws Exception {
        when(categoryService.create(requestDto)).thenReturn(responseDto);
        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated());

    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void createCategoryWithNullName() throws Exception {
        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());

    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void createCategoryWithBlankName() throws Exception {
        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void createCategoryWithNullDescription() throws Exception {
        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullDescription))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void createCategoryWithBlankDescription() throws Exception {
        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankDescription))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getCategoryUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/categories")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = "USER")
    void getCategoryPageUser() throws Exception {
        when(categoryService.getPage(1)).thenReturn(pageDto);
        mockMvc.perform(get("/categories?page=1")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void getCategoryPageAdmin() throws Exception {
        when(categoryService.getPage(1)).thenReturn(pageDto);
        mockMvc.perform(get("/categories?page=1")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "USER")
    void getCategoryPageNegativeIndex() throws Exception {
        when(categoryService.getPage(-1)).thenThrow(BadRequestException.class);
        mockMvc.perform(get("/categories?page=-1")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(roles = "USER")
    void getCategoryEmptyPageList() throws Exception {
        when(categoryService.getPage(0)).thenThrow(EmptyListException.class);
        mockMvc.perform(get("/categories?page=0")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser
    void getCategoryEmptyPage() throws Exception {
        when(categoryService.getPage(10)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/categories?page=10")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }
    @Test
    void getCategoryByIdUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = "USER")
    void getCategoryByIdUser() throws Exception {
        mockMvc.perform(get("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void getCategoryByIdAdmin() throws Exception {
        when(categoryService.getById(1L)).thenReturn(responseDto);
        mockMvc.perform(get("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void getCategoryByIdNegativeIndex() throws Exception {
        when(categoryService.getById(-1L)).thenThrow(ArithmeticException.class);
        mockMvc.perform(get("/categories/-1")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void getCategoryByIdNotFound() throws Exception {
        when(categoryService.getById(10L)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/categories/10")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }
    @Test
    void updateCategoryUserNotAuthenticated() throws Exception {
        mockMvc.perform(put("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = "USER")
    void updateCategoryUser() throws Exception {
        mockMvc.perform(put("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void updateCategoryAdmin() throws Exception {
        when(categoryService.update(1L, requestDto)).thenReturn(responseDto);
        mockMvc.perform(put("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void updateCategoryWithNullName() throws Exception {
        mockMvc.perform(put("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void updateCategoryWithBlankName() throws Exception {
        mockMvc.perform(put("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void updateCategoryWithNullDescription() throws Exception {
        mockMvc.perform(put("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullDescription))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void updateCategoryWithBlankDescription() throws Exception {
        mockMvc.perform(put("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankDescription))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }
    @Test
    void deleteCategoryUserNotAuthenticated() throws Exception {
        mockMvc.perform(delete("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = "USER")
    void deleteCategoryUser() throws Exception {
        mockMvc.perform(delete("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteCategoryAdmin() throws Exception {
        mockMvc.perform(delete("/categories/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void getCategoryNameList() throws Exception {
        when(categoryService.getAll()).thenReturn(categoryList);
        mockMvc.perform(get("/categories/all")
                .contentType(APPLICATION_JSON)
                .with(user("admin").roles("ADMIN"))
                .with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void getCategoryEmptyList() throws Exception {
        when(categoryService.getAll().isEmpty()).thenThrow(EmptyListException.class);
        mockMvc.perform(get("/categories/all")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

}
