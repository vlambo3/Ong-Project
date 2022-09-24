package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field name cannot be null")
    private String name;

    @Pattern(regexp = "^(\\+?\\d{1,3})?(\\d{10})$", message = "The number phone es invalid")
    @NotBlank(message = "Phone cannot be null")
    private String phone;

    @Email(regexp = "^[a-zA-Z]+((\\.|_)*[a-zA-Z0-9]+)*((\\.|_)[a-zA-Z0-9]+)*@[a-z]+\\.\\w\\w\\w(\\.\\w\\w)?$",
            message = "The email is invalid")
    @NotBlank(message = "Field email cannot be null")
    private String email;

    private String message;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    private boolean deleted = Boolean.FALSE;

}
