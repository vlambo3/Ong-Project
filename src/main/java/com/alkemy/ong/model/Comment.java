package com.alkemy.ong.model;

import com.alkemy.ong.security.model.User;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.alkemy.ong.security.model.User;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @NotNull(message = "Body can't be null.")
    private String body;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private News news;


}
