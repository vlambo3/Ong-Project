package com.alkemy.ong.controller;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;


import com.alkemy.ong.dto.category.CategoryNameDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import com.alkemy.ong.service.ICategoryService;

import lombok.RequiredArgsConstructor;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService service;

    private final CategoryRepository repository;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> addNewCategory(@RequestBody @Valid CategoryRequestDto dto){

        return ResponseEntity.status(CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryNameDto>> getAll() {
        List<CategoryNameDto> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @DeleteMapping("/:{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable Long id,@RequestBody @Valid CategoryRequestDto dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/page")
    public Page<Category> loadCategoriesPage(@PageableDefault(page=0, size = 10)
                                             @SortDefault.SortDefaults({
                                                     @SortDefault(sort = "name", direction = Sort.Direction.ASC)
                                             })Pageable pageable) {
        return repository.findAllPage(pageable);
    }
}
