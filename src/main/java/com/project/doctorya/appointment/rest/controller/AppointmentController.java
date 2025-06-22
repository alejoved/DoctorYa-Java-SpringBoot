package com.project.doctorya.appointment.rest.controller;

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

import com.project.doctorya.appointment.application.port.IAppointmentCreateUseCase;
import com.project.doctorya.appointment.application.port.IAppointmentDeleteUseCase;
import com.project.doctorya.appointment.application.port.IAppointmentGetUseCase;
import com.project.doctorya.appointment.application.port.IAppointmentUpdateUseCase;
import com.project.doctorya.appointment.domain.model.AppointmentModel;
import com.project.doctorya.appointment.rest.dto.AppointmentDTO;
import com.project.doctorya.appointment.rest.dto.AppointmentResponseDTO;
import com.project.doctorya.appointment.rest.mapper.AppointmentRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Appointments", description = "Appointment related operations")
@Validated
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private IAppointmentGetUseCase appointmentGetUseCase;
    @Autowired
    private IAppointmentCreateUseCase appointmentCreateUseCase;
    @Autowired
    private IAppointmentUpdateUseCase appointmentUpdateUseCase;
    @Autowired
    private IAppointmentDeleteUseCase aAppointmentDeleteUseCase;


    @Operation(summary = "Get all appointments currently")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all appointments successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAll() {
        List<AppointmentModel> appointmentModels = appointmentGetUseCase.execute();
        List<AppointmentResponseDTO> appointments = appointmentModels.stream().map(AppointmentRestMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(appointments);
    }

    @Operation(summary = "Get an appointment existing by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get an appointment successfully"),
        @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@Parameter(description = "uuid for filter appointment") @PathVariable UUID id) {
        AppointmentModel appointmentModel = appointmentGetUseCase.executeById(id);
        AppointmentResponseDTO appointment = AppointmentRestMapper.toDTO(appointmentModel);
        return ResponseEntity.ok(appointment);
    }

    @Operation(summary = "Create a new appointment associated with a patient and physician")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Appointment created successfully"),
        @ApiResponse(responseCode = "404", description = "Patient or physician not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody @Valid AppointmentDTO appointmentDTO) {
        AppointmentModel appointmentModel = AppointmentRestMapper.toDomain(appointmentDTO);
        AppointmentModel response = appointmentCreateUseCase.execute(appointmentModel);
        AppointmentResponseDTO appointment = AppointmentRestMapper.toDTO(response);
        return ResponseEntity.ok(appointment);
    }

    @Operation(summary = "Update data about an appointment by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment updated successfully"),
        @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error",content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(@Parameter(description = "uuid for filter appointment") @PathVariable UUID id, @RequestBody @Valid AppointmentDTO appointmentDTO) {
        AppointmentModel appointmentModel = AppointmentRestMapper.toDomain(appointmentDTO);
        AppointmentModel response = appointmentUpdateUseCase.execute(appointmentModel, id);
        AppointmentResponseDTO appointment = AppointmentRestMapper.toDTO(response);
        return ResponseEntity.ok(appointment);
    }

    @Operation(summary = "Delete an appointment by uuid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Appointment deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        aAppointmentDeleteUseCase.execute(id);
    }
}
