package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Doctor;

import java.util.List;

public interface DoctorService {
    public List<Doctor> getDoctorsByClinicId(Long clinicId);

}
