package com.project.doctorya.physician.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doctorya.physician.dto.PhysicianDTO;
import com.project.doctorya.physician.dto.PhysicianResponseDTO;
import com.project.doctorya.physician.service.IPhysicianService;

import jakarta.validation.Valid;

@Validated
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PhysicianResponseDTO> create(@RequestBody @Valid PhysicianDTO patientDTO) {
        PhysicianResponseDTO patient = service.create(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhysicianResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid PhysicianDTO patientDTO) {
        PhysicianResponseDTO patient = service.update(patientDTO, id);
        return ResponseEntity.ok(patient);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
