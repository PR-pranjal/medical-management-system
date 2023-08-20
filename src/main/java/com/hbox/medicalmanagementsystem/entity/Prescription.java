package com.hbox.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prescriptionId;
    @JoinColumn(name = "patient_id")
    private Long patientId;
    @JoinColumn(name = "doctor_id")
    private Long doctorId;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String cause;
    private String notes;
}
