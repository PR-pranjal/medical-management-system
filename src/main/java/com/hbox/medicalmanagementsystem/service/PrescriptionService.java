package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.response.PrescriptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

public interface PrescriptionService {
    public Prescription addPrescription(Prescription prescription);

    public void deletePrescription(Long prescriptionId);

    public Prescription updatePrescription(Prescription prescription);

    public List<PrescriptionResponse> getAllPrescription(String search,String sortBy,String sortOrder,Integer pageNumber,Integer pageSize);
//    public Page<PrescriptionResponse> getAllPrescriptions(
//            String patientName,
//            String doctorName,
//            String clinicName,
//            String prescriptionCause,
//            LocalDateTime createdFrom,
//            LocalDateTime createdTo,
//            String sortField,
//            Sort.Direction sortDirection,
//            int page,
//            int pageSize
//    );
}
