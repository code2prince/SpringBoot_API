package com.example.LearingRestAPI.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String email;

    private boolean isActive;

    @PrePersist
    public void setDefaultValue() {
        this.isActive = true;
    }

}
