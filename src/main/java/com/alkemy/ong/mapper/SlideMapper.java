package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.slide.SlideRequestDTO;
import com.alkemy.ong.dto.slide.SlideResponseDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class SlideMapper {

    private SlideRepository slideRepository;

    public Slide slideDTO2SlideEntity (SlideRequestDTO dto) {
        Slide slide = new Slide();

        //TODO ad method to convert amazon S3
        slide.setImageUrl(dto.getImageUrl());
        slide.setText(dto.getText());

        return slide;
    }

    public SlideResponseDTO slideEntity2SlideDTO (Slide slide) {
        SlideResponseDTO dto = new SlideResponseDTO();
        dto.setId(slide.getId());
        dto.setImageUrl(slide.getImageUrl());
        dto.setText(slide.getText());
        dto.setPosition(slide.getPosition());

        if (slide.getPosition() == 1)
            dto.setMessage("The slide was saved in the first position.");
        else dto.setMessage("The slide was saved in the last position, the position " + slide.getPosition());

        //dto.setOrganizationId(slide.getOrganizationId());
        return dto;
    }
}
