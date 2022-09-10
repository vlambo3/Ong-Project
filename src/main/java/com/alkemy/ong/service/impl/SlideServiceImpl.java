package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.slide.SlideBasicResponseDto;
import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.dto.slide.SlideResponseDTO;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.UnableToUpdateEntityException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.service.ISlideService;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements ISlideService {

    private final SlideRepository repository;

    private final OrganizationRepository organizationRepository;
   
    private final SlideMapper mapper;

    private final MessageSource messageSource;
      
    @Override
    public List<SlideBasicResponseDto> getAll() {
        List<Slide> slides =  repository.findAllByOrderByPositionAsc();
        if (slides.isEmpty())
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        return mapper.slideEntityList2DtoList(slides);
    }


    public SlideResponseDto create(SlideRequestDto dto) {

        Organization org = organizationRepository.findAll().get(0);

        Slide slide = mapper.slideDTO2SlideEntity(dto, org);

        List<Slide> slidesList = repository.findAll();

        int n = 0;

        if (dto.getPosition() == null){
            slide.setPosition(slidesList.size() + 1);
            slidesList.add(slide);
            n++;
        } else if (slidesList.isEmpty() || (slidesList.size() < dto.getPosition())) {
            slide.setPosition(slidesList.size()+1);
            slidesList.add(slide);
        } else if (!slidesList.isEmpty() && (slidesList.size() >= dto.getPosition())) {
            slide.setPosition(dto.getPosition());
            slidesList.add(dto.getPosition(), slide);
        }

        SlideResponseDto responseDTO = mapper.slideEntity2SlideDTO(repository.save(slide));

        if (n == 1)
            responseDTO.setMessage(messageSource.getMessage("slide-position", null, Locale.US));

        return responseDTO;

    }

    public List<SlideResponseDTO> findByOrganizationId(Long organizationId){
        List<SlideResponseDTO> slides = repository.findByOrganizationId(organizationId);

        if (slides.isEmpty()) {
            throw new EmptyListException(messageSource.getMessage
                    ("slide.list.empty", null, Locale.ENGLISH));
        }
        Collections.sort(slides, Comparator.comparing(SlideResponseDTO::getPosition));

        return slides;
    }

    public SlideResponseDto update(SlideRequestDto dto, Long id) {
        Slide entity = getSlideById(id);
        try {
            entity.setImageUrl(dto.getImageUrl());
            entity.setText(dto.getText());
            entity.setPosition(dto.getPosition());
            repository.save(entity);
            return mapper.slideEntity2SlideDTO(entity);
        } catch (Exception e) {
            throw new UnableToUpdateEntityException(messageSource.getMessage("unable-to-update-slide", null, Locale.US));
        }
    }

    private Slide getSlideById(Long id) {
        Optional<Slide> entity = repository.findById(id);
        if (entity.isEmpty())
            throw new NotFoundException(messageSource.getMessage("slide-not-found", null ,Locale.US));
        return entity.get();
    }

}
