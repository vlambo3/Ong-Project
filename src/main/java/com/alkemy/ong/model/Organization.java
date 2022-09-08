package com.alkemy.ong.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
@SQLDelete(sql = "UPDATE organizations SET deleted = true where id=?")
@Where(clause = "deleted=false")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "field name cannot be null")
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull(message = "field img cannot be null")
    @Column(nullable = false, length = 100)
    private String image;

    @Column(nullable = true, length = 50)
    private String address;

    @Pattern(regexp = "^(\\+?\\d{1,3})?(\\d{10})$", message = "the number phone es invalid")
    @Column(nullable = true, length = 15)
    private String phone;

    @Email(regexp = "^[a-zA-Z]+((\\.|_)*[a-zA-Z0-9]+)*((\\.|_)[a-zA-Z0-9]+)*@[a-z]+\\.\\w\\w\\w(\\.\\w\\w)?$", message = "the email is invalid")
    @NotNull(message = "field email cannot be null")
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = true, name = "welcome_text", length = 150)
    private String welcomeText;

    @Column(nullable = true,name = "about_us", length = 150)
    private String aboutUs;

    @Column(name = "creation_date",nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Boolean deleted = Boolean.FALSE;
}
