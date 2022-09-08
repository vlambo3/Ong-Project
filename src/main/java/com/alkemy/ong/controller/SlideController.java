package com.alkemy.ong.controller;


import com.alkemy.ong.dto.slide.SlideBasicResponseDto;
import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.service.ISlideService;

import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final ISlideService service;


    @GetMapping
    public ResponseEntity<List<SlideBasicResponseDto>> getAllBasic(){
        List<SlideBasicResponseDto> allSlides = service.getAllBasic();
        return ResponseEntity.status(OK).body(allSlides);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SlideResponseDto>> getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(OK).body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<SlideResponseDto> createNewSlide(@Valid @RequestBody SlideRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

}
