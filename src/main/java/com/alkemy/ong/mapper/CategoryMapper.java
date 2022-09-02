package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.category.CategoryNameDto;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import com.alkemy.ong.model.Category;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
 
    public Category categoryDto2CategoryEntity(CategoryRequestDto dto) {
        Category entity = new Category();
        
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    public CategoryResponseDto CategoryEntity2CategoryDto(Category entity) {        
       CategoryResponseDto dto = new CategoryResponseDto();

       dto.setId(entity.getId());
       dto.setName(entity.getName());
       dto.setDescription(entity.getDescription());
       dto.setCreationTimestamp(entity.getCreationTimestamp());
       dto.setUpdateTimeStamp(entity.getUpdateTimeStamp());
       dto.setDeleted(entity.isDeleted());
       dto.setImage(entity.getImage());

       return dto;
    }

    public List<CategoryNameDto> CategoryEntityList2CategoryNameDtoList(List<Category> entities) {
        List<CategoryNameDto> dtoList = new ArrayList<>();
        for (Category entity : entities) {
            CategoryNameDto dto = new CategoryNameDto();
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

}
