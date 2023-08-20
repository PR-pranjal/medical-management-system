package com.hbox.medicalmanagementsystem.repository;

import com.hbox.medicalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByDoctorId(Long doctorId);

    List<Patient> findClinicPatientByDoctorId(Long doctorId);
    List<Patient> findByFirstNameOrLastName(String firstName,String lastName);
    Patient findPatientsByEmrNumber(String emrNumber);


}
