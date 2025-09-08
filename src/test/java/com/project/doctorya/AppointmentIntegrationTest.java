package com.project.doctorya;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doctorya.auth.infrastructure.entities.AuthEntity;
import com.project.doctorya.auth.infrastructure.repositories.IAuthJpaRepository;
import com.project.doctorya.auth.rest.dto.AuthDTO;
import com.project.doctorya.patient.infrastructure.entities.PatientEntity;
import com.project.doctorya.patient.infrastructure.repositories.IPatientJpaRepository;
import com.project.doctorya.physician.infrastructure.entities.PhysicianEntity;
import com.project.doctorya.physician.infrastructure.repositories.IPhysicianJpaRepository;
import com.project.doctorya.appointment.rest.dto.AppointmentDTO;
import com.project.doctorya.appointment.rest.dto.AppointmentResponseDTO;
import com.project.doctorya.appointment.infrastructure.entities.AppointmentEntity;
import com.project.doctorya.appointment.infrastructure.repositories.IAppointmentJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IAppointmentJpaRepository appointmentRepository;

    @Autowired
    private IPatientJpaRepository patientRepository;

    @Autowired
    private IPhysicianJpaRepository physicianRepository;

    @Autowired
    private IAuthJpaRepository authRepository;

    private String accessToken;
    
    private AppointmentEntity appointmentDB;

    @BeforeEach
    void beforeTest() throws Exception {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setIdentification("1053847601");
        authDTO.setPassword("12345");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authDTO)))
                .andExpect(status().isOk())
                .andReturn();

        AuthDTO loginDTO = new AuthDTO();
        loginDTO.setIdentification(authDTO.getIdentification());
        loginDTO.setPassword(authDTO.getPassword());

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(response);
        accessToken = jsonNode.get("token").asText();
        PatientEntity patientDB = new PatientEntity();
        patientDB.setName("Test Name");
        patientDB.setInsurance("Test Insurance");
        patientDB.setAuthEntity(new AuthEntity());
        patientDB.getAuthEntity().setIdentification("1053847610");
        patientDB.getAuthEntity().setPassword("12345");
        patientDB = patientRepository.save(patientDB);

        PhysicianEntity physicianDB = new PhysicianEntity();
        physicianDB.setName("Test Name");
        physicianDB.setCode("Test Code");
        physicianDB.setSpeciality("Test Speciality");
        physicianDB.setAuthEntity(new AuthEntity());
        physicianDB.getAuthEntity().setIdentification("1053847620");
        physicianDB.getAuthEntity().setPassword("12345");
        physicianDB = physicianRepository.save(physicianDB);
        
        appointmentDB = new AppointmentEntity();
        appointmentDB.setStartDate(Timestamp.valueOf("2025-05-01 10:00:00"));
        appointmentDB.setEndDate(Timestamp.valueOf("2025-05-01 10:30:00"));
        appointmentDB.setReason("Test Reason");
        appointmentDB.setPatientEntity(patientDB);
        appointmentDB.setPhysicianEntity(physicianDB);
    }

    @AfterEach
    void afterEach(){
        appointmentRepository.deleteAll();
        patientRepository.deleteAll();
        physicianRepository.deleteAll();
        authRepository.deleteAll();
    }

    @Test
    void testCreateAppointment() throws Exception {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setStartDate(Timestamp.valueOf("2025-05-01 10:00:00"));
        appointmentDTO.setDuration(20);
        appointmentDTO.setReason("Test Reason");
        appointmentDTO.setPatientIdentification("1053847610");
        appointmentDTO.setPhysicianIdentification("1053847620");

        String responseJson = mockMvc.perform(post("/appointment")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(appointmentDTO)))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        AppointmentResponseDTO appointment = objectMapper.readValue(responseJson, AppointmentResponseDTO.class);
        assertNotNull(appointment);
        assertEquals(appointment.getPatient().getIdentification(), appointmentDTO.getPatientIdentification());
        assertEquals(appointment.getPhysician().getIdentification(), appointmentDTO.getPhysicianIdentification());
        assertEquals(appointment.getReason(), appointmentDTO.getReason());
        assertEquals(appointment.getStartDate(), appointmentDTO.getStartDate());
    }

    @Test
    void testPatientNotFoundCreateAppointment() throws Exception {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setStartDate(Timestamp.valueOf("2025-05-01 10:00:00"));
        appointmentDTO.setDuration(20);
        appointmentDTO.setReason("Test Reason");
        appointmentDTO.setPatientIdentification("1053847600");
        appointmentDTO.setPhysicianIdentification("1053847620");

        String responseJson = mockMvc.perform(post("/appointment")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(appointmentDTO)))
                            .andExpect(status().isNotFound())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        String expectedError = "Patient not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }

    @Test
    void testPhysicianNotFoundCreateAppointment() throws Exception {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setStartDate(Timestamp.valueOf("2025-05-01 10:00:00"));
        appointmentDTO.setDuration(20);
        appointmentDTO.setReason("Test Reason");
        appointmentDTO.setPatientIdentification("1053847610");
        appointmentDTO.setPhysicianIdentification("1053847600");

        String responseJson = mockMvc.perform(post("/appointment")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(appointmentDTO)))
                            .andExpect(status().isNotFound())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        String expectedError = "Physician not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }

    @Test
    void testGetAppointment() throws Exception {
        appointmentRepository.save(this.appointmentDB);
        String responseJson = mockMvc.perform(get("/appointment")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        List<AppointmentResponseDTO> appointmentResponseDTO = objectMapper.readValue(responseJson, new TypeReference<List<AppointmentResponseDTO>>(){});
        assertNotNull(appointmentResponseDTO);
        assertFalse(appointmentResponseDTO.isEmpty());
    }

    @Test
    void testGetByIdAppointment() throws Exception {
        AppointmentEntity appointment = appointmentRepository.save(this.appointmentDB);
        String responseJson = mockMvc.perform(get("/appointment/" + appointment.getId())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();
        AppointmentResponseDTO appointmentResponseDTO = objectMapper.readValue(responseJson, AppointmentResponseDTO.class);
        assertNotNull(appointmentResponseDTO);
        assertEquals(appointmentResponseDTO.getPatient().getIdentification(), appointment.getPatientEntity().getAuthEntity().getIdentification());
        assertEquals(appointmentResponseDTO.getPhysician().getIdentification(), appointment.getPhysicianEntity().getAuthEntity().getIdentification());
        assertEquals(appointmentResponseDTO.getReason(), appointment.getReason());
        assertEquals(appointmentResponseDTO.getStartDate(), appointment.getStartDate());
    }

    @Test
    void testGetByIdNotFoundAppointment() throws Exception {
        UUID id = UUID.randomUUID();
        String responseJson = mockMvc.perform(get("/appointment/" + id)
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isNotFound())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();
        String expectedError = "Appointment not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }

    @Test
    void testUpdateAppointment() throws Exception {
        AppointmentEntity appointment = appointmentRepository.save(this.appointmentDB);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatientIdentification(appointment.getPatientEntity().getAuthEntity().getIdentification());
        appointmentDTO.setPhysicianIdentification(appointment.getPhysicianEntity().getAuthEntity().getIdentification());
        appointmentDTO.setDuration(50);
        appointmentDTO.setReason("Test Reason 2");
        appointmentDTO.setStartDate(Timestamp.valueOf("2025-05-01 15:00:00"));
        String responseJson = mockMvc.perform(put("/appointment/"+ appointment.getId())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(appointmentDTO)))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        AppointmentResponseDTO appointmentResponseDTO = objectMapper.readValue(responseJson, AppointmentResponseDTO.class);
        assertNotNull(appointmentResponseDTO);
        assertEquals(appointmentResponseDTO.getPatient().getIdentification(), appointmentDTO.getPatientIdentification());
        assertEquals(appointmentResponseDTO.getPhysician().getIdentification(), appointmentDTO.getPhysicianIdentification());
        assertEquals(appointmentResponseDTO.getReason(), appointmentDTO.getReason());
        assertEquals(appointmentResponseDTO.getStartDate(), appointmentDTO.getStartDate());
    }

    @Test
    void testUpdateNotFoundAppointment() throws Exception {
        AppointmentEntity appointment = appointmentRepository.save(this.appointmentDB);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatientIdentification(appointment.getPatientEntity().getAuthEntity().getIdentification());
        appointmentDTO.setPhysicianIdentification(appointment.getPhysicianEntity().getAuthEntity().getIdentification());
        appointmentDTO.setDuration(50);
        appointmentDTO.setReason("Test Reason 2");
        appointmentDTO.setStartDate(Timestamp.valueOf("2025-05-01 15:00:00"));
        UUID id = UUID.randomUUID(); 
        String responseJson = mockMvc.perform(put("/appointment/"+ id)
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(appointmentDTO)))
                            .andExpect(status().isNotFound())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        String expectedError = "Appointment not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }

    @Test
    void testDeleteAppointment() throws Exception {
        AppointmentEntity appointment = appointmentRepository.save(this.appointmentDB);
        mockMvc.perform(delete("/appointment/"+ appointment.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        assertTrue(appointmentRepository.findById(appointment.getId()).isEmpty());
    }

    @Test
    void testDeleteNotFoundAppointment() throws Exception {
        UUID id = UUID.randomUUID();
        String responseJson = mockMvc.perform(delete("/appointment/"+ id)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        String expectedError = "Appointment not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }

}
