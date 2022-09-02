package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

    public Testimonial testimonialDto2TestimonialEntity(TestimonialDto dto) {
        Testimonial entity = new Testimonial();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setContent(dto.getContent());
        return entity;
    }

    public TestimonialDto testimonialEntity2testimonialDto(Testimonial entity) {
        TestimonialDto dto = new TestimonialDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setContent(entity.getContent());
        return dto;
    }
}
