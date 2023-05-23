package org.mobydigital.marias.testbackenddeveloper.service;

import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyByCandidateDto;

import java.util.List;

public interface TechnologyByCandidateService {
    TechnologyByCandidateDto createTechnologyByCandidate(TechnologyByCandidateDto technologyByCandidateDto);
    List<TechnologyByCandidateDto> findAll(Long idCandidato);
    void deleteTechnologyByCandidate(Long id);
    TechnologyByCandidateDto getTechnologyById(Long id);
    void updateTechnologyByCandidate(Long id, TechnologyByCandidateDto technologyByCandidateDto);
}
