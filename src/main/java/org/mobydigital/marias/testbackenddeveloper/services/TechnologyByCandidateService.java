package org.mobydigital.marias.testbackenddeveloper.services;

import org.mobydigital.marias.testbackenddeveloper.models.entities.TechnologyByCandidate;

import java.util.List;

public interface TechnologyByCandidateService {
    TechnologyByCandidate createTechnologyByCandidate(TechnologyByCandidate technologyByCandidate);
    List<TechnologyByCandidate> findAll(Long idCandidato);
    void deleteTechnologyByCandidate(Long id);
    TechnologyByCandidate getTechnologyById(Long id);
    void updateTechnologyByCandidate(Long id, TechnologyByCandidate technologyByCandidate);
}
