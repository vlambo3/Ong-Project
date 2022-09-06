package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.model.Slide;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlideMapper {

 public List<SlideResponseDto> slideEntityList2DtoList(List<Slide> entities){
     return entities.stream()
             .map(this::slideEntity2Dto)
             .collect(Collectors.toList());
 }

    public SlideResponseDto slideEntity2Dto(Slide entity){

        SlideResponseDto dto = new SlideResponseDto();

        dto.setImageUrl(entity.getImageUrl());
        dto.setPosition(entity.getPosition());

        return dto;
    }

}
