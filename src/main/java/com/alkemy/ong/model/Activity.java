package com.alkemy.ong.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
@Getter
@Setter
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor(force = true)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "the name can´t be null")
    private String name;

    @Lob
    @NotNull
    private String content;

    @NotNull
    private String image;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    private boolean deleted = Boolean.FALSE;
}
