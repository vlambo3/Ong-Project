package com.alkemy.ong.controller;

import com.alkemy.ong.dto.slide.SlideBasicResponseDto;
import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.service.ISlideService;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.status(OK).body(allSlides);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlideResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.status(OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<SlideResponseDto> createNewSlide(@Valid @RequestBody SlideRequestDto dto) throws Exception{
        return ResponseEntity.status(CREATED).body(service.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideResponseDto> updateSlide(@Valid @RequestBody SlideRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.status(OK).body(service.update(dto, id));
    }

}
