package com.hbox.medicalmanagementsystem.controller;

import com.hbox.medicalmanagementsystem.dto.PageRequestDto;
import com.hbox.medicalmanagementsystem.dto.RequestDto;
import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.repository.PrescriptionRepository;
import com.hbox.medicalmanagementsystem.response.PrescriptionResponse;
import com.hbox.medicalmanagementsystem.service.PrescriptionService;
import com.hbox.medicalmanagementsystem.service.SpecificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final PrescriptionRepository prescriptionRepository;
    private final SpecificationService<Prescription> specificationService;

    public PrescriptionController(PrescriptionService prescriptionService, PrescriptionRepository prescriptionRepository, SpecificationService specificationService) {
        this.prescriptionService = prescriptionService;
        this.prescriptionRepository = prescriptionRepository;
        this.specificationService = specificationService;
    }

    @PostMapping("/add-prescription")
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription){
        Prescription prescriptionToAdd=prescriptionService.addPrescription(prescription);
        return new ResponseEntity<>(prescriptionToAdd, HttpStatus.OK);
    }
    @DeleteMapping("/delete-prescription")
    public ResponseEntity<String> deletePrescription(@RequestBody Map<String,Long> request){
        Long prescriptionId=request.get("prescriptionId");
        prescriptionService.deletePrescription(prescriptionId);
        return new ResponseEntity<>("Prescription record deleted successfully",HttpStatus.OK);
    }
    @PutMapping("/update-prescription")
    public ResponseEntity<Prescription> updatePrescription(@RequestBody Prescription prescription){
        Prescription prescriptionToUpdate=prescriptionService.updatePrescription(prescription);
        return new ResponseEntity<>(prescriptionToUpdate,HttpStatus.OK);
    }
    @GetMapping("/search")
    public List<PrescriptionResponse> getPrescriptions(@RequestParam(required = false) String searchTerm,
                                                       @RequestParam(required=false) String sortBy,
                                                       @RequestParam(required = false) String sortOrder,
                                                       @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize",defaultValue = "1000",required = false) Integer pageSize,
                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                                                       @RequestParam(required = false) String doctorName,
                                                       @RequestParam(required = false) String clinicName

    ) {

        return prescriptionService.getAllPrescription(searchTerm,sortBy,sortOrder,fromDate,toDate,doctorName,clinicName,pageNumber,pageSize);

    }
    @PostMapping("/pagination")
    public Page<PrescriptionResponse> getPrescriptionsUsingSpecificationAndPagination(@RequestBody RequestDto requestDto) {
        Specification<Prescription> searchSpecification =specificationService
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
        Pageable pageable=new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return specificationService.getPrescriptionsUsingSpecificationAndPagination(searchSpecification,pageable);
    }






}
