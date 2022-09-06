package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.slide.SlideRequestDTO;
import com.alkemy.ong.dto.slide.SlideResponseDTO;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    private SlideRepository slideRepository;

    public Slide slideDTO2SlideEntity (SlideRequestDTO dto, Organization org) {
        Slide slide = new Slide();

        //TODO ad method to convert amazon S3
        slide.setImageUrl(dto.getImageUrl());
        slide.setText(dto.getText());
        slide.setOrganizationId(org.getId());
        return slide;
    }

    public SlideResponseDTO slideEntity2SlideDTO (Slide slide) {
        SlideResponseDTO dto = new SlideResponseDTO();
        dto.setId(slide.getId());
        dto.setImageUrl(slide.getImageUrl());
        dto.setText(slide.getText());
        dto.setPosition(slide.getPosition());
        dto.setOrganizationId(slide.getOrganizationId());
        return dto;
    }


}
