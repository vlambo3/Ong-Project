package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;

public interface ITestimonialService {

    TestimonialDto save(TestimonialDto dto);

    TestimonialDto update(TestimonialDto newTestimonial, Long id);

}
