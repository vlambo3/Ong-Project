package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    private SlideRepository slideRepository;

    public Slide slideDTO2SlideEntity (SlideRequestDto dto, Organization org) {
        Slide slide = new Slide();

        //TODO ad method to convert amazon S3
        slide.setImageUrl(dto.getImageUrl());
        slide.setText(dto.getText());
        slide.setOrganizationId(org.getId());
        return slide;
    }

    public SlideResponseDto slideEntity2SlideDTO (Slide slide) {
        SlideResponseDto dto = new SlideResponseDto();
        dto.setId(slide.getId());
        dto.setImageUrl(slide.getImageUrl());
        dto.setText(slide.getText());
        dto.setPosition(slide.getPosition());
        dto.setOrganizationId(slide.getOrganizationId());
        return dto;
    }


}
