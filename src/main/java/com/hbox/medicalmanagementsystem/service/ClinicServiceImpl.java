package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.entity.Clinic;
import com.hbox.medicalmanagementsystem.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService{
    private final ClinicRepository clinicRepository;

    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public List<Clinic> getALlClinics() {
        return clinicRepository.findAll();

    }
}
