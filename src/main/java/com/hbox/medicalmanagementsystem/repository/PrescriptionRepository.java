package com.hbox.medicalmanagementsystem.repository;

import com.hbox.medicalmanagementsystem.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

}
