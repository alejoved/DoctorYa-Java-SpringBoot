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
import com.project.doctorya.patient.rest.dto.PatientDTO;
import com.project.doctorya.patient.rest.dto.PatientResponseDTO;
import com.project.doctorya.patient.infrastructure.entities.PatientEntity;
import com.project.doctorya.patient.infrastructure.repositories.IPatientJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPatientJpaRepository patientRepository;

    @Autowired
    private IAuthJpaRepository authRepository;

    private String accessToken;
    
    private PatientEntity patientDB;

    @BeforeEach
    void beforeTest() throws Exception {
        AuthDTO registerDTO = new AuthDTO();
        registerDTO.setIdentification("1053847601");
        registerDTO.setPassword("12345");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk())
                .andReturn();

        AuthDTO loginDTO = new AuthDTO();
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
        patientDB = new PatientEntity();
        patientDB.setName("Test Name");
        patientDB.setInsurance("Test Insurance");
        patientDB.setAuthEntity(new AuthEntity());
        patientDB.getAuthEntity().setIdentification("1053847610");
        patientDB.getAuthEntity().setPassword("12345");
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
        assertEquals(patient.getIdentification(), patientDTO.getIdentification());
        assertEquals(patient.getName(), patientDTO.getName());
        assertEquals(patient.getInsurance(), patientDTO.getInsurance());
    }

    @Test
    void testExistsCreatePatient() throws Exception {
        patientRepository.save(this.patientDB);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setIdentification("1053847610");
        patientDTO.setPassword("password");
        patientDTO.setName("Test Name");
        patientDTO.setInsurance("Test Insurance");

        String responseJson = mockMvc.perform(post("/patient")
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(patientDTO)))
                            .andExpect(status().isConflict())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();
        String expectedError = "Auth already exists";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
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
        PatientEntity patient = patientRepository.save(this.patientDB);
        String responseJson = mockMvc.perform(get("/patient/"+patient.getId())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PatientResponseDTO patientResponseDTO = objectMapper.readValue(responseJson, PatientResponseDTO.class);
        assertNotNull(patientResponseDTO);
        assertEquals(patientResponseDTO.getIdentification(), patient.getAuthEntity().getIdentification());
        assertEquals(patientResponseDTO.getName(), patient.getName());
        assertEquals(patientResponseDTO.getInsurance(), patient.getInsurance());
    }

    @Test
    void testGetByIdNotFoundPatient() throws Exception {
        UUID id = UUID.randomUUID();
        String responseJson = mockMvc.perform(get("/patient/" + id)
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
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
    void testGetByIdentificationPatient() throws Exception {
        PatientEntity patient = patientRepository.save(patientDB);
        String responseJson = mockMvc.perform(get("/patient/identification/"+patient.getAuthEntity().getIdentification())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PatientResponseDTO patientResponseDTO = objectMapper.readValue(responseJson, PatientResponseDTO.class);
        assertNotNull(patientResponseDTO);
        assertEquals(patientResponseDTO.getIdentification(), patient.getAuthEntity().getIdentification());
        assertEquals(patientResponseDTO.getName(), patient.getName());
        assertEquals(patientResponseDTO.getInsurance(), patient.getInsurance());
    }

    @Test
    void testGetByIdentificationNotFoundPatient() throws Exception {
        String identification = "1053847600";
        String responseJson = mockMvc.perform(get("/patient/identification/" + identification)
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
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
    void testUpdatePatient() throws Exception {
        PatientEntity patient = patientRepository.save(this.patientDB);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setIdentification(patient.getAuthEntity().getIdentification());
        patientDTO.setPassword(patient.getAuthEntity().getPassword());
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
        assertEquals(patientResponseDTO.getIdentification(), patientDTO.getIdentification());
        assertEquals(patientResponseDTO.getName(), patientDTO.getName());
        assertEquals(patientResponseDTO.getInsurance(), patientDTO.getInsurance());
    }

    @Test
    void testNotFoundUpdatePatient() throws Exception {
        UUID id = UUID.randomUUID();
        PatientEntity patient = patientRepository.save(this.patientDB);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setIdentification(patient.getAuthEntity().getIdentification());
        patientDTO.setPassword(patient.getAuthEntity().getPassword());
        patientDTO.setName("Test Name 2");
        patientDTO.setInsurance("Test Insurance 2");
        String responseJson = mockMvc.perform(put("/patient/"+ id)
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(patientDTO)))
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
    void testDeletePatient() throws Exception {
        PatientEntity patient = patientRepository.save(this.patientDB);
        mockMvc.perform(delete("/patient/"+ patient.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        assertTrue(patientRepository.findById(patient.getId()).isEmpty());
    }


    @Test
    void testNotFoundDeletePatient() throws Exception {
        UUID id = UUID.randomUUID();
        patientRepository.save(this.patientDB);
        String responseJson = mockMvc.perform(delete("/patient/"+ id)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        String expectedError = "Patient not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }

}
