package org.mobydigital.marias.testbackenddeveloper.controller;

import org.mobydigital.marias.testbackenddeveloper.model.view.CandidateDto;
import org.mobydigital.marias.testbackenddeveloper.service.CandidateService;
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


import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<CandidateDto>> getCandidates(){
        return new ResponseEntity<>(candidateService.findAll(), HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<CandidateDto> getCandidatePorId(@PathVariable("id") Long id){
        return new ResponseEntity<>(candidateService.getCandidateById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CandidateDto> saveCandidate(@RequestBody CandidateDto candidate){
        return new ResponseEntity<>(candidateService.createCandidate(candidate),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCandidate(@PathVariable("id") Long id){
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateCandidate(@PathVariable Long id, @RequestBody CandidateDto candidate){
        candidateService.updateCandidate(id,candidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
