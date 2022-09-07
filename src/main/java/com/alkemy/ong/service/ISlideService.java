package com.alkemy.ong.service;

import com.alkemy.ong.dto.slide.SlideRequestDto;
import com.alkemy.ong.dto.slide.SlideResponseDto;

public interface ISlideService {
    SlideResponseDto create(SlideRequestDto dto);
}
