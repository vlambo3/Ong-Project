package com.alkemy.ong.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.UnableToSaveEntityException;

import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {


    private final CategoryRepository categoryRepository;
    private final MessageSource messageSource;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponseDto create(CategoryRequestDto dto) {
        Category categorySaved;
        try {
            List<Category> categories = categoryRepository.findAll();

            categories.forEach(c -> {
                if (c.getName().equalsIgnoreCase(dto.getName())) {
                    throw new AlreadyExistsException(
                            messageSource.getMessage("already-exists", new Object[] { "Category name" }, Locale.US));
                }
            });

            Category category = mapper.categoryDto2CategoryEntity(dto);

            category.setCreationTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            category.setUpdateTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

            /*
             * TODO: <- ImageService should validate and return the path of the File...
             * example:
             * category.setImage(imageService.getImage(dto.getImage()));
             */
            category.setImage(dto.getImage());

            categorySaved = categoryRepository.save(category);
        } catch (Exception e) {
            throw new UnableToSaveEntityException(
                    messageSource.getMessage("unable-to-save-entity", new Object[] { "the new Category: " }, Locale.US)
                    + e.getMessage());
        }

        return mapper.CategoryEntity2CategoryDto(categorySaved);
    }
}
