package org.mobydigital.marias.testbackenddeveloper.services;

import org.mobydigital.marias.testbackenddeveloper.models.entities.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate createCandidate(Candidate candidate);
    List<Candidate> findAll();
    void deleteCandidate(Long id);
    Candidate getCandidateById(Long id);
    void updateCandidate(Long id, Candidate candidate);

}
