package com.hbox.medicalmanagementsystem.repository;

import com.hbox.medicalmanagementsystem.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

   // Page<Prescription> findAll(Specification<Prescription> specification, Pageable pageable);

    List<Prescription> findByPatientFirstNameContainingOrPatientLastNameContaining(String firstName, String lastName,Pageable pageable);
    List<Prescription> findByDoctorFirstNameContainingOrDoctorLastNameContaining(String firstName, String lastName,Pageable pageable);
    List<Prescription> findByDoctorClinicNameContaining(String clinicName,Pageable pageable);
    List<Prescription> findByCauseContaining(String cause,Pageable pageable);





}
