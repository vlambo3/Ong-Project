package com.alkemy.ong.controller;

import static org.springframework.http.HttpStatus.*;

import com.alkemy.ong.dto.category.CategoryNameDto;
import com.alkemy.ong.utils.documentation.ICategoriesController;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.category.CategoryRequestDto;
import com.alkemy.ong.dto.category.CategoryResponseDto;
import com.alkemy.ong.service.ICategoryService;

import lombok.RequiredArgsConstructor;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Api(value = "Category controller", description = "This controller has a CRUD for categories")
public class CategoryController implements ICategoriesController {

    private final ICategoryService service;


    @PostMapping
    public ResponseEntity<CategoryResponseDto> addNewCategory(@RequestBody @Valid CategoryRequestDto dto) {
        return ResponseEntity.status(CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(OK).body(service.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryNameDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping
    public ResponseEntity<?> getPage(@RequestParam int page) {
        return ResponseEntity.ok(service.getPage(page));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }


}
