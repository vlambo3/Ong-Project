package com.alkemy.ong.controller;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;


import com.alkemy.ong.dto.category.CategoryNameDto;
import com.alkemy.ong.dto.news.NewsResponseDto;
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

}
