package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.repository.PrescriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Prescription addPrescription(Prescription prescription) {
       return prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(Long prescriptionId) {
        Prescription prescription=prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found"));
        prescriptionRepository.delete(prescription);
    }


    @Override
    public Prescription updatePrescription(Prescription newPrescription) {
        Prescription existingPrescription=prescriptionRepository.findById(newPrescription.getPrescriptionId())
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found"));
        existingPrescription.setPrescriptionId(newPrescription.getPrescriptionId());
        existingPrescription.setNotes(newPrescription.getNotes());
        existingPrescription.setCause(newPrescription.getCause());
        existingPrescription.setDoctorId(newPrescription.getDoctorId());
        existingPrescription.setPatientId(newPrescription.getPatientId());
        existingPrescription.setCreatedDateTime(newPrescription.getCreatedDateTime());
        existingPrescription.setUpdatedDateTime(newPrescription.getUpdatedDateTime());
        return prescriptionRepository.save(existingPrescription);
    }
}
