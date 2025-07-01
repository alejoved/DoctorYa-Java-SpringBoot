package com.project.doctorya;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.UUID;
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
import com.project.doctorya.auth.infrastructure.entities.Auth;
import com.project.doctorya.auth.infrastructure.repositories.IAuthJpaRepository;
import com.project.doctorya.auth.rest.dto.AuthDTO;
import com.project.doctorya.physician.rest.dto.PhysicianDTO;
import com.project.doctorya.physician.rest.dto.PhysicianResponseDTO;
import com.project.doctorya.shared.Role;
import com.project.doctorya.physician.infrastructure.entities.Physician;
import com.project.doctorya.physician.infrastructure.repositories.IPhysicianJpaRepository;

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
    private IPhysicianJpaRepository physicianRepository;

    @Autowired
    private IAuthJpaRepository authRepository;

    private String accessToken;
    
    private Physician physicianDB;

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
        physicianDB = new Physician();
        physicianDB.setName("Test Name");
        physicianDB.setCode("Test Code");
        physicianDB.setSpeciality("Test Speciality");
        physicianDB.setAuth(new Auth());
        physicianDB.getAuth().setIdentification("1053847620");
        physicianDB.getAuth().setPassword("12345");
        physicianDB.getAuth().setRole(Role.PHYSICIAN);
    }

    @AfterEach
    void afterEach(){
        physicianRepository.deleteAll();
        authRepository.deleteAll();
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
        assertEquals(physician.getIdentification(), physicianDTO.getIdentification());
        assertEquals(physician.getName(), physicianDTO.getName());
        assertEquals(physician.getCode(), physicianDTO.getCode());
    }

    @Test
    void testExistsCreatePhysician() throws Exception {
        physicianRepository.save(this.physicianDB);
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
    void testGetPhysician() throws Exception {
        physicianRepository.save(this.physicianDB);
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
        assertEquals(patientResponseDTO.getIdentification(), physician.getAuth().getIdentification());
        assertEquals(patientResponseDTO.getName(), physician.getName());
        assertEquals(patientResponseDTO.getCode(), physician.getCode());
        assertEquals(patientResponseDTO.getSpeciality(), physician.getSpeciality());
    }

    @Test
    void testGetByIdNotFoundPhysician() throws Exception {
        UUID id = UUID.randomUUID();
        String responseJson = mockMvc.perform(get("/physician/" + id)
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
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
    void testGetByIdentificationPhysician() throws Exception {
        Physician physician = physicianRepository.save(physicianDB);
        String responseJson = mockMvc.perform(get("/physician/identification/" + physician.getAuth().getIdentification())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PhysicianResponseDTO physicianResponseDTO = objectMapper.readValue(responseJson, PhysicianResponseDTO.class);
        assertNotNull(physicianResponseDTO);
        assertEquals(physicianResponseDTO.getIdentification(), physician.getAuth().getIdentification());
        assertEquals(physicianResponseDTO.getName(), physician.getName());
        assertEquals(physicianResponseDTO.getCode(), physician.getCode());
        assertEquals(physicianResponseDTO.getSpeciality(), physician.getSpeciality());
    }

    @Test
    void testGetByIdentificationNotFoundPhysician() throws Exception {
        String identification = "1053847600";
        String responseJson = mockMvc.perform(get("/physician/identification/" + identification)
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON))
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
    void testUpdatePhysician() throws Exception {
        Physician physician = physicianRepository.save(this.physicianDB);
        PhysicianDTO physicianDTO = new PhysicianDTO();
        physicianDTO.setIdentification(physician.getAuth().getIdentification());
        physicianDTO.setPassword(physician.getAuth().getPassword());
        physicianDTO.setName("Test Name 2");
        physicianDTO.setCode("Test Code 2");
        physicianDTO.setSpeciality("Test Speciality 2");
        String responseJson = mockMvc.perform(put("/physician/"+ physician.getId())
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(physicianDTO)))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        PhysicianResponseDTO physicianResponseDTO = objectMapper.readValue(responseJson, PhysicianResponseDTO.class);
        assertNotNull(physicianResponseDTO);
        assertEquals(physicianResponseDTO.getIdentification(), physicianDTO.getIdentification());
        assertEquals(physicianResponseDTO.getName(), physicianDTO.getName());
        assertEquals(physicianResponseDTO.getCode(), physicianDTO.getCode());
        assertEquals(physicianResponseDTO.getSpeciality(), physicianDTO.getSpeciality());
    }

    @Test
    void testNotFoundUpdatePhysician() throws Exception {
        UUID id = UUID.randomUUID();
        Physician physician = physicianRepository.save(this.physicianDB);
        PhysicianDTO physicianDTO = new PhysicianDTO();
        physicianDTO.setIdentification(physician.getAuth().getIdentification());
        physicianDTO.setPassword(physician.getAuth().getPassword());
        physicianDTO.setName("Test Name 2");
        physicianDTO.setCode("Test Code 2");
        physicianDTO.setSpeciality("Test Speciality 2");
        String responseJson = mockMvc.perform(put("/physician/"+ id)
                            .header("Authorization", "Bearer " + accessToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(physicianDTO)))
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
    void testDeletePhysician() throws Exception {
        Physician physician = physicianRepository.save(this.physicianDB);
        mockMvc.perform(delete("/physician/"+ physician.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        assertTrue(physicianRepository.findById(physician.getId()).isEmpty());
    }

    @Test
    void testNotFoundDeletePhysician() throws Exception {
        UUID id = UUID.randomUUID();
        physicianRepository.save(this.physicianDB);
        String responseJson = mockMvc.perform(delete("/physician/"+ id)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        String expectedError = "Physician not found";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(responseJson);
        assertEquals(expectedError, response.get("error").asText());
    }
}
