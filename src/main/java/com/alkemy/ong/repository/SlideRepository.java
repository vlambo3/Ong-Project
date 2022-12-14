package com.alkemy.ong.repository;

import com.alkemy.ong.dto.slide.SlideResponseDto;
import com.alkemy.ong.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

    List<Slide> findAllByOrderByPositionAsc();
    List<SlideResponseDto> findByOrganizationId(Long organizationId);

}
