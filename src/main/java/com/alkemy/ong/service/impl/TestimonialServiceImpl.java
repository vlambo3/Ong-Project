package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.UnableToDeleteEntityException;
import com.alkemy.ong.exception.UnableToSaveEntityException;
import com.alkemy.ong.exception.UnableToUpdateEntityException;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.service.ITestimonialService;
import com.alkemy.ong.repository.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TestimonialServiceImpl implements ITestimonialService {

    private final TestimonialRepository repository;
    private final GenericMapper mapper;
    private final MessageSource messageSource;

    public TestimonialResponseDto save(TestimonialRequestDto dto) {
        try {
            Testimonial entity = mapper.map(dto, Testimonial.class);
            entity.setCreationDate(LocalDateTime.now());
            entity = repository.save(entity);
            return mapper.map(entity, TestimonialResponseDto.class);
        } catch (Exception e){
            throw new UnableToSaveEntityException(messageSource.getMessage("unable-to-save-testimonial", null, Locale.US));
        }
    }

    public TestimonialResponseDto update(TestimonialRequestDto dto, Long id) {
        Testimonial entity = getTestimonialById(id);
        try {
            Testimonial updatedEntity = mapper.map(dto, Testimonial.class);
            updatedEntity.setId(entity.getId());
            updatedEntity.setCreationDate(entity.getCreationDate());
            updatedEntity.setUpdateDate(LocalDateTime.now());
            // TODO: Implement image service
            updatedEntity = repository.save(updatedEntity);
            return mapper.map(updatedEntity, TestimonialResponseDto.class);
        } catch (Exception e) {
            throw new UnableToUpdateEntityException(messageSource.getMessage("unable-to-update-testimonial", null, Locale.US));
        }
    }

    private Testimonial getTestimonialById(Long id) {
        Optional<Testimonial> entity = repository.findById(id);
        if (entity.isEmpty())
            throw new NotFoundException(messageSource.getMessage("testimonial-not-found", null , Locale.US));
        return entity.get();
    }

    public void delete(Long id){
        Testimonial entity = getTestimonialById(id);
        try {
            entity.setUpdateDate(LocalDateTime.now());
            repository.save(entity);
            repository.deleteById(id);
        }catch (Exception e) {
            throw new UnableToDeleteEntityException(messageSource.getMessage("unable-to-delete-testimonial", null, Locale.US));
        }
    }

}
