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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Physicians", description = "Physician related operations")
@Validated
@RestController
@RequestMapping("/physician")
public class PhysicianController {
    @Autowired
    private IPhysicianService service;

    @Operation(summary = "Get all physicians currently")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all the physicians successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PhysicianResponseDTO>> getAll() {
        List<PhysicianResponseDTO> physicians = service.getAll();
        return ResponseEntity.ok(physicians);
    }

    @Operation(summary = "Get an physician existing by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an physician successfully"),
        @ApiResponse(responseCode = "404", description = "Physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PhysicianResponseDTO> getById(@Parameter(description = "uuid for filter physician") @PathVariable UUID id) {
        PhysicianResponseDTO physician = service.getById(id);
        return ResponseEntity.ok(physician);
    }

    @Operation(summary = "Get an physician existing by identification")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an physician successfully"),
        @ApiResponse(responseCode = "404", description = "Physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/identification/{identification}")
    public ResponseEntity<PhysicianResponseDTO> getByIdentification(@Parameter(description = "Identification for filter physician") @PathVariable String identification) {
        PhysicianResponseDTO physician = service.getByIdentification(identification);
        return ResponseEntity.ok(physician);
    }

    @Operation(summary = "Create a new physician associated with a name and medical code")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Physician created successfully"),
        @ApiResponse(responseCode = "404", description = "Physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PhysicianResponseDTO> create(@RequestBody @Valid PhysicianDTO physicianDTO) {
        PhysicianResponseDTO physician = service.create(physicianDTO);
        return ResponseEntity.ok(physician);
    }
    @Operation(summary = "Update data about a physician by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Physician updated successfully"),
        @ApiResponse(responseCode = "404", description = "Physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PhysicianResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid PhysicianDTO physicianDTO) {
        PhysicianResponseDTO physician = service.update(physicianDTO, id);
        return ResponseEntity.ok(physician);
    }
    @Operation(summary = "Update data about a physician by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Physician updated successfully"),
        @ApiResponse(responseCode = "404", description = "Physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
