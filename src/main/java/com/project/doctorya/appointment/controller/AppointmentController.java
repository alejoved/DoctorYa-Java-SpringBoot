package com.project.doctorya.appointment.controller;

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

import com.project.doctorya.appointment.dto.AppointmentDTO;
import com.project.doctorya.appointment.dto.AppointmentResponseDTO;
import com.project.doctorya.appointment.service.IAppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IAppointmentService service;

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAll() {
        List<AppointmentResponseDTO> appointments = service.getAll();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable UUID id) {
        AppointmentResponseDTO appointment = service.getById(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentResponseDTO appointment = service.create(appointmentDTO);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(@PathVariable UUID id, @RequestBody AppointmentDTO appointmentDTO) {
        AppointmentResponseDTO appointment = service.create(appointmentDTO);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
