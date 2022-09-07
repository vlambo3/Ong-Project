package com.alkemy.ong.controller;

import com.alkemy.ong.dto.slide.SlideBasicResponseDto;
import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.service.impl.SlideServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideServiceImpl service;

    @PostMapping
    public ResponseEntity<SlideResponseDto> createNewSlide(@Valid @RequestBody SlideRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<SlideBasicResponseDto>> getAll(){
        List<SlideBasicResponseDto> allSlides = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(allSlides);
    }

}
