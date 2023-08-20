package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Prescription;

public interface PrescriptionService {
    public Prescription addPrescription(Prescription prescription);

    public void deletePrescription(Long prescriptionId);

    public Prescription updatePrescription(Prescription prescription);
}
