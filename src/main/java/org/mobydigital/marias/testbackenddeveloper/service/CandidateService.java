package org.mobydigital.marias.testbackenddeveloper.service;

import org.mobydigital.marias.testbackenddeveloper.model.entity.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate createCandidate(Candidate candidate);
    List<Candidate> findAll();
    void deleteCandidate(Long id);
    Candidate getCandidateById(Long id);
    void updateCandidate(Long id, Candidate candidate);

}
