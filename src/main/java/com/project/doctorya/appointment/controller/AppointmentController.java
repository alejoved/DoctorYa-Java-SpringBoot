package com.project.doctorya.appointment.controller;

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

import com.project.doctorya.appointment.dto.AppointmentDTO;
import com.project.doctorya.appointment.dto.AppointmentResponseDTO;
import com.project.doctorya.appointment.service.IAppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/appointment")
@Tag(name = "Appointments", description = "Appointment-related operations")
public class AppointmentController {
    @Autowired
    private IAppointmentService service;

    @Operation(summary = "Get all appointments currently")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully get all appointments"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAll() {
        List<AppointmentResponseDTO> appointments = service.getAll();
        return ResponseEntity.ok(appointments);
    }

    @Operation(summary = "Get an appointment existing by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an appointment successful"),
        @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@Parameter(description = "uuid for filter appointment") @PathVariable UUID id) {
        AppointmentResponseDTO appointment = service.getById(id);
        return ResponseEntity.ok(appointment);
    }

    @Operation(summary = "Create a new appointment associated with a patient and physician")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Appointment create successfull"),
        @ApiResponse(responseCode = "404", description = "Patient or physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody @Valid AppointmentDTO appointmentDTO) {
        AppointmentResponseDTO appointment = service.create(appointmentDTO);
        return ResponseEntity.ok(appointment);
    }

    @Operation(summary = "Update data about an appointment by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment updated successfull"),
        @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error",content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(@Parameter(description = "uuid for filter appointment") @PathVariable UUID id, @RequestBody @Valid AppointmentDTO appointmentDTO) {
        AppointmentResponseDTO appointment = service.create(appointmentDTO);
        return ResponseEntity.ok(appointment);
    }

    @Operation(summary = "Delete an appointment by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment deleted successfull"),
        @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
