package com.alkemy.ong.controller;

import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;
import com.alkemy.ong.service.ITestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/testimonials")
@RequiredArgsConstructor
@RestController
public class TestimonialController {

    private final ITestimonialService service;

    @PostMapping
    public ResponseEntity<TestimonialResponseDto> save(@Valid @RequestBody TestimonialRequestDto testimonial) {
        TestimonialResponseDto savedTestimonial = service.save(testimonial);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestimonial);
    }

    @PutMapping("/:{id}")
    public ResponseEntity<TestimonialResponseDto> update(@Valid @RequestBody TestimonialRequestDto newTestimonial, @PathVariable Long id) {
        TestimonialResponseDto updatedTestimonial = service.update(newTestimonial, id);
        return ResponseEntity.ok(updatedTestimonial);
    }

    @DeleteMapping("/:{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
