package com.alkemy.ong.repository;

import com.alkemy.ong.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    public String findByName (@Param("name") String name);
}
