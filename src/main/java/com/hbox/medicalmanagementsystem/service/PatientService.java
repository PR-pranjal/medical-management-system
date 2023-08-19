package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Doctor;
import com.hbox.medicalmanagementsystem.entity.Patient;

import java.util.List;

public interface PatientService {
    public List<Patient> getPatientsByDoctorId(Long doctorId);
    public List<Patient> getPatientsByClinicId(Long id);

    public List<Patient> getPatientsByFirstNameOrLastName(String name);

    public Patient getPatientByEmrNumber(String emrNumber);
}
