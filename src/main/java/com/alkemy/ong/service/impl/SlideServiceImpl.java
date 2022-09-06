package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.ISlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements ISlideService {

    private final SlideRepository slideRepository;

    private final SlideMapper mapper;

    private final MessageSource messageSource;

    @Override
    public List<SlideResponseDto> getAll() {
        List<Slide> slides =  slideRepository.findAllByOrderByPositionAsc();
        if (slides.isEmpty())
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        return mapper.slideEntityList2DtoList(slides);
    }
}
