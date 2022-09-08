package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.slide.SlideBasicResponseDto;
import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.service.ISlideService;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements ISlideService {

    private final SlideRepository slideRepository;
    private final OrganizationRepository organizationRepository;
    private final SlideMapper mapper;
    private final GenericMapper mapper2; //TODO: 
    private final MessageSource messageSource;

    @Override
    public List<SlideBasicResponseDto> getAll() {
        List<Slide> slides = slideRepository.findAllByOrderByPositionAsc();
        if (slides.isEmpty())
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        return mapper.slideEntityList2DtoBasicList(slides);
    }

    @Override
    public SlideResponseDto getById(Long id) {
        if (id < 1) {
            throw new BadRequestException(messageSource.getMessage("invalid-id", new Object[] { id }, Locale.US));
        }
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        messageSource.getMessage("not-found", new Object[] { "Slide" }, Locale.US)));

        return mapper2.map(slide, SlideResponseDto.class);
    }

    public SlideResponseDto create(SlideRequestDto dto) {

        Organization org = organizationRepository.findAll().get(0);

        Slide slide = mapper.slideDTO2SlideEntity(dto, org.getId());

        List<Slide> slidesList = slideRepository.findAll();

        int n = 0;

        if (dto.getPosition() == null) {
            slide.setPosition(slidesList.size() + 1);
            slidesList.add(slide);
            n++;
        } else if (slidesList.isEmpty() || (slidesList.size() < dto.getPosition())) {
            slide.setPosition(slidesList.size() + 1);
            slidesList.add(slide);
        } else if (!slidesList.isEmpty() && (slidesList.size() >= dto.getPosition())) {
            slide.setPosition(dto.getPosition());
            slidesList.add(dto.getPosition(), slide);
        }

        SlideResponseDto responseDTO = mapper.slideEntity2SlideDTO(slideRepository.save(slide));

        if (n == 1)
            responseDTO.setMessage(messageSource.getMessage("slide-position", null, Locale.US));

        return responseDTO;

    }

}
