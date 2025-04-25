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

import com.project.doctorya.dtos.AppointmentDTO;
import com.project.doctorya.services.interf.IAppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IAppointmentService service;

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAll() {
        List<AppointmentDTO> patients = service.getAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getById(@PathVariable String id) {
        AppointmentDTO appointment = service.getById(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO patientDTO) {
        AppointmentDTO appointment = service.create(patientDTO);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(@PathVariable String id, @RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO appointment = service.create(appointmentDTO);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
