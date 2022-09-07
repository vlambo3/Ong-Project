package com.alkemy.ong.service;

import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;

public interface ITestimonialService {

    TestimonialResponseDto save(TestimonialRequestDto dto);

    TestimonialResponseDto update(TestimonialRequestDto newTestimonial, Long id);

}
