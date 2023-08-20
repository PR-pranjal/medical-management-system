package com.hbox.medicalmanagementsystem.controller;

import com.hbox.medicalmanagementsystem.entity.Patient;
import com.hbox.medicalmanagementsystem.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Patient>> searchPatientByName(@PathVariable String name){
        List<Patient> patients=patientService.getPatientsByFirstNameOrLastName(name);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    @GetMapping("/search/emrNumber/{emrNumber}")
    public ResponseEntity<Patient> searchPatientByEmrNumber(@PathVariable String emrNumber){
        Patient patient=patientService.getPatientByEmrNumber(emrNumber);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    @PostMapping("/add-patient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient patientToAdd=patientService.addPatient(patient);
        return new ResponseEntity<>(patientToAdd,HttpStatus.OK);
    }
    @DeleteMapping("/delete-patient")
    public ResponseEntity<String> deletePatient(@RequestBody Map<String, Long> request){
        Long patientId = request.get("patientId");
        patientService.deletePatient(patientId);
        return new ResponseEntity<>("Patient record deleted successfully",HttpStatus.OK);
    }
    @PutMapping("/update-patient")
    public ResponseEntity<Patient> updatePatient(
            @RequestBody Patient updatedPatient) {
        Patient patient = patientService.updatePatient(updatedPatient);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }


}
