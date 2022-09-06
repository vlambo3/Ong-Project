package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.dto.slide.SlideRequestDTO;
import com.alkemy.ong.dto.slide.SlideResponseDTO;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.service.ISlideService;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements ISlideService {

    private final SlideRepository slideRepository;

    private final OrganizationRepository organizationRepository;
   
    private final SlideMapper slideMapper;

    private final MessageSource messageSource;
      
    @Override
    public List<SlideResponseDto> getAll() {
        List<Slide> slides =  slideRepository.findAllByOrderByPositionAsc();
        if (slides.isEmpty())
            throw new EmptyListException(messageSource.getMessage("empty-list", null, Locale.US));
        return mapper.slideEntityList2DtoList(slides);


    public SlideResponseDTO create(SlideRequestDTO dto) {

        Organization org = organizationRepository.findAll().get(0);

        Slide slide = slideMapper.slideDTO2SlideEntity(dto, org);

        List<Slide> slidesList = slideRepository.findAll();

        int n = 0;

        if (dto.getPosition() == null){
            slide.setPosition(slidesList.size() + 1);
            slidesList.add(slide);
            n++;
        } else if (slidesList.isEmpty() || (slidesList.size() < dto.getPosition())) {
            slide.setPosition(slidesList.size()+1);
            slidesList.add(slide);
        } else if (!slidesList.isEmpty() && (slidesList.size() >= dto.getPosition())) {
            slide.setPosition(dto.getPosition());
            slidesList.add(dto.getPosition(), slide);
        }

        SlideResponseDTO responseDTO = slideMapper.slideEntity2SlideDTO(slideRepository.save(slide));

        if (n == 1)
            responseDTO.setMessage(messageSource.getMessage("slide-position", null, Locale.US));

        return responseDTO;

    }
}
