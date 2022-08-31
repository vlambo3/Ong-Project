package com.alkemy.ong.repository;

import com.alkemy.ong.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    //@Query("SELECT n FROM Activities a WHERE a.n = :name")
    public String findByName(@Param("name") String name);
}
