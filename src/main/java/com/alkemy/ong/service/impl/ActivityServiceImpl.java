package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.NotFoundException;
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
        Activity activity = repository.findById(id).orElseThrow(
                ()-> new NotFoundException(
                        messageSource.getMessage("is not found", new Object[] { "Category name" }, Locale.US)));
        activity = mapper.map(dto, Activity.class);
        activity.setUpdateDate(LocalDateTime.now());
        activity = repository.save(activity);
        return mapper.map(activity, ActivityResponseDTO.class);
    }



}
