package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.slide.SlideBasicResponseDto;
import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlideMapper {

    private SlideRepository slideRepository;

    public Slide slideDTO2SlideEntity(SlideRequestDto dto, Long id) {
        Slide slide = new Slide();

        // TODO ad method to convert amazon S3
        slide.setImage(dto.getImageUrl());
        slide.setText(dto.getText());
        slide.setOrganizationId(id);
        return slide;
    }

    public SlideResponseDto slideEntity2SlideDTO(Slide slide) {
        SlideResponseDto dto = new SlideResponseDto();
        dto.setId(slide.getId());
        dto.setImageUrl(slide.getImage());
        dto.setText(slide.getText());
        dto.setPosition(slide.getPosition());
        dto.setOrganizationId(slide.getOrganizationId());
        return dto;
    }

    public List<SlideBasicResponseDto> slideEntityList2DtoBasicList(List<Slide> entities) {
        return entities.stream()
                .map(this::slideEntity2SlideBasicDto)
                .collect(Collectors.toList());
    }


    public SlideBasicResponseDto slideEntity2SlideBasicDto(Slide entity) {
        SlideBasicResponseDto dto = new SlideBasicResponseDto();
        dto.setImageUrl(entity.getImage());
        dto.setPosition(entity.getPosition());
        return dto;
    }

}
