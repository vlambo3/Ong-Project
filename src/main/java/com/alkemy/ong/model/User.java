package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
@Setter
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "FirstName cannot be null.")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "LastName cannot be null.")
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    @NotNull(message = "Email cannot be null.")
    @Email
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Wrong email.")
    private String email;

    @NotNull(message = "Password cannot be null.")
    private String password;

    private String photo;

    @ManyToOne
    @Column(name = "role_id")
    private Role role;

    private boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;





}
