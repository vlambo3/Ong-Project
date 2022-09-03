package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;
import com.alkemy.ong.model.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public Activity activityDto2ActivityEntity (ActivityRequestDTO dto) {
        Activity activity = new Activity();
        activity.setName(dto.getName());
        activity.setContent(dto.getContent());
        //TODO method to convert string to file
        activity.setImage(dto.getImage());
        return activity;
    }

    public ActivityResponseDTO activityEntity2ActivityDTO (Activity activity) {
        ActivityResponseDTO dto = new ActivityResponseDTO();
        dto.setId(activity.getId());
        dto.setName(activity.getName());
        dto.setContent(activity.getContent());
        dto.setCreationDate(activity.getCreationDate());
        dto.setImage(activity.getImage());
        return dto;
    }
}
