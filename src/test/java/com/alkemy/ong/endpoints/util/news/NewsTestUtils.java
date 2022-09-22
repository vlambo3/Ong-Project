package com.alkemy.ong.endpoints.util.news;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.comment.CommentBasicResponseDto;
import com.alkemy.ong.dto.news.NewsRequestDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsTestUtils {

    public NewsRequestDto generateRequestDto() {
        NewsRequestDto dto = new NewsRequestDto();
        dto.setName("News name");
        dto.setContent("News content");
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

    public PageDto<NewsResponseDto> generatePageDto() {
        PageDto<NewsResponseDto> page = new PageDto<>();
        return page;
    }

    public List<CommentBasicResponseDto> generateCommentList() {
        return new ArrayList<>();
        //List<CommentBasicResponseDto> list = new ArrayList<>();
        //return list;
    }

}
