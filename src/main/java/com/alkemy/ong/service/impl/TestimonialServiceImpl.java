package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.testimonial.TestimonialRequestDto;
import com.alkemy.ong.dto.testimonial.TestimonialResponseDto;
import com.alkemy.ong.exception.*;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.service.IAmazonClient;
import com.alkemy.ong.service.ITestimonialService;
import com.alkemy.ong.repository.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final IAmazonClient amazonClient;

    public TestimonialResponseDto save(TestimonialRequestDto dto) {
        try {
            Testimonial entity = mapper.map(dto, Testimonial.class);
            entity.setCreationDate(LocalDateTime.now());
            entity.setImage(amazonClient.uploadFile(dto.getImage(), dto.getName()));
            entity = repository.save(entity);
            return mapper.map(entity, TestimonialResponseDto.class);
        } catch (Exception e){
            throw new UnableToSaveEntityException(messageSource.getMessage("unable-to-save-testimonial", null, Locale.US));
        }
    }

    public PageDto<TestimonialResponseDto> getPage(int pageNum) {
        if (pageNum < 1)
            throw new BadRequestException(messageSource.getMessage("negative-page-number", null, Locale.US));
        Pageable pageable = PageRequest.of(pageNum - 1, 10);
        Page<Testimonial> page = repository.findAll(pageable);
        if (page.getTotalPages() == 0)
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        if (page.isEmpty())
            throw new NotFoundException(messageSource.getMessage("last-page-is", new Object[]{ page.getTotalPages() }, Locale.US));
        return mapper.mapPage(page, TestimonialResponseDto.class, "testimonials");
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
            throw new UnableToUpdateEntityException(messageSource.getMessage("unable-to-update-testimonial", new Object[] {id}, Locale.US));
        }
    }

    public void delete(Long id){
        Testimonial entity = getTestimonialById(id);
        try {
            entity.setUpdateDate(LocalDateTime.now());
            repository.save(entity);
            repository.deleteById(id);
        }catch (Exception e) {
            throw new UnableToDeleteEntityException(messageSource.getMessage("unable-to-delete-testimonial", new Object[] {id}, Locale.US));
        }
    }

    private Testimonial getTestimonialById(Long id) {
        Optional<Testimonial> entity = repository.findById(id);
        if (entity.isEmpty())
            throw new NotFoundException(messageSource.getMessage("testimonial-not-found", new Object[] {id}, Locale.US));
        return entity.get();
    }

}
