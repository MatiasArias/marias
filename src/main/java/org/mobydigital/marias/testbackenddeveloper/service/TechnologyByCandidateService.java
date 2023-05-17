package org.mobydigital.marias.testbackenddeveloper.service;

import org.mobydigital.marias.testbackenddeveloper.model.entity.TechnologyByCandidate;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyByCandidateDto;

import java.util.List;

public interface TechnologyByCandidateService {
    TechnologyByCandidate createTechnologyByCandidate(TechnologyByCandidateDto technologyByCandidateDto);
    List<TechnologyByCandidate> findAll(Long idCandidato);
    void deleteTechnologyByCandidate(Long id);
    TechnologyByCandidate getTechnologyById(Long id);
    void updateTechnologyByCandidate(Long id, TechnologyByCandidateDto technologyByCandidateDto);
}
