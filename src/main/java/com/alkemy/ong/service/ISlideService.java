package com.alkemy.ong.service;

import com.alkemy.ong.dto.slide.SlideRequestDTO;
import com.alkemy.ong.dto.slide.SlideResponseDTO;

public interface ISlideService {
    SlideResponseDTO create(SlideRequestDTO dto);
}
