package com.project.doctorya.patient.controller;

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

import com.project.doctorya.patient.dto.PatientDTO;
import com.project.doctorya.patient.dto.PatientResponseDTO;
import com.project.doctorya.patient.service.IPatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService service;

    @Operation(summary = "Get all patients currently")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully get all patients"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAll() {
        List<PatientResponseDTO> patients = service.getAll();
        return ResponseEntity.ok(patients);
    }
    @Operation(summary = "Get an patient existing by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an patient successful"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@Parameter(description = "uuid for filter patient") @PathVariable UUID id) {
        PatientResponseDTO patient = service.getById(id);
        return ResponseEntity.ok(patient);
    }
    @Operation(summary = "Create a new patient associated with a name and an insurance")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patient created successfull"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody @Valid PatientDTO patientDTO) {
        PatientResponseDTO patient = service.create(patientDTO);
        return ResponseEntity.ok(patient);
    }
    @Operation(summary = "Update data about a patient by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patient updated successfull"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@Parameter(description = "uuid for filter patient") @PathVariable UUID id, @RequestBody @Valid PatientDTO patientDTO) {
        PatientResponseDTO patient = service.update(patientDTO, id);
        return ResponseEntity.ok(patient);
    }
    @Operation(summary = "Delete an patient by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patient deleted successfull"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
