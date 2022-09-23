package com.alkemy.ong.endpoints.unit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.endpoints.util.news.NewsTestUtils;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.IdNullOrNegativeException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.service.INewsService;
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

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
public class NewsControllerUnitTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @MockBean
    private INewsService newsService;
    @MockBean
    private ICommentService commentService;
    @Autowired
    private NewsTestUtils utils;
    @Autowired
    private ObjectMapper objectMapper;

    private NewsRequestDto requestDto;
    private NewsResponseDto responseDto;
    private NewsRequestDto requestDtoWithNullName;
    private NewsRequestDto requestDtoWithBlankName;
    private NewsRequestDto requestDtoWithNullContent;
    private NewsRequestDto requestDtoWithBlankContent;
    private NewsRequestDto requestDtoWithNullImage;
    private NewsRequestDto requestDtoWithBlankImage;
    private PageDto<NewsResponseDto> pageDto;
    private List<CommentBasicResponseDto> commentList;


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
        requestDtoWithNullImage = utils.generateRequestDtoWithNullImage();
        requestDtoWithBlankImage = utils.generateRequestDtoWithBlankImage();
        pageDto = utils.generatePageDto();
        commentList = utils.generateCommentList();
    }

    @Test
    void createNewsUserNotAuthenticated() throws Exception {
        mockMvc.perform(post("/news")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
                .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void createNewsUser() throws Exception {
        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewsAdmin() throws Exception {
        when(newsService.create(requestDto)).thenReturn(responseDto);
        mockMvc.perform(post("/news")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
                .with(user("admin").roles("ADMIN"))
                .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewsWithNullName() throws Exception {
        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewsWithBlankName() throws Exception {
        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewsWithNullContent() throws Exception {
        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullContent))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewsWithBlankContent() throws Exception {
        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankContent))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewsWithNullImage() throws Exception {
        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullImage))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewsWithBlankImage() throws Exception {
        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankImage))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getNewsUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/news")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getNewsPageUser() throws Exception {
        when(newsService.getPage(1)).thenReturn(pageDto);
        mockMvc.perform(get("/news?page=1")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getNewsPageAdmin() throws Exception {
        when(newsService.getPage(1)).thenReturn(pageDto);
        mockMvc.perform(get("/news?page=1")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getNewsPageNegativeIndex() throws Exception {
        when(newsService.getPage(-1)).thenThrow(BadRequestException.class);
        mockMvc.perform(get("/news?page=-1")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getNewsEmptyList() throws Exception {
        when(newsService.getPage(0)).thenThrow(EmptyListException.class);
        mockMvc.perform(get("/news?page=0")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getNewsEmptyPage() throws Exception {
        when(newsService.getPage(10)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/news?page=10")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getNewsByIdUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/news/1")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getNewsByIdUser() throws Exception {
        mockMvc.perform(get("/news/1")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getNewsByIdAdmin() throws Exception {
        when(newsService.getById(1L)).thenReturn(responseDto);
        mockMvc.perform(get("/news/1")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getNewsByIdNegativeIndex() throws Exception {
        when(newsService.getById(-1L)).thenThrow(ArithmeticException.class);
        mockMvc.perform(get("/news/-1")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getNewsByIdNotFound() throws Exception {
        when(newsService.getById(10L)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/news/10")
                        .contentType(APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateNewsUserNotAuthenticated() throws Exception {
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void updateNewsUser() throws Exception {
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateNewsAdmin() throws Exception {
        when(newsService.update(requestDto, 1L)).thenReturn(responseDto);
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateNewsWithNullName() throws Exception {
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateNewsWithBlankName() throws Exception {
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankName))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateNewsWithNullContent() throws Exception {
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullContent))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateNewsWithBlankContent() throws Exception {
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankContent))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateNewsWithNullImage() throws Exception {
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithNullImage))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateNewsWithBlankImage() throws Exception {
        mockMvc.perform(put("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoWithBlankImage))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteNewsUserNotAuthenticated() throws Exception {
        mockMvc.perform(delete("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void deleteNewsUser() throws Exception {
        mockMvc.perform(delete("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteNewsAdmin() throws Exception {
        mockMvc.perform(delete("/news/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    void getCommentsByNewsIdUserNotAuthenticated() throws Exception {
        mockMvc.perform(get("/news/1/comments")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getCommentsByNewsIdUser() throws Exception {
        mockMvc.perform(get("/news/1/comments")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getCommentsByNewsIdAdmin() throws Exception {
        when(commentService.getAllCommentsByNewsId(1L)).thenReturn(commentList);
        mockMvc.perform(get("/news/1/comments")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getCommentsByNewsIdNegativeIndex() throws Exception {
        when(commentService.getAllCommentsByNewsId(-1L)).thenThrow(IdNullOrNegativeException.class);
        mockMvc.perform(get("/news/-1/comments")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getCommentsByNewsIdEmptyList() throws Exception {
        when(commentService.getAllCommentsByNewsId(10L)).thenThrow(EmptyListException.class);
        mockMvc.perform(get("/news/1/comments")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

}
