package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class DemoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(nullable = false)
    String name;

    public DemoModel(){

    }

    public DemoModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
