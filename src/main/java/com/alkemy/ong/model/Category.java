package com.alkemy.ong.model;



import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;



@Entity
@Table(name = "categories")
@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotNull(message = "Name cannot be empty")
    private String name;

    private String description;

    private String image;


    @CreationTimestamp
    private Timestamp creationTimestamp;

    @UpdateTimestamp
    private Timestamp UpdateTimeStamp;



    private boolean deleted = Boolean.FALSE;

<<<<<<< HEAD
}

=======
}
>>>>>>> develop
