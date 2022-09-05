package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDto;
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
    public ResponseEntity<TestimonialDto> save(@Valid @RequestBody TestimonialDto testimonial) {
        TestimonialDto savedTestimonial = service.save(testimonial);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestimonial);
    }

    @PutMapping("/:{id}")
    public ResponseEntity<TestimonialDto> update(@Valid @RequestBody TestimonialDto newTestimonial, @PathVariable Long id) {
        TestimonialDto updatedTestimonial = service.update(newTestimonial, id);
        return ResponseEntity.ok(updatedTestimonial);
    }

}
