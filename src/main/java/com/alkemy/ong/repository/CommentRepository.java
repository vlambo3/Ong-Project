package com.alkemy.ong.repository;

import com.alkemy.ong.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /*@Query("SELECT * FROM Comment c WHERE news_id = :id")
    public List<Comment> findCommentsByNewId(Long newsId);*/

    List<Comment> findByNewsId(Long id);

}
