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
import com.project.doctorya.dtos.PhysicianDTO;
import com.project.doctorya.services.interf.IPhysicianService;

@RestController
@RequestMapping("/physician")
public class PhysicianController {
    @Autowired
    private IPhysicianService service;

    @GetMapping
    public ResponseEntity<List<PhysicianDTO>> getAll() {
        List<PhysicianDTO> patients = service.getAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhysicianDTO> getById(@PathVariable String id) {
        PhysicianDTO patient = service.getById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<PhysicianDTO> create(@RequestBody PhysicianDTO patientDTO) {
        PhysicianDTO patient = service.create(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhysicianDTO> update(@PathVariable String id, @RequestBody PhysicianDTO patientDTO) {
        PhysicianDTO patient = service.create(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
