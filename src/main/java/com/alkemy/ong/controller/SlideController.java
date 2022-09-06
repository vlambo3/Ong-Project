package com.alkemy.ong.controller;

import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.service.impl.SlideServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideServiceImpl slideService;

    @GetMapping
    public ResponseEntity<List<SlideResponseDto>> getAll(){
        List<SlideResponseDto> allSlides = slideService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(allSlides);
    }
     /*@GetMapping("/all")
    public  ResponseEntity<List<PeliculaBasicDTO>> getAllBasic(){
        List<PeliculaBasicDTO> basicMovies = peliculaService.getAllBasics();
        return ResponseEntity.status(HttpStatus.OK).body(basicMovies);
    }*/

}
