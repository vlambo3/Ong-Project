package com.alkemy.ong.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "news")
@SQLDelete(sql = "UPDATE news_deleted SET news_deleted = true WHERE news_id=?")
@Where(clause = "deleted=false")
@Data
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

}
