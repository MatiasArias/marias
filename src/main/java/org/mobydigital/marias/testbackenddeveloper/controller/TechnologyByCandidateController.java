package org.mobydigital.marias.testbackenddeveloper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mobydigital.marias.testbackenddeveloper.model.projection.TechnologyByCandidateView;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyByCandidateDto;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyByCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@RestController
@RequestMapping("/candidateTechnology")
@Tag(name="Technology by Candidate controller")
public class TechnologyByCandidateController {
    @Autowired
    TechnologyByCandidateService technologyByCandidateService;

    @GetMapping("/{id}")
    @Operation(summary = "findAll")
    public ResponseEntity<List<TechnologyByCandidateDto>> getTechnologiesByCandidate(@PathVariable("id") Long id){
        return new ResponseEntity<>(technologyByCandidateService.findAll(id), HttpStatus.OK);
    }
    @GetMapping("/id/{idTechnologyByCandidate}")
    @Operation(summary = "getTechnologyById")
    public ResponseEntity<TechnologyByCandidateDto> getTechnologiesByCandidateById(@PathVariable("idTechnologyByCandidate") Long id){
        return new ResponseEntity<>(technologyByCandidateService.getTechnologyById(id), HttpStatus.OK);
    }
    @GetMapping("/candidatesByTechnology")
    @Operation(summary = "getCandidatesByTechnology")
    public ResponseEntity<List<TechnologyByCandidateView>> getCandidatesByTechnology(@RequestParam String technologyName){
        return new ResponseEntity<>(technologyByCandidateService.getCandidatesByTechnology(technologyName), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    @Operation(summary = "createTechnologyByCandidate")
    public ResponseEntity<TechnologyByCandidateDto> createTechnologyByCandidate(@RequestBody TechnologyByCandidateDto technologyByCandidateDto){
       return new ResponseEntity<>(technologyByCandidateService.createTechnologyByCandidate(technologyByCandidateDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{idTechnologyByCandidate}")
    @Operation(summary = "updateTechnologyByCandidate")
    public ResponseEntity<HttpStatus> updateTechnologyByCandidate(@PathVariable("idTechnologyByCandidate") Long id, @RequestBody TechnologyByCandidateDto technologyByCandidateDto){
        technologyByCandidateService.updateTechnologyByCandidate(id,technologyByCandidateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idTechnologyByCandidate}")
    @Operation(summary = "deleteTechnologyByCandidate")
    public ResponseEntity<HttpStatus> deleteTechnologyByCandidate(@PathVariable("idTechnologyByCandidate") Long id){
        technologyByCandidateService.deleteTechnologyByCandidate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
