package com.alkemy.ong.repository;

import com.alkemy.ong.model.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {



    List<Comment> findByNewsId(Long id);


    List<Comment> findAllByOrderByNewsCreationDateAsc();

}
