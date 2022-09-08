package com.alkemy.ong.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@SQLDelete(sql = "UPDATE deleted SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Data
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name cannot be null")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotBlank(message = "Content cannot be null")
    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @NotBlank(message = "Image cannot be null")
    @Column(name = "image", nullable = false)
    private String image;

    private boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade =  CascadeType.PERSIST)
    @JoinColumn(name = "category_id", insertable=false, updatable = false)
    private Category category;

    @Column(name = "category_id")
    private Long categoryId;

}
