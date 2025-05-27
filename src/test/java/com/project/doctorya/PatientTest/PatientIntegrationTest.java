package com.project.doctorya.PatientTest;

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
import com.project.doctorya.auth.dto.LoginDTO;
import com.project.doctorya.auth.dto.RegisterDTO;
import com.project.doctorya.auth.entity.Auth;
import com.project.doctorya.auth.repository.AuthRepository;
import com.project.doctorya.patient.dto.PatientDTO;
import com.project.doctorya.patient.dto.PatientResponseDTO;
import com.project.doctorya.patient.entity.Patient;
import com.project.doctorya.patient.repository.PatientRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AuthRepository authRepository;

    private String accessToken;
    
    private Patient patientDB;

    @BeforeEach
    void beforeTest() throws Exception {
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
        patientDB = new Patient();
        patientDB.setName("Test Name");
        patientDB.setInsurance("Test Insurance");
        patientDB.setAuth(new Auth());
        patientDB.getAuth().setIdentification("1053847610");
        patientDB.getAuth().setPassword("12345");
    }

    @AfterEach
    void afterEach(){
        patientRepository.deleteAll();
        authRepository.deleteAll();
    }

    @Test
    void testCreatePatient() throws Exception {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setIdentification("1053847610");
        patientDTO.setPassword("password");
        patientDTO.setName("Test Name");
        patientDTO.setInsurance("Test Insurance");

        String responseJson = mockMvc.perform(post("/patient")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(patientDTO)))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PatientResponseDTO patient = objectMapper.readValue(responseJson, PatientResponseDTO.class);
        assertNotNull(patient);
        assertEquals(patient.getAuth().getIdentification(), patientDTO.getIdentification());
        assertEquals(patient.getName(), patientDTO.getName());
        assertEquals(patient.getInsurance(), patientDTO.getInsurance());
    }

    @Test
    void testGetPatient() throws Exception {
        patientRepository.save(this.patientDB);
        String responseJson = mockMvc.perform(get("/patient")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        List<PatientResponseDTO> patientResponseDTO = objectMapper.readValue(responseJson, new TypeReference<List<PatientResponseDTO>>(){});
        assertNotNull(patientResponseDTO);
        assertFalse(patientResponseDTO.isEmpty());
    }

    @Test
    void testGetByIdPatient() throws Exception {
        Patient patient = patientRepository.save(this.patientDB);
        String responseJson = mockMvc.perform(get("/patient/"+patient.getId())
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
    void testGetByIdentificationPatient() throws Exception {
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
