package com.alkemy.ong.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @NotBlank(message = "Field name cannot be null or empty")
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull(message = "Field img cannot be null")
    @Column(nullable = false, length = 100)
    private String image;

    @Column(nullable = true, length = 50)
    private String address;

    @Pattern(regexp = "^(\\+?\\d{1,3})?(\\d{10})$", message = "The number phone entered is invalid")
    @Column(nullable = true, length = 15)
    private String phone;

    @Email(regexp = "^[a-zA-Z]+((\\.|_)*[a-zA-Z0-9]+)*((\\.|_)[a-zA-Z0-9]+)*@[a-z]+\\.\\w\\w\\w(\\.\\w\\w)?$", 
           message = "the email is invalid")
    @NotBlank(message = "Field email cannot be null or empty")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = true, name = "welcome_text", length = 150)
    private String welcomeText;

    @Column(nullable = true, name = "about_us", length = 150)
    private String aboutUs;

    @Column(name = "creation_date",nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "organization",fetch = FetchType.LAZY)
    private Set<Slide> slides = new HashSet<>();

    @Pattern(regexp = "^\\s*(http\\:\\/\\/)?facebook\\.com\\/[a-z\\d-_]{1,255}\\s*$/i", 
            message = "The url entered does not match the pattern")
    @Column(name = "facebook_url")
    private String facebookUrl;

    @Pattern(regexp = "^\\s*(http\\:\\/\\/)?instagram\\.com\\/[a-z\\d-_]{1,255}\\s*$/i", 
            message = "The url entered does not match the pattern")
    @Column(name = "instagram_url")
    private String instagramUrl;

    @Pattern(regexp = "^\\s*(http\\:\\/\\/)?linkedin\\.com\\/[a-z\\d-_]{1,255}\\s*$/i", 
            message = "The url entered does not match the pattern")
    @Column(name = "linkedin_url")
    private String linkedinUrl;

    private Boolean deleted = Boolean.FALSE;
}
