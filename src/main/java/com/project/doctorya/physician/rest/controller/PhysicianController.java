package com.project.doctorya.physician.rest.controller;

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

import com.project.doctorya.physician.application.interfaces.IPhysicianCreateUseCase;
import com.project.doctorya.physician.application.interfaces.IPhysicianDeleteUseCase;
import com.project.doctorya.physician.application.interfaces.IPhysicianGetUseCase;
import com.project.doctorya.physician.application.interfaces.IPhysicianUpdateUseCase;
import com.project.doctorya.physician.domain.models.Physician;
import com.project.doctorya.physician.rest.dto.PhysicianDTO;
import com.project.doctorya.physician.rest.dto.PhysicianResponseDTO;
import com.project.doctorya.physician.rest.mapper.PhysicianRestMapper;
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
    private IPhysicianCreateUseCase physicianCreateUseCase;
    @Autowired
    private IPhysicianGetUseCase physicianGetUseCase;
    @Autowired
    private IPhysicianUpdateUseCase physicianUpdateUseCase;
    @Autowired
    private IPhysicianDeleteUseCase physicianDeleteUseCase;

    @Operation(summary = "Get all physicians currently")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all the physicians successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PhysicianResponseDTO>> getAll() {
        List<Physician> physicians = physicianGetUseCase.execute();
        List<PhysicianResponseDTO> physicianResponseDTOs = physicians.stream().map(PhysicianRestMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(physicianResponseDTOs);
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
        Physician physicianModel = physicianGetUseCase.executeById(id);
        PhysicianResponseDTO physicianResponseDTO = PhysicianRestMapper.toDTO(physicianModel);
        return ResponseEntity.ok(physicianResponseDTO);
    }

    @Operation(summary = "Get an physician existing by identification")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an physician successfully"),
        @ApiResponse(responseCode = "404", description = "Physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/identification/{identification}")
    public ResponseEntity<PhysicianResponseDTO> getByIdentification(@Parameter(description = "Identification for filter physician") @PathVariable String identification) {
        Physician physicianModel = physicianGetUseCase.executeByIdentification(identification);
        PhysicianResponseDTO physicianResponseDTO = PhysicianRestMapper.toDTO(physicianModel);
        return ResponseEntity.ok(physicianResponseDTO);
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
        Physician physicianModel = PhysicianRestMapper.toDomain(physicianDTO);
        Physician response = physicianCreateUseCase.execute(physicianModel);
        PhysicianResponseDTO physicianResponseDTO = PhysicianRestMapper.toDTO(response);
        return ResponseEntity.ok(physicianResponseDTO);
    }
    @Operation(summary = "Update data about a physician by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Physician updated successfully"),
        @ApiResponse(responseCode = "404", description = "Physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PhysicianResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid PhysicianDTO physicianDTO) {
        Physician physicianModel = PhysicianRestMapper.toDomain(physicianDTO);
        Physician response = physicianUpdateUseCase.execute(physicianModel, id);
        PhysicianResponseDTO physicianResponseDTO = PhysicianRestMapper.toDTO(response);
        return ResponseEntity.ok(physicianResponseDTO);
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
        physicianDeleteUseCase.execute(id);
    }
}
