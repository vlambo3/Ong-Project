package com.alkemy.ong.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@SQLDelete(sql = "UPDATE testimonials SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private String image;

    private String content;

    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    private boolean deleted = Boolean.FALSE;

    public Testimonial (String name, String image, String content) {
        this.name = name;
        this.image = image;
        this.content = content;
    }

}
