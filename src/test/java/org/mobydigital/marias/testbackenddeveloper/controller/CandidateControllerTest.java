package org.mobydigital.marias.testbackenddeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mobydigital.marias.testbackenddeveloper.model.enums.DocumentTypeEnum;
import org.mobydigital.marias.testbackenddeveloper.model.view.CandidateDto;
import org.mobydigital.marias.testbackenddeveloper.service.CandidateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CandidateControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CandidateService candidateService;
    @InjectMocks
    private CandidateController candidateController;
    private final ObjectMapper mapper = new ObjectMapper();
    private CandidateDto candidateDto;

    @BeforeEach
    public void getCandidateDto(){
        candidateDto = new CandidateDto();
        candidateDto.setName("Matias");
        candidateDto.setLastname("Arias");
        candidateDto.setBirthdate(Date.from(Instant.now()));
        candidateDto.setDocumentNumber(912201831);
        candidateDto.setDocumentType(DocumentTypeEnum.DNI);
    }

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(candidateController).build();
    }

    @Test
    @DisplayName("GET http://localhost:8080/candidate - Success")
    void testGetCandidates() throws Exception {

        List<CandidateDto> candidates = new ArrayList<>();
        candidates.add(candidateDto);

        when(candidateService.findAll()).thenReturn(candidates);

        mockMvc.perform(get("/candidate"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET http://localhost:8080/candidate/1 - Success")
    void testCandidateById() throws Exception {

        when(candidateService.getCandidateById(1L)).thenReturn(candidateDto);

        mockMvc.perform(get("/candidate/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(equalTo(candidateDto.getName()))))
                .andExpect(jsonPath("$.lastname", is(equalTo(candidateDto.getLastname()))))
                .andExpect(jsonPath("$.documentNumber", is(equalTo(candidateDto.getDocumentNumber()))))
                .andExpect(jsonPath("$.documentType", is(equalTo(candidateDto.getDocumentType().toString()))));
    }

    @Test
    @DisplayName("POST http://localhost:8080/candidate/create - Success")
    void testCreateCandidate() throws Exception {
        when(candidateService.createCandidate(Mockito.eq(candidateDto))).thenReturn(candidateDto);

        mockMvc.perform(post("/candidate/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(candidateDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(equalTo(candidateDto.getName()))))
                .andExpect(jsonPath("$.lastname", is(equalTo(candidateDto.getLastname()))))
                .andExpect(jsonPath("$.documentNumber", is(equalTo(candidateDto.getDocumentNumber()))))
                .andExpect(jsonPath("$.documentType", is(equalTo(candidateDto.getDocumentType().toString()))));
    }

    @Test
    @DisplayName("UPDATE http://localhost:8080/candidate/update/1 - Success")
    void testUpdateCandidate() throws Exception {
        CandidateDto candidateDtoPut = new CandidateDto("Matias","Arias",DocumentTypeEnum.DNI,43132313,Date.from(Instant.now()));

        mockMvc.perform(put("/candidate/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(candidateDtoPut)))
                .andExpect(status().isOk());
        Mockito.verify(candidateService,Mockito.times(1)).updateCandidate(eq(1L),eq(candidateDtoPut));
    }

    @Test
    @DisplayName("DELETE http://localhost:8080/candidate/delete/1 - Success")
    void testDeleteCandidate() throws Exception {
        mockMvc.perform(delete("/candidate/delete/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(candidateService,Mockito.times(1)).deleteCandidate(eq(1L));
    }
}
