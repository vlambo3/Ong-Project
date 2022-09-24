package com.alkemy.ong.endpoints.util.testimonial;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TestimonialTestUtils {


    public TestimonialRequestDto generateRequestDto() {
        TestimonialRequestDto dto = new TestimonialRequestDto();
        dto.setName("Testimonial name");
        dto.setImage("TestimonialImage.jpeg");
        dto.setContent("Testimonial content");
        return dto;
    }

    public TestimonialResponseDto generateResponseDto() {
        TestimonialResponseDto dto = new TestimonialResponseDto();
        dto.setId(1L);
        dto.setName("Testimonial name");
        dto.setImage("TestimonialImage.jpeg");
        dto.setContent("Testimonial content");
        return dto;
    }

    public TestimonialRequestDto generateRequestDtoWithNullName() {
        TestimonialRequestDto dto = new TestimonialRequestDto();
        dto.setName(null);
        dto.setImage("TestimonialImage.jpeg");
        dto.setContent("Testimonial content");
        return dto;
    }

    public TestimonialRequestDto generateRequestDtoWithBlankName() {
        TestimonialRequestDto dto = new TestimonialRequestDto();
        dto.setName("");
        dto.setImage("TestimonialImage.jpeg");
        dto.setContent("Testimonial content");
        return dto;
    }

    public TestimonialRequestDto generateRequestDtoWithNullContent() {
        TestimonialRequestDto dto = new TestimonialRequestDto();
        dto.setName("Testimonial name");
        dto.setImage("TestimonialImage.jpeg");
        dto.setContent(null);
        return dto;
    }

    public TestimonialRequestDto generateRequestDtoWithBlankContent() {
        TestimonialRequestDto dto = new TestimonialRequestDto();
        dto.setName("Testimonial name");
        dto.setImage("TestimonialImage.jpeg");
        dto.setContent("");
        return dto;
    }

    public PageDto<TestimonialResponseDto> generatePageDto() {
        PageDto<TestimonialResponseDto> page = new PageDto<>();
        return page;
    }

}
