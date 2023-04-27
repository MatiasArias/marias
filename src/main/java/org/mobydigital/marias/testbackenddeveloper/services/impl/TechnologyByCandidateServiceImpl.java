package org.mobydigital.marias.testbackenddeveloper.services.impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.models.entities.TechnologyByCandidate;
import org.mobydigital.marias.testbackenddeveloper.repositories.TechnologyByCandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.services.TechnologyByCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TechnologyByCandidateServiceImpl implements TechnologyByCandidateService {
    @Autowired
    TechnologyByCandidateRepository technologyByCandidateRepository;
    @Override
    public TechnologyByCandidate createTechnologyByCandidate(TechnologyByCandidate technologyByCandidate) {
        return null;
    }

    @Override
    public List<TechnologyByCandidate> findAll(Long idCandidato) {
        return technologyByCandidateRepository.findAll().stream()
                .filter(technologyByCandidate -> technologyByCandidate.getCandidate().getIdCandidate()==(idCandidato))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTechnologyByCandidate(Long id) {

    }

    @Override
    public TechnologyByCandidate getTechnologyById(Long id) {
        return null;
    }

    @Override
    public void updateTechnologyByCandidate(Long id, TechnologyByCandidate technologyByCandidate) {

    }
}
