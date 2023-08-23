package com.hbox.medicalmanagementsystem.controller;

import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.response.PrescriptionResponse;
import com.hbox.medicalmanagementsystem.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
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
                                                       @RequestParam(value = "pageSize",defaultValue = "1",required = false) Integer pageSize

    ) {
        if (searchTerm != null) {
            return prescriptionService.getAllPrescription(searchTerm,sortBy,sortOrder,pageNumber,pageSize);
        } else {

            return null;
        }
    }





}
