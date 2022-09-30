package com.alkemy.ong.controller;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;
import com.alkemy.ong.service.ITestimonialService;
import com.alkemy.ong.utils.documentation.ITestimonialController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/testimonials")
@RequiredArgsConstructor
@RestController
@Api(value = "Testimonial controller", description = "This controller has a CRUD for testimonials")
public class TestimonialController implements ITestimonialController {

        private final ITestimonialService service;

        @PostMapping
        @ResponseStatus(CREATED)
        public ResponseEntity<TestimonialResponseDto> addNewTestimonial(@Valid @RequestBody TestimonialRequestDto testimonial) {
                return ResponseEntity.status(CREATED).body(service.save(testimonial));
        }

        @PutMapping("/{id}")
        public ResponseEntity<TestimonialResponseDto> updateTestimonial(@Valid @RequestBody TestimonialRequestDto newTestimonial,
                                                                        @PathVariable Long id) {
                return ResponseEntity.ok(service.update(newTestimonial, id));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTestimonial(@PathVariable Long id) {
                service.delete(id);
                return ResponseEntity.status(OK).build();
        }

        @GetMapping
        public ResponseEntity<PageDto<TestimonialResponseDto>> getPage(@RequestParam int page) {
                return ResponseEntity.status(OK).body(service.getPage(page));
        }
}
