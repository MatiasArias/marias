package org.mobydigital.marias.testbackenddeveloper.services;

import org.mobydigital.marias.testbackenddeveloper.models.entities.TechnologyByCandidate;
import org.mobydigital.marias.testbackenddeveloper.models.views.TechnologyByCandidateDto;

import java.util.List;

public interface TechnologyByCandidateService {
    TechnologyByCandidate createTechnologyByCandidate(TechnologyByCandidateDto technologyByCandidateDto);
    List<TechnologyByCandidate> findAll(Long idCandidato);
    void deleteTechnologyByCandidate(Long id);
    TechnologyByCandidate getTechnologyById(Long id);
    void updateTechnologyByCandidate(Long id, TechnologyByCandidateDto technologyByCandidateDto);
}
