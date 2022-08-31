package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.IActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    public ActivityResponseDTO create(ActivityRequestDTO dto) {

        List<Activity> activities = activityRepository.findAll();

        for(Activity a : activities) {
            if(dto.getName().equalsIgnoreCase(a.getName())) {
                //TODO add exception
                }
        }

        Activity activity = activityMapper.activityDto2ActivityEntity(dto);

        activity.setCreationDate(LocalDateTime.now());

        return activityMapper.activityEntity2ActivityDTO(activityRepository.save(activity));
    }


}
