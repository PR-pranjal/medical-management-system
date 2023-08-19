package com.hbox.medicalmanagementsystem.controller;

import com.hbox.medicalmanagementsystem.entity.Patient;
import com.hbox.medicalmanagementsystem.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Patient>> searchPatientByName(@PathVariable String name){
        List<Patient> patients=patientService.getPatientsByFirstNameOrLastName(name);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    @GetMapping("/search/emrNumber/{emrNumber}")
    public ResponseEntity<Patient> searchPatientByEmrNumber(@PathVariable String emrNumber){
        Patient patient=patientService.getPatientByEmrNumber(emrNumber);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }
}
