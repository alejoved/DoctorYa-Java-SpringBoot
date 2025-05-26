package com.project.doctorya.PatientTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
import com.project.doctorya.auth.dto.LoginDTO;
import com.project.doctorya.auth.dto.RegisterDTO;
import com.project.doctorya.auth.entity.Auth;
import com.project.doctorya.auth.repository.AuthRepository;
import com.project.doctorya.patient.dto.PatientDTO;
import com.project.doctorya.patient.dto.PatientResponseDTO;
import com.project.doctorya.patient.entity.Patient;
import com.project.doctorya.physician.dto.PhysicianDTO;
import com.project.doctorya.physician.dto.PhysicianResponseDTO;
import com.project.doctorya.physician.entity.Physician;
import com.project.doctorya.physician.repository.PhysicianRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PhysicianIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private AuthRepository authRepository;

    private String accessToken;
    
    private Physician physicianDB;

    @BeforeEach
    void beforeTest() throws Exception {
        physicianRepository.deleteAll();
        authRepository.deleteAll();
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setIdentification("1053847601");
        registerDTO.setPassword("12345");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk())
                .andReturn();

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setIdentification(registerDTO.getIdentification());
        loginDTO.setPassword(registerDTO.getPassword());

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(response);
        accessToken = jsonNode.get("token").asText(); 
        physicianDB = new Physician();
        physicianDB.setName("Test Name");
        physicianDB.setCode("Test Code");
        physicianDB.setSpeciality("Test Speciality");
        physicianDB.setAuth(new Auth());
        physicianDB.getAuth().setIdentification("1053847620");
        physicianDB.getAuth().setPassword("12345");
    }

    @Test
    void testCreatePhysician() throws Exception {
        PhysicianDTO physicianDTO = new PhysicianDTO();
        physicianDTO.setIdentification("1053847620");
        physicianDTO.setPassword("password");
        physicianDTO.setName("Test Name");
        physicianDTO.setCode("Test Code");
        physicianDTO.setSpeciality("Test Speciality");

        String responseJson = mockMvc.perform(post("/physician")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(physicianDTO)))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PhysicianResponseDTO physician = objectMapper.readValue(responseJson, PhysicianResponseDTO.class);
        assertNotNull(physician);
        assertEquals(physician.getAuth().getIdentification(), physicianDTO.getIdentification());
        assertEquals(physician.getName(), physicianDTO.getName());
        assertEquals(physician.getCode(), physicianDTO.getCode());
    }

    @Test
    void testGetPhysician() throws Exception {
        Physician physician = physicianRepository.save(this.physicianDB);
        String responseJson = mockMvc.perform(get("/physician")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        List<PhysicianResponseDTO> physicianResponseDTO = objectMapper.readValue(responseJson, new TypeReference<List<PhysicianResponseDTO>>(){});
        assertNotNull(physicianResponseDTO);
        assertFalse(physicianResponseDTO.isEmpty());
    }

    @Test
    void testGetByIdPhysician() throws Exception {
        Physician physician = physicianRepository.save(this.physicianDB);
        String responseJson = mockMvc.perform(get("/physician/"+physician.getId())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PhysicianResponseDTO patientResponseDTO = objectMapper.readValue(responseJson, PhysicianResponseDTO.class);
        assertNotNull(patientResponseDTO);
        assertEquals(patientResponseDTO.getAuth().getIdentification(), physician.getAuth().getIdentification());
        assertEquals(patientResponseDTO.getName(), physician.getName());
        assertEquals(patientResponseDTO.getCode(), physician.getCode());
        assertEquals(patientResponseDTO.getSpeciality(), physician.getSpeciality());
    }

    @Test
    void testGetByIdentificationPhysician() throws Exception {
        Patient patient = patientRepository.save(patientDB);
        String responseJson = mockMvc.perform(get("/patient/identification/"+patient.getAuth().getIdentification())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PatientResponseDTO patientResponseDTO = objectMapper.readValue(responseJson, PatientResponseDTO.class);
        assertNotNull(patientResponseDTO);
        assertEquals(patientResponseDTO.getAuth().getIdentification(), patient.getAuth().getIdentification());
        assertEquals(patientResponseDTO.getName(), patient.getName());
        assertEquals(patientResponseDTO.getInsurance(), patient.getInsurance());
    }

    @Test
    void testUpdatePatient() throws Exception {
        Patient patient = patientRepository.save(this.patientDB);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setIdentification(patient.getAuth().getIdentification());
        patientDTO.setPassword(patient.getAuth().getPassword());
        patientDTO.setName("Test Name 2");
        patientDTO.setInsurance("Test Insurance 2");
        String responseJson = mockMvc.perform(put("/patient/"+ patient.getId())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(patientDTO)))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PatientResponseDTO patientResponseDTO = objectMapper.readValue(responseJson, PatientResponseDTO.class);
        assertNotNull(patientResponseDTO);
        assertEquals(patientResponseDTO.getAuth().getIdentification(), patientDTO.getIdentification());
        assertEquals(patientResponseDTO.getName(), patientDTO.getName());
        assertEquals(patientResponseDTO.getInsurance(), patientDTO.getInsurance());
    }

    @Test
    void testDeletePatient() throws Exception {
        Patient patient = patientRepository.save(this.patientDB);
        mockMvc.perform(delete("/patient/"+ patient.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        assertTrue(patientRepository.findById(patient.getId()).isEmpty());
    }

}
