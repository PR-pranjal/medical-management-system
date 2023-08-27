package com.hbox.medicalmanagementsystem.repository;

import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.response.PrescriptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> , JpaSpecificationExecutor<Prescription> {

   // Page<PrescriptionResponse> findAll(Specification<PrescriptionResponse> specification, Pageable pageable);
   // Page<Prescription> findAll(Specification<Prescription> specification,Pageable pageable);

    List<Prescription> findByPatientFirstNameContainingOrPatientLastNameContaining(String firstName, String lastName,Pageable pageable);
    List<Prescription> findByDoctorFirstNameContainingOrDoctorLastNameContaining(String firstName, String lastName,Pageable pageable);
    List<Prescription> findByDoctorClinicNameContaining(String clinicName,Pageable pageable);
    List<Prescription> findByCauseContaining(String cause,Pageable pageable);





}
