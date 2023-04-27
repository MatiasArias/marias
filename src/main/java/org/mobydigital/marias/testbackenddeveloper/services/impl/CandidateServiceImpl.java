package org.mobydigital.marias.testbackenddeveloper.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.models.entities.Candidate;
import org.mobydigital.marias.testbackenddeveloper.repositories.CandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate createCandidate(Candidate candidate) {
        return null;
    }

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public void deleteCandidate(Long id) {

    }

    @Override
    public Candidate getCandidateById(Long id) {
        return null;
    }

    @Override
    public void updateCandidate(Long id, Candidate candidate) {

    }
}
