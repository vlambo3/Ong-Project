package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.security.dto.exception.UnableToSaveEntityException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.service.ITestimonialService;
import com.alkemy.ong.repository.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class TestimonialServiceImpl implements ITestimonialService {

    private final TestimonialRepository repository;
    private final TestimonialMapper mapper;
    private final MessageSource messageSource;

    public TestimonialDto save(TestimonialDto dto) {
        try {
            Testimonial entity = mapper.testimonialDto2TestimonialEntity(dto);
            Testimonial savedEntity = repository.save(entity);
            return mapper.testimonialEntity2testimonialDto(savedEntity);
        } catch (Exception e){
            throw new UnableToSaveEntityException(messageSource.getMessage("unable-to-save-entity", null, Locale.US));
        }
    }
}
