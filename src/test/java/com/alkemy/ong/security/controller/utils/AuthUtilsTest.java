package com.alkemy.ong.security.controller.utils;

import com.alkemy.ong.security.dto.UserRequestDto;

public class AuthUtilsTest {
    
    public UserRequestDto generateRequestDto() {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName("User name");
        dto.setLastName("User lastName");
        dto.setImage("NewsImage.jpeg");
        dto.setCategoryId(1L);
        return dto;
    }

    public NewsResponseDto generateResponseDto() {
        NewsResponseDto dto = new NewsResponseDto();
        dto.setId(1L);
        dto.setName("News name");
        dto.setContent("News content");
        dto.setImage("NewsImage.jpeg");
        dto.setCategoryId(1L);
        dto.setCreationDate(LocalDateTime.now());
        return dto;
    }

    public NewsRequestDto generateRequestDtoWithNullName() {
        NewsRequestDto dto = new NewsRequestDto();
        dto.setName(null);
        dto.setContent("News content");
        dto.setImage("NewsImage.jpeg");
        dto.setCategoryId(1L);
        return dto;
    }

    public NewsRequestDto generateRequestDtoWithBlankName() {
        NewsRequestDto dto = new NewsRequestDto();
        dto.setName("");
        dto.setContent("News content");
        dto.setImage("NewsImage.jpeg");
        dto.setCategoryId(1L);
        return dto;
    }

    public NewsRequestDto generateRequestDtoWithNullContent() {
        NewsRequestDto dto = new NewsRequestDto();
        dto.setName("News name");
        dto.setContent(null);
        dto.setImage("NewsImage.jpeg");
        dto.setCategoryId(1L);
        return dto;
    }

    public NewsRequestDto generateRequestDtoWithBlankContent() {
        NewsRequestDto dto = new NewsRequestDto();
        dto.setName("News name");
        dto.setContent("");
        dto.setImage("NewsImage.jpeg");
        dto.setCategoryId(1L);
        return dto;
    }

    public NewsRequestDto generateRequestDtoWithNullImage() {
        NewsRequestDto dto = new NewsRequestDto();
        dto.setName("News name");
        dto.setContent("News content");
        dto.setImage(null);
        dto.setCategoryId(1L);
        return dto;
    }

    public NewsRequestDto generateRequestDtoWithBlankImage() {
        NewsRequestDto dto = new NewsRequestDto();
        dto.setName("News name");
        dto.setContent("News content");
        dto.setImage("");
        dto.setCategoryId(1L);
        return dto;
    }
}
