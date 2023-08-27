package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.response.PrescriptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PrescriptionService {
    public Prescription addPrescription(Prescription prescription);

    public void deletePrescription(Long prescriptionId);

    public Prescription updatePrescription(Prescription prescription);

    public List<PrescriptionResponse> getAllPrescription(String search, String sortBy, String sortOrder, LocalDateTime fromDate,LocalDateTime toDate,String doctorName,String clinicName,Integer pageNumber, Integer pageSize);


}
