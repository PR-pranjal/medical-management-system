package com.hbox.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String emrNumber;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
