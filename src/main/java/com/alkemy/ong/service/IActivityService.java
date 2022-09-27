package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;

public interface IActivityService {
    ActivityResponseDTO create(ActivityRequestDTO dto);

    PageDto<ActivityResponseDTO> getPage(int page);
    ActivityResponseDTO update(Long id, ActivityRequestDTO dto);
}
