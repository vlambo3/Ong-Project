package com.alkemy.ong.model;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

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

    @NotNull(message = "Name cannot be null")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotNull(message = "Content cannot be null")
    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @NotNull(message = "Image cannot be null")
    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created;

    @Column(name = "updated")
    @UpdateTimestamp
    private Date updated;

   // @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    //@JoinColumn(name = "category_id")
    //private Category category;

}
