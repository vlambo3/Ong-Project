package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "slides")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "field imageUrl cannot be null")
    @Column(name = "image", length = 100,nullable = false)
    private String image;

    @Column(nullable = true,length = 150)
    private String text;

    @NotNull(message = "field position cannot be null")
    @Column(nullable = false)
    private Integer position;

    @ManyToOne(fetch = FetchType.EAGER, cascade =  CascadeType.PERSIST)
    @JoinColumn(name = "organization_Id", insertable=false, updatable = false)
    private Organization organization;

    @Column(name = "organization_Id")
    private Long organizationId;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
