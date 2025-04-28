package com.project.doctorya.controllers;

import java.util.List;
import java.util.UUID;

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

import com.project.doctorya.dtos.physician.PhysicianDTO;
import com.project.doctorya.dtos.physician.PhysicianResponseDTO;
import com.project.doctorya.services.interf.IPhysicianService;

@RestController
@RequestMapping("/physician")
public class PhysicianController {
    @Autowired
    private IPhysicianService service;

    @GetMapping
    public ResponseEntity<List<PhysicianResponseDTO>> getAll() {
        List<PhysicianResponseDTO> patients = service.getAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhysicianResponseDTO> getById(@PathVariable UUID id) {
        PhysicianResponseDTO patient = service.getById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<PhysicianResponseDTO> create(@RequestBody PhysicianDTO patientDTO) {
        PhysicianResponseDTO patient = service.create(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhysicianResponseDTO> update(@PathVariable UUID id, @RequestBody PhysicianDTO patientDTO) {
        PhysicianResponseDTO patient = service.create(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
