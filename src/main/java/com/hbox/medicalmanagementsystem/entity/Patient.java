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
    @JoinColumn(name = "doctor_id")
    private Long doctorId;
    @JoinColumn(name = "id")
    private Long  id;



}
