package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Doctor;
import com.hbox.medicalmanagementsystem.entity.Patient;
import com.hbox.medicalmanagementsystem.repository.ClinicRepository;
import com.hbox.medicalmanagementsystem.repository.DoctorRepository;
import com.hbox.medicalmanagementsystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;

    private final DoctorService doctorService;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorService doctorService) {
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
    }

    @Override
    public List<Patient> getPatientsByDoctorId(Long doctorId) {
        return patientRepository.findByDoctorId(doctorId);

    }

    @Override
    public List<Patient> getPatientsByClinicId(Long id) {
        List<Doctor> doctorsInClinic = doctorService.getDoctorsByClinicId(id);
        List<Patient> patientsInClinic = new ArrayList<>();

        for (Doctor doctor : doctorsInClinic) {
            List<Patient> patientsForDoctor = patientRepository.findByDoctorId(doctor.getDoctorId());
            patientsInClinic.addAll(patientsForDoctor);
        }

        return patientsInClinic;
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
    public List<Patient> getOldAgePatient() {
        LocalDate today=LocalDate.now();
        LocalDate comparable=today.minusYears(60);
        List<Patient> patientList=patientRepository.findAll();
        List<Patient> oldAgePatientList=new ArrayList<>();
        for(Patient patient:patientList){
            LocalDate dateOfBirth=LocalDate.parse(patient.getDob());
            if(dateOfBirth.isBefore(comparable)){
                oldAgePatientList.add(patient);
            }
        }
        return oldAgePatientList;
    }

    @Override
    public Patient addPatient(Patient patient) {
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
        return patientRepository.save(existingPatient);
    }


}
