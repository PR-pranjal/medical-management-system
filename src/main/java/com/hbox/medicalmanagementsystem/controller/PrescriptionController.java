package com.hbox.medicalmanagementsystem.controller;

import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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




}
