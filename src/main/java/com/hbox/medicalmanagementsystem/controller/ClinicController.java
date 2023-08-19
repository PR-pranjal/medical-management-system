package com.hbox.medicalmanagementsystem.controller;

import com.hbox.medicalmanagementsystem.entity.Clinic;
import com.hbox.medicalmanagementsystem.entity.Doctor;
import com.hbox.medicalmanagementsystem.service.ClinicService;
import com.hbox.medicalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clinics")
public class ClinicController {
    private final ClinicService clinicService;
    private final DoctorService doctorService;

    @Autowired
    public ClinicController(ClinicService clinicService, DoctorService doctorService){
        this.clinicService=clinicService;

        this.doctorService = doctorService;
    }
    @GetMapping
    public ResponseEntity<List<Clinic>> getALlClinics(){
        return new ResponseEntity<>(clinicService.getALlClinics(),HttpStatus.OK);
    }
    @GetMapping("/{clinicId}/doctors")
    public ResponseEntity<List<Doctor>> getDoctorsByClinicId(@PathVariable Long clinicId) {
        List<Doctor> doctors = doctorService.getDoctorsByClinicId(clinicId);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }



}
