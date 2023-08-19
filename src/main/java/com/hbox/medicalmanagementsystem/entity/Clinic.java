package com.hbox.medicalmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;

    public Clinic(Long id,String name, String address, String city, String state) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public Clinic() {

    }


}
