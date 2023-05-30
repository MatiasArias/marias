package org.mobydigital.marias.testbackenddeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyDto;
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


@ExtendWith(MockitoExtension.class)
class TechnologyControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TechnologyService technologyService;
    @InjectMocks
    private TechnologyController technologyController;
    private final ObjectMapper mapper = new ObjectMapper();
    private TechnologyDto technologyDto;

    @BeforeEach
    public void getTechnologyDto() {
        technologyDto = new TechnologyDto();
        technologyDto.setName("Java");
        technologyDto.setVersion("11");
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(technologyController).build();
    }
    @Test
    @DisplayName("GET http://localhost:8080/technology - Success")
    void testGetTechnologies() throws Exception {
        List<TechnologyDto> technologies = new ArrayList<>();
        technologies.add(technologyDto);

        when(technologyService.findAll()).thenReturn(technologies);

        mockMvc.perform(get("/technology"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET http://localhost:8080/technology/1 - Success")
    void testGetTechnologyById() throws Exception {
        when(technologyService.getTechnologyById(1L)).thenReturn(technologyDto);

        mockMvc.perform(get("/technology/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(equalTo(technologyDto.getName()))))
                .andExpect(jsonPath("$.version", is(equalTo(technologyDto.getVersion()))));
    }
    @Test
    @DisplayName("POST http://localhost:8080/technology/create - Success")
    void testCreateTechnology() throws Exception {
        when(technologyService.createTechnology(eq(technologyDto))).thenReturn(technologyDto);

        mockMvc.perform(post("/technology/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(technologyDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(equalTo(technologyDto.getName()))))
                .andExpect(jsonPath("$.version", is(equalTo(technologyDto.getVersion()))));
    }

    @Test
    @DisplayName("UPDATE http://localhost:8080/technology/update/1 - Success")
    void testUpdateTechnology() throws Exception {
        TechnologyDto technologyDtoPut = new TechnologyDto("Java", "12");

        mockMvc.perform(put("/technology/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(technologyDtoPut)))
                .andExpect(status().isOk());

        Mockito.verify(technologyService, Mockito.times(1))
                .updateTechnology(eq(1L), eq(technologyDtoPut));
    }

    @Test
    @DisplayName("DELETE http://localhost:8080/technology/delete/1 - Success")
    void testDeleteTechnology() throws Exception {
        mockMvc.perform(delete("/technology/delete/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(technologyService, Mockito.times(1))
                .deleteTechnology(eq(1L));
    }

}
