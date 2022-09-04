package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.slide.SlideRequestDTO;
import com.alkemy.ong.dto.slide.SlideResponseDTO;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.ISlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements ISlideService {

    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;

    public SlideResponseDTO create(SlideRequestDTO dto) {

        Slide slide = slideMapper.slideDTO2SlideEntity(dto);

        List<Slide> slidesList = slideRepository.findAll();

        slide.setPosition(slidesList.size()+1);
             slidesList.add(slide);
    /*
        while (slideRepository.findAll().isEmpty()) {
            slide.setPosition(1);
            slidesList.add(slide);
            break;
        }*/

        return slideMapper.slideEntity2SlideDTO(slideRepository.save(slide));
    }
}
