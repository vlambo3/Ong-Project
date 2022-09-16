package com.alkemy.ong.repository;

import com.alkemy.ong.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT name FROM News n WHERE n.name = :name")
    public String findByName (@Param("name") String name);

    Page<News> findAll(Pageable pageable);

}
