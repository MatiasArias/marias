package org.mobydigital.marias.testbackenddeveloper.controllers;

import org.mobydigital.marias.testbackenddeveloper.models.entities.Candidate;
import org.mobydigital.marias.testbackenddeveloper.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
