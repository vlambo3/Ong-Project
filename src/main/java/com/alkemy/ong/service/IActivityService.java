package com.alkemy.ong.service;

import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;

public interface IActivityService {
    ActivityResponseDTO create(ActivityRequestDTO dto);
    ActivityResponseDTO update(Long id, ActivityRequestDTO dto);
}
