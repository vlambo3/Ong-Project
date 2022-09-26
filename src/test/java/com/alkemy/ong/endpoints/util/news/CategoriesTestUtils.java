package com.alkemy.ong.endpoints.util.news;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.category.CategoryNameDto;
import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriesTestUtils {

    public CategoryRequestDto generateRequestDto(){
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setName("Name category");
        dto.setDescription("Description category");
        dto.setImage("image.jpg");
        return dto;
    }

    public CategoryResponseDto generateResponseDto(){
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(1L);
        dto.setName("Name category");
        dto.setDescription("Description category");
        dto.setImage("image.jpg");
        return dto;
    }

    public CategoryRequestDto generateRequestDtoWithNullDescription(){
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setName("Name category");
        dto.setDescription(null);
        dto.setImage("Image.jpg");
        return dto;
    }

    public CategoryRequestDto generateRequestDtoWithBlankDescription(){
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setName("Name category");
        dto.setDescription("");
        dto.setImage("Imagen.jpg");
        return dto;
    }

    public CategoryRequestDto generateRequestDtoWithNullImage(){
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setName("Name category");
        dto.setDescription("Description category");
        dto.setImage(null);
        return dto;
    }

    public CategoryRequestDto generateRequestDtoWithBlankImage(){
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setName("Name category");
        dto.setDescription("Description category");
        dto.setImage("");
        return dto;
    }

    public CategoryRequestDto generateRequestDtoWithNullName(){
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setName(null);
        dto.setDescription("Description category");
        dto.setImage("Image.jpg");
        return dto;
    }

    public CategoryRequestDto generateRequestDtoWithBlankName(){
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setName("");
        dto.setDescription("Description category");
        dto.setImage("Imagen.jpg");
        return dto;
    }

    public List<CategoryNameDto> generateCategoryNameList(){return new ArrayList<>();}

    public PageDto<CategoryResponseDto> generatePageDto(){
        PageDto<CategoryResponseDto> page = new PageDto<>();
        return page;
    }


}