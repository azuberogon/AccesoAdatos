package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Primary;

@Entity
public class Cliente {


    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
