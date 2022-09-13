package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.UnableToUpdateEntityException;
import com.alkemy.ong.mapper.GenericMapper;
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

    private final ActivityRepository repository;
    private final GenericMapper mapper;
    private final MessageSource messageSource;

    public ActivityResponseDTO create(ActivityRequestDTO dto) {

        List<Activity> activities = repository.findAll();

        activities.forEach (a -> {
            if (repository.findByName(a.getName()).equalsIgnoreCase(dto.getName())) {
                throw new AlreadyExistsException(
                        messageSource.getMessage("already-exists", new Object[] { "Category name" }, Locale.US));
            }
        });

        Activity activity = mapper.map(dto, Activity.class);

        activity.setCreationDate(LocalDateTime.now());
        activity = repository.save(activity);

        return mapper.map(activity, ActivityResponseDTO.class);
    }

    public ActivityResponseDTO update(Long id, ActivityRequestDTO dto) {
        Activity activity = getActivityById(id);
        try {
            Activity updatedActivity = mapper.map(dto, Activity.class);
            updatedActivity.setCreationDate(activity.getCreationDate());
            updatedActivity.setUpdateDate(LocalDateTime.now());
            repository.save(updatedActivity);
            return mapper.map(updatedActivity, ActivityResponseDTO.class);
        } catch (Exception e) {
            throw new UnableToUpdateEntityException(
                    messageSource.getMessage("unable-to-update-entity", new Object[]{id}, Locale.US));
        }
    }

    private Activity getActivityById(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new NotFoundException(
                messageSource.getMessage("not-found", new Object[] { "Activity" }, Locale.US))
                );
    }

}
