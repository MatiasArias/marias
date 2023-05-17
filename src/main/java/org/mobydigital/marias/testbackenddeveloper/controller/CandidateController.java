package org.mobydigital.marias.testbackenddeveloper.controller;

import org.mobydigital.marias.testbackenddeveloper.model.entity.Candidate;
import org.mobydigital.marias.testbackenddeveloper.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates(){
        return new ResponseEntity<>(candidateService.findAll(), HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Candidate> getCandidatePorId(@PathVariable("id") Long id){
        return new ResponseEntity<>(candidateService.getCandidateById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Candidate> saveCandidate(@RequestBody Candidate candidate){
        return new ResponseEntity<>(candidateService.createCandidate(candidate),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCandidate(@PathVariable("id") Long id){
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate){
        candidateService.updateCandidate(id,candidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
