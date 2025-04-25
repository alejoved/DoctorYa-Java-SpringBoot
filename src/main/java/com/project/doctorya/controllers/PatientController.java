package com.project.doctorya.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doctorya.dtos.PatientDTO;
import com.project.doctorya.services.interf.IPatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService service;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll() {
        List<PatientDTO> patients = service.getAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable String id) {
        PatientDTO patient = service.getById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patientDTO) {
        PatientDTO patient = service.create(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable String id, @RequestBody PatientDTO patientDTO) {
        PatientDTO patient = service.create(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
