package com.hbox.medicalmanagementsystem.controller;

import com.hbox.medicalmanagementsystem.entity.Patient;
import com.hbox.medicalmanagementsystem.service.DoctorService;
import com.hbox.medicalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final PatientService patientService;

    @Autowired
    public DoctorController(PatientService patientService) {

        this.patientService = patientService;
    }

    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<Patient>> getPatientsByDoctorId(@PathVariable Long doctorId) {
        List<Patient> patients = patientService.getPatientsByDoctorId(doctorId);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

}
