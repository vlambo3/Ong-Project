package com.alkemy.ong.service;

import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;

public interface ICategoryService {
    
    CategoryResponseDto create(CategoryRequestDto dto);
}
