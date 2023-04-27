package org.mobydigital.marias.testbackenddeveloper.controllers;

import org.mobydigital.marias.testbackenddeveloper.models.views.TechnologyByCandidateDto;
import org.mobydigital.marias.testbackenddeveloper.services.TechnologyByCandidateService;
import org.mobydigital.marias.testbackenddeveloper.models.entities.TechnologyByCandidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates/{id}/technologies")
public class TechnologyByCandidateController {
    @Autowired
    TechnologyByCandidateService technologyByCandidateService;

    @GetMapping
    public ResponseEntity<List<TechnologyByCandidate>> getTechnologiesByCandidate(@PathVariable("id") Long id){
        return new ResponseEntity<>(technologyByCandidateService.findAll(id), HttpStatus.OK);
    }
    @GetMapping("/{idTechnologyByCandidate}")
    public ResponseEntity<TechnologyByCandidate> getTechnologiesByCandidateById(@PathVariable("idTechnologyByCandidate") Long id){
        return new ResponseEntity<>(technologyByCandidateService.getTechnologyById(id), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<TechnologyByCandidate> createTechnologyByCandidate(@RequestBody TechnologyByCandidateDto technologyByCandidateDto){
       return new ResponseEntity<>(technologyByCandidateService.createTechnologyByCandidate(technologyByCandidateDto),HttpStatus.OK);
    }

    @PutMapping("/update/{idTechnologyByCandidate}")
    public ResponseEntity<Object> updateTechnologyByCandidate(@PathVariable("idTechnologyByCandidate") Long id, @RequestBody TechnologyByCandidateDto technologyByCandidateDto){
        technologyByCandidateService.updateTechnologyByCandidate(id,technologyByCandidateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idTechnologyByCandidate}")
    public ResponseEntity<Object> deleteTechnologyByCandidate(@PathVariable("idTechnologyByCandidate") Long id){
        technologyByCandidateService.deleteTechnologyByCandidate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
