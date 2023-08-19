package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Patient;
import com.hbox.medicalmanagementsystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getPatientsByDoctorId(Long doctorId) {
        return patientRepository.findByDoctorDoctorId(doctorId);

    }

    @Override
    public List<Patient> getPatientsByClinicId(Long id) {
        return patientRepository.findPatientsByClinicId(id);
    }

    @Override
    public List<Patient> getPatientsByFirstNameOrLastName(String name) {
        return patientRepository.findByFirstNameOrLastName(name,name);
    }

    @Override
    public Patient getPatientByEmrNumber(String emrNumber) {
       return patientRepository.findPatientsByEmrNumber(emrNumber);
    }
}
