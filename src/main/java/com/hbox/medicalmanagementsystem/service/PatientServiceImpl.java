package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Clinic;
import com.hbox.medicalmanagementsystem.entity.Doctor;
import com.hbox.medicalmanagementsystem.entity.Patient;
import com.hbox.medicalmanagementsystem.repository.ClinicRepository;
import com.hbox.medicalmanagementsystem.repository.DoctorRepository;
import com.hbox.medicalmanagementsystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final ClinicRepository clinicRepository;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, ClinicRepository clinicRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.clinicRepository = clinicRepository;
    }

    @Override
    public List<Patient> getPatientsByDoctorId(Long doctorId) {
        return patientRepository.findByDoctorId(doctorId);

    }

    @Override
    public List<Patient> getPatientsByClinicId(Long id) {
        return patientRepository.findClinicPatientById(id);
    }

    @Override
    public List<Patient> getPatientsByFirstNameOrLastName(String name) {
        return patientRepository.findByFirstNameOrLastName(name,name);
    }

    @Override
    public Patient getPatientByEmrNumber(String emrNumber) {
       return patientRepository.findPatientsByEmrNumber(emrNumber);
    }

    @Override
    public Patient addPatient(Patient patient) {
//        System.out.println(patient);
//        if (patient.getDoctor() == null) {
//            throw new IllegalArgumentException("The Respective Doctor doesn't exist.");
//        }
//
//        if (patient.getClinic() == null) {
//            throw new IllegalArgumentException("The Respective Clinic doesn't exist.");
//        }

        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        patientRepository.delete(patient);
    }

    @Override
    public Patient updatePatient(Patient newPatient) {
        Patient existingPatient=patientRepository.findById(newPatient.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        existingPatient.setFirstName(newPatient.getFirstName());
        existingPatient.setLastName(newPatient.getLastName());
        existingPatient.setDob(newPatient.getDob());
        existingPatient.setDoctorId(newPatient.getDoctorId());
        existingPatient.setGender(newPatient.getGender());
        existingPatient.setEmrNumber(newPatient.getEmrNumber());
        existingPatient.setId(newPatient.getId());
        return patientRepository.save(existingPatient);
    }


}
