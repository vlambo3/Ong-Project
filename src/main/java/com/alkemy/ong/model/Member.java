package com.alkemy.ong.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Name may not be null or empty")
    @Column(length = 35, nullable = false)
    private String name;

    @Column(name = "facebook_url", length = 100, nullable = true)
    private String facebookUrl;

    @Column(name = "instagram_url", length = 100, nullable = true)
    private String instagramUrl;

    @Column(name = "linkedin_url", length = 100, nullable = true)
    private String linkedinUrl;

    @NotBlank(message = "Image may not be null")
    @Column(length = 100, nullable = false)
    private String image;

    @Column(nullable = true)
    private String description;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(nullable = false)
    private Boolean deleted;
}
