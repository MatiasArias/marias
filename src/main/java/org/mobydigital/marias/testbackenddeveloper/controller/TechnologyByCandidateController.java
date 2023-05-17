package org.mobydigital.marias.testbackenddeveloper.controller;

import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyByCandidateDto;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyByCandidateService;
import org.mobydigital.marias.testbackenddeveloper.model.entity.TechnologyByCandidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidateTechnology")
public class TechnologyByCandidateController {
    @Autowired
    TechnologyByCandidateService technologyByCandidateService;

    @GetMapping("/{id}")
    public ResponseEntity<List<TechnologyByCandidate>> getTechnologiesByCandidate(@PathVariable("id") Long id){
        return new ResponseEntity<>(technologyByCandidateService.findAll(id), HttpStatus.OK);
    }
    @GetMapping("/{idTechnologyByCandidate}")
    public ResponseEntity<TechnologyByCandidate> getTechnologiesByCandidateById(@PathVariable("idTechnologyByCandidate") Long id){
        return new ResponseEntity<>(technologyByCandidateService.getTechnologyById(id), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<TechnologyByCandidate> createTechnologyByCandidate(@RequestBody TechnologyByCandidateDto technologyByCandidateDto){
       return new ResponseEntity<>(technologyByCandidateService.createTechnologyByCandidate(technologyByCandidateDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{idTechnologyByCandidate}")
    public ResponseEntity<HttpStatus> updateTechnologyByCandidate(@PathVariable("idTechnologyByCandidate") Long id, @RequestBody TechnologyByCandidateDto technologyByCandidateDto){
        technologyByCandidateService.updateTechnologyByCandidate(id,technologyByCandidateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idTechnologyByCandidate}")
    public ResponseEntity<HttpStatus> deleteTechnologyByCandidate(@PathVariable("idTechnologyByCandidate") Long id){
        technologyByCandidateService.deleteTechnologyByCandidate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
