package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "slides")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "field imageUrl cannot be null")
    @Column(name = "image_url", length = 100,nullable = false)
    private String imageUrl;

    @Column(nullable = true,length = 150)
    private String text;

    @NotNull(message = "field position cannot be null")
    @Column(nullable = false)
    private Integer position;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
