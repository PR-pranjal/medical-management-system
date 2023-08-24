package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Clinic;
import com.hbox.medicalmanagementsystem.entity.Doctor;
import com.hbox.medicalmanagementsystem.entity.Patient;
import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.repository.ClinicRepository;
import com.hbox.medicalmanagementsystem.repository.DoctorRepository;
import com.hbox.medicalmanagementsystem.repository.PatientRepository;
import com.hbox.medicalmanagementsystem.repository.PrescriptionRepository;
import com.hbox.medicalmanagementsystem.response.PrescriptionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, ClinicRepository clinicRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.clinicRepository = clinicRepository;
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
        existingPrescription.setDoctor(newPrescription.getDoctor());
        existingPrescription.setPatient(newPrescription.getPatient());
        existingPrescription.setCreatedDateTime(newPrescription.getCreatedDateTime());
        existingPrescription.setUpdatedDateTime(newPrescription.getUpdatedDateTime());
        return prescriptionRepository.save(existingPrescription);
    }

    @Override
    public List<PrescriptionResponse> getAllPrescription(String searchTerm,String sortBy,String sortOrder,LocalDateTime fromDate,LocalDateTime toDate,String doctorName,String clinicName,Integer pageNumber,Integer pageSize) {


        Pageable pageable=PageRequest.of(pageNumber-1,pageSize);
        List<Prescription> prescriptions = new ArrayList<>();

        if (searchTerm != null) {
            prescriptions.addAll(prescriptionRepository
                    .findByPatientFirstNameContainingOrPatientLastNameContaining(searchTerm, searchTerm, pageable));
            prescriptions.addAll(prescriptionRepository
                    .findByDoctorFirstNameContainingOrDoctorLastNameContaining(searchTerm, searchTerm, pageable));
            prescriptions.addAll(prescriptionRepository
                    .findByDoctorClinicNameContaining(searchTerm, pageable));
            prescriptions.addAll(prescriptionRepository
                    .findByCauseContaining(searchTerm, pageable));
        } else {
            Page<Prescription> prescriptionPage = prescriptionRepository.findAll(pageable);
            prescriptions.addAll(prescriptionPage.getContent());
        }

        if(fromDate != null && toDate != null){
            prescriptions.removeIf(prescription -> prescription.getCreatedDateTime().isBefore(fromDate)
            || prescription.getCreatedDateTime().isAfter(toDate));
        }
        if(doctorName!=null){
            String[] doctorNameParts=doctorName.split("\\s+",2);
            String firstName=doctorNameParts[0].toLowerCase();
            String lastName;
            if(doctorNameParts.length>1){
                lastName=doctorNameParts[1].toLowerCase();
            }
            else{
                lastName="";
            }
            prescriptions.removeIf(prescription ->
                    !prescription.getDoctor().getFirstName().toLowerCase().contains(firstName)||
                    !prescription.getDoctor().getLastName().toLowerCase().contains(lastName));
        }
        if(clinicName!=null){
            prescriptions.removeIf(prescription ->
                    !prescription.getDoctor().getClinic().getName().contains(clinicName));
        }
        if(sortBy!=null && sortOrder!=null){
            if ("patientName".equalsIgnoreCase(sortBy)) {
                prescriptions.sort((p1, p2) -> {
                    String fullName1 = p1.getPatient().getFirstName() + " " + p1.getPatient().getLastName();
                    String fullName2 = p2.getPatient().getFirstName() + " " + p2.getPatient().getLastName();
                    int result = fullName1.compareToIgnoreCase(fullName2);
                    return "desc".equalsIgnoreCase(sortOrder) ? -result : result;
                });
            } else if ("doctorName".equalsIgnoreCase(sortBy)) {
                prescriptions.sort((p1, p2) -> {
                    String fullName1 = p1.getDoctor().getFirstName() + " " + p1.getDoctor().getLastName();
                    String fullName2 = p2.getDoctor().getFirstName() + " " + p2.getDoctor().getLastName();
                    int result = fullName1.compareToIgnoreCase(fullName2);
                    return "desc".equalsIgnoreCase(sortOrder) ? -result : result;
                });
            } else if ("clinicName".equalsIgnoreCase(sortBy)) {
                prescriptions.sort((p1, p2) -> {
                    String clinicName1 = p1.getDoctor().getClinic().getName();
                    String clinicName2 = p2.getDoctor().getClinic().getName();
                    int result = clinicName1.compareToIgnoreCase(clinicName2);
                    return "desc".equalsIgnoreCase(sortOrder) ? -result : result;
                });
            }
        }

        return prescriptions.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    private PrescriptionResponse mapToDto(Prescription prescription) {
        PrescriptionResponse dto = new PrescriptionResponse();
        dto.setPrescriptionId(prescription.getPrescriptionId());
        dto.setPatientFullName(prescription.getPatient().getFirstName() + " " + prescription.getPatient().getLastName());
        dto.setPatientEmrNumber(prescription.getPatient().getEmrNumber());
        dto.setDoctorFullName(prescription.getDoctor().getFirstName() + " " + prescription.getDoctor().getLastName());
        dto.setClinicName(prescription.getDoctor().getClinic().getName());
        dto.setCreatedDateTime(prescription.getCreatedDateTime());
        dto.setNotes(prescription.getNotes());
        dto.setCause(prescription.getCause());
        return dto;
    }

}
