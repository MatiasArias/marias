package org.mobydigital.marias.testbackenddeveloper.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mobydigital.marias.testbackenddeveloper.model.projection.TechnologyByCandidateView;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyByCandidateDto;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyDto;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyByCandidateService;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)public class TechnologyByCandidateTest {
    private MockMvc mockMvc;

    @Mock
    private TechnologyByCandidateService technologyByCandidateService;

    @InjectMocks
    private TechnologyByCandidateController technologyByCandidateController;

    private final ObjectMapper mapper = new ObjectMapper();
    private TechnologyByCandidateDto technologyByCandidateDto;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(technologyByCandidateController).build();
    }

    @BeforeEach
    public void getTechnologyByCandidateDto() {
        technologyByCandidateDto = new TechnologyByCandidateDto();
        technologyByCandidateDto.setIdCandidate(1L);
        technologyByCandidateDto.setIdTechnology(1L);
        technologyByCandidateDto.setYearsOfExperience(2);
    }
    @Test
    @DisplayName("GET http://localhost:8080/candidateTechnology/{id} - Success")
    void testGetTechnologiesByCandidate() throws Exception {
        Long candidateId = 1L;
        List<TechnologyByCandidateDto> technologies = new ArrayList<>();
        technologies.add(technologyByCandidateDto);

        when(technologyByCandidateService.findAll(candidateId)).thenReturn(technologies);

        mockMvc.perform(get("/candidateTechnology/{id}", candidateId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET http://localhost:8080/candidateTechnology/id/{idTechnologyByCandidate} - Success")
    void testGetTechnologyByCandidateById() throws Exception {
        Long technologyByCandidateId = 1L;
        when(technologyByCandidateService.getTechnologyById(technologyByCandidateId))
                .thenReturn(technologyByCandidateDto);

        mockMvc.perform(get("/candidateTechnology/id/{idTechnologyByCandidate}", technologyByCandidateId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(technologyByCandidateDto.getId()))));
    }

    @Test
    @DisplayName("GET http://localhost:8080/candidateTechnology/candidatesByTechnology - Success")
    void testGetCandidatesByTechnology() throws Exception {
        String technologyName = "Java";
        List<TechnologyByCandidateView> candidates = new ArrayList<>();

        when(technologyByCandidateService.getCandidatesByTechnology(technologyName)).thenReturn(candidates);

        mockMvc.perform(get("/candidateTechnology/candidatesByTechnology")
                        .param("technologyName", technologyName))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST http://localhost:8080/candidateTechnology/create - Success")
    void testCreateTechnologyByCandidate() throws Exception {
        when(technologyByCandidateService.createTechnologyByCandidate(Mockito.eq(technologyByCandidateDto)))
                .thenReturn(technologyByCandidateDto);

        mockMvc.perform(post("/candidateTechnology/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(technologyByCandidateDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(equalTo(technologyByCandidateDto.getId()))))
                .andExpect(jsonPath("$.idTechnology", is(equalTo(technologyByCandidateDto.getIdTechnology().intValue()))))
                .andExpect(jsonPath("$.idCandidate", is(equalTo(technologyByCandidateDto.getIdCandidate().intValue()))))
                .andExpect(jsonPath("$.yearsOfExperience", is(equalTo(technologyByCandidateDto.getYearsOfExperience().intValue()))));
    }

    @Test
    @DisplayName("PUT http://localhost:8080/candidateTechnology/update/{idTechnologyByCandidate} - Success")
    void testUpdateTechnologyByCandidate() throws Exception {
        Long technologyByCandidateId = 1L;

        mockMvc.perform(put("/candidateTechnology/update/{idTechnologyByCandidate}", technologyByCandidateId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(technologyByCandidateDto)))
                .andExpect(status().isOk());

        Mockito.verify(technologyByCandidateService, Mockito.times(1))
                .updateTechnologyByCandidate(eq(technologyByCandidateId), eq(technologyByCandidateDto));
    }

    @Test
    @DisplayName("DELETE http://localhost:8080/candidateTechnology/delete/{idTechnologyByCandidate} - Success")
    void testDeleteTechnologyByCandidate() throws Exception {
        Long technologyByCandidateId = 1L;

        mockMvc.perform(delete("/candidateTechnology/delete/{idTechnologyByCandidate}", technologyByCandidateId))
                .andExpect(status().isNoContent());

        Mockito.verify(technologyByCandidateService, Mockito.times(1))
                .deleteTechnologyByCandidate(eq(technologyByCandidateId));
    }
}
