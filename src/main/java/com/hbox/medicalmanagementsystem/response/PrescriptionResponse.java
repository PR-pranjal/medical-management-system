package com.hbox.medicalmanagementsystem.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PrescriptionResponse {
    private Long prescriptionId;
    private String patientFullName;
    private String patientEmrNumber;
    private String doctorFullName;
    private String clinicName;
    private LocalDateTime createdDateTime;
    private String cause;
    private String notes;

}
