package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.slide.SlideRequestDTO;
import com.alkemy.ong.dto.slide.SlideResponseDTO;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.ISlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements ISlideService {

    private final SlideRepository slideRepository;
    private final OrganizationRepository organizationRepository;
    private final SlideMapper slideMapper;
    private final OrganizationMapper organizationMapper;


    public SlideResponseDTO create(SlideRequestDTO dto) {

        Organization org = organizationRepository.findAll().get(0);

        Slide slide = slideMapper.slideDTO2SlideEntity(dto, org);

        List<Slide> slidesList = slideRepository.findAll();

        slide.setPosition(slidesList.size()+1);
             slidesList.add(slide);

        Slide slideSaved = slideRepository.save(slide);

        SlideResponseDTO responseDTO = slideMapper.slideEntity2SlideDTO(slideSaved);

        if (slideSaved.getPosition() == 1)
            responseDTO.setMessage("The slide was saved in the first position.");
        else responseDTO.setMessage("The slide was saved in the last position, the position " + slide.getPosition());

        return responseDTO;
    }
}
