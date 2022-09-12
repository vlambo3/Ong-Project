package com.alkemy.ong.controller;

import com.alkemy.ong.dto.slide.SlideBasicResponseDto;
import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.alkemy.ong.service.ISlideService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final ISlideService service;


    @GetMapping
    public ResponseEntity<List<SlideBasicResponseDto>> getAll(){
        List<SlideBasicResponseDto> allSlides = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(allSlides);
    }

    @PostMapping
    public ResponseEntity<SlideResponseDto> createNewSlide(@Valid @RequestBody SlideRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<SlideResponseDto> updateSlide(@Valid @RequestBody SlideRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto, id));

    }
}
