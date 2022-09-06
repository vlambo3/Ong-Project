package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;
import com.alkemy.ong.model.Testimonial;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

    public Testimonial testimonialDto2TestimonialEntity(TestimonialRequestDto dto) {
        Testimonial entity = new Testimonial();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setContent(dto.getContent());
        return entity;
    }

    public TestimonialResponseDto testimonialEntity2testimonialDto(Testimonial entity) {
        TestimonialResponseDto dto = new TestimonialResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setContent(entity.getContent());
        return dto;
    }
}
