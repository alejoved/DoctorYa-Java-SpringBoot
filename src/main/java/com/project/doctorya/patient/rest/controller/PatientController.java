package com.project.doctorya.patient.rest.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import com.project.doctorya.patient.application.interfaces.IPatientCreateUseCase;
import com.project.doctorya.patient.application.interfaces.IPatientDeleteUseCase;
import com.project.doctorya.patient.application.interfaces.IPatientGetUseCase;
import com.project.doctorya.patient.application.interfaces.IPatientUpdateUseCase;
import com.project.doctorya.patient.domain.models.PatientModel;
import com.project.doctorya.patient.rest.dto.PatientDTO;
import com.project.doctorya.patient.rest.dto.PatientResponseDTO;
import com.project.doctorya.patient.rest.mapper.PatientRestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Patients", description = "Patient related operations")
@Validated
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private IPatientGetUseCase patientGetUsecase;
    @Autowired
    private IPatientCreateUseCase patientCreateUseCase;
    @Autowired
    private IPatientUpdateUseCase patientUpdateUseCase;
    @Autowired
    private IPatientDeleteUseCase patientDeleteUseCase;

    @Operation(summary = "Get all patients currently")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all patients successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAll() {
        List<PatientModel> patients = patientGetUsecase.execute();
        List<PatientResponseDTO> patientResponseDTO = patients.stream().map(PatientRestMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(patientResponseDTO);
    }

    @Operation(summary = "Get an patient existing by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an patient successfully"),
        @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@Parameter(description = "uuid for filter patient") @PathVariable UUID id) {
        PatientModel patientModel = patientGetUsecase.executeById(id);
        PatientResponseDTO patientResponseDTO = PatientRestMapper.toDTO(patientModel);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @Operation(summary = "Get an patient existing by identification")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an patient successfully"),
        @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/identification/{identification}")
    public ResponseEntity<PatientResponseDTO> getByIdentification(@Parameter(description = "Identification for filter patient") @PathVariable String identification) {
        PatientModel patientModel = patientGetUsecase.executeByIdentification(identification);
        PatientResponseDTO patientResponseDTO = PatientRestMapper.toDTO(patientModel);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @Operation(summary = "Create a new patient associated with a identification, name and an insurance")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patient created successfully"),
        @ApiResponse(responseCode = "409", description = "Patient already exists", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody @Valid PatientDTO patientDTO) {
        PatientModel patientModel = PatientRestMapper.toDomain(patientDTO);
        PatientModel response = patientCreateUseCase.execute(patientModel);
        PatientResponseDTO patientResponseDTO = PatientRestMapper.toDTO(response);
        return ResponseEntity.ok(patientResponseDTO);
    }
    @Operation(summary = "Update data about a patient by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patient updated successfully"),
        @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@Parameter(description = "uuid for filter patient") @PathVariable UUID id, @RequestBody @Valid PatientDTO patientDTO) {
        PatientModel patientModel = PatientRestMapper.toDomain(patientDTO);
        PatientModel response = patientUpdateUseCase.execute(patientModel, id);
        PatientResponseDTO patientResponseDTO = PatientRestMapper.toDTO(response);
        return ResponseEntity.ok(patientResponseDTO);
    }
    @Operation(summary = "Delete an patient by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patient deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        patientDeleteUseCase.execute(id);
    }

}
