package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.model.Slide;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlideMapper {

    public List<SlideResponseDto> slideEntityList2DtoList(List<Slide> entities){
        List<SlideResponseDto> dtoList = new ArrayList<>();
        for (Slide aux: entities) {
            dtoList.add(this.slideEntity2Dto(aux));
        }
        return dtoList;
    }

    public SlideResponseDto slideEntity2Dto(Slide entity){

        SlideResponseDto dto = new SlideResponseDto();

        dto.setImageUrl(entity.getImageUrl());
        dto.setPosition(entity.getPosition());

        return dto;
    }

}
