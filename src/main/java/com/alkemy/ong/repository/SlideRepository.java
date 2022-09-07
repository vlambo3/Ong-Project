package com.alkemy.ong.repository;

import com.alkemy.ong.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

    public List<Slide> findAllByOrderByPositionAsc();
    public Integer findByPosition (@Param("position") Integer position);
}
