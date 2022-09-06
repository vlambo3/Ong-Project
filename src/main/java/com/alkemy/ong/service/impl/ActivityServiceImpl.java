package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.service.IActivityService;
import com.alkemy.ong.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final MessageSource messageSource;

    public ActivityResponseDTO create(ActivityRequestDTO dto) {

        List<Activity> activities = activityRepository.findAll();

        activities.forEach (a -> {
            if (activityRepository.findByName(a.getName()).equalsIgnoreCase(dto.getName())) {
                throw new AlreadyExistsException(
                        messageSource.getMessage("already-exists", new Object[] { "Category name" }, Locale.US));
            }
        });

        Activity activity = activityMapper.activityDto2ActivityEntity(dto);

        activity.setCreationDate(LocalDateTime.now());

        return activityMapper.activityEntity2ActivityDTO(activityRepository.save(activity));
    }

    public ActivityResponseDTO update(Long id, ActivityRequestDTO dto) {
        Activity activityToUpdate = activityRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(
                        messageSource.getMessage("is not found", new Object[] { "Category name" }, Locale.US)));
        Activity updated = activityMapper.activityUpdated(activityToUpdate,dto);
        updated.setCreationDate(activityToUpdate.getCreationDate());
        updated.setUpdateDate(LocalDateTime.now());
        return activityMapper.activityEntity2ActivityDTO(activityRepository.save(updated));
    }
}
