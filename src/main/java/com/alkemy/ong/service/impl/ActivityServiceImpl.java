package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.activity.ActivityRequestDTO;
import com.alkemy.ong.dto.activity.ActivityResponseDTO;
import com.alkemy.ong.dto.news.NewsResponseDto;
import com.alkemy.ong.exception.*;
import com.alkemy.ong.mapper.GenericMapper;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.model.News;
import com.alkemy.ong.service.IActivityService;
import com.alkemy.ong.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                        messageSource.getMessage("activity-name-already-exists", null, Locale.US));
            }
        });

        Activity activity = mapper.map(dto, Activity.class);

        activity.setCreationDate(LocalDateTime.now());
        activity = repository.save(activity);

        return mapper.map(activity, ActivityResponseDTO.class);
    }

    public PageDto<ActivityResponseDTO> getPage(int pageNum) {
        if (pageNum < 1)
            throw new BadRequestException(messageSource.getMessage("negative-page-number", null, Locale.US));
        Pageable pageable = PageRequest.of(pageNum - 1, 10);
        Page<Activity> page = repository.findAll(pageable);
        if (page.getTotalPages() == 0)
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        if (page.isEmpty())
            throw new NotFoundException(messageSource.getMessage("last-page-is", new Object[]{ page.getTotalPages() }, Locale.US));
        return mapper.mapPage(page, ActivityResponseDTO.class, "activities");
    } 

    public ActivityResponseDTO update(Long id, ActivityRequestDTO dto) {
        Activity activity = getActivityById(id);
        try {
            Activity updatedActivity = mapper.map(dto, Activity.class);
            updatedActivity.setId(activity.getId());
            updatedActivity.setCreationDate(activity.getCreationDate());
            updatedActivity.setUpdateDate(LocalDateTime.now());
            repository.save(updatedActivity);
            return mapper.map(updatedActivity, ActivityResponseDTO.class);
        } catch (Exception e) {
            throw new UnableToUpdateEntityException(
                    messageSource.getMessage("unable-to-update-activity", new Object[] {id}, Locale.US));
        }
    }

    private Activity getActivityById(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new NotFoundException(
                messageSource.getMessage("activity-not-found", new Object[] {id}, Locale.US))
                );
    }

}
