package com.alkemy.ong.service;

import com.alkemy.ong.dto.slide.SlideRequestDTO;
import com.alkemy.ong.dto.slide.SlideResponseDTO;
import com.alkemy.ong.model.Organization;

public interface ISlideService {
    SlideResponseDTO create(SlideRequestDTO dto);
    List<SlideResponseDto> getAll();

}
