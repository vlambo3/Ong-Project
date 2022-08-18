package com.alkemy.ong.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET member_deleted = TRUE WHERE member_id = ?")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name", length = 35, nullable = false)
    private String name;

    @Column(name = "member_facebook_url", length = 100, nullable = true)
    private String facebookUrl;

    @Column(name = "member_instagram_url", length = 100, nullable = true)
    private String instagramUrl;

    @Column(name = "member_linkedin_url", length = 100, nullable = true)
    private String linkedinUrl;

    @Column(name = "member_image", length = 100, nullable = false)
    private String image;

    @Column(name = "member_description", nullable = true)
    private String description;

    @Column(name = "member_creation_date", nullable = false)
    private Timestamp creationDate;
    
    @Column(name = "member_deleted", nullable = false)
    private Boolean deleted;
}
