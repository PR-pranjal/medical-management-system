package com.hbox.medicalmanagementsystem.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Prescription {
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String cause;
    private String notes;
}
