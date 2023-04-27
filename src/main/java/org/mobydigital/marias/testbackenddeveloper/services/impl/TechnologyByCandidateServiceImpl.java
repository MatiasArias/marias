package org.mobydigital.marias.testbackenddeveloper.services.impl;

import jakarta.persistence.EntityNotFoundException;
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
    private final String ID_NOT_FOUND = "Technology by Candidate not found -  id:";
    @Override
    public TechnologyByCandidate createTechnologyByCandidate(TechnologyByCandidate technologyByCandidate) {
        technologyByCandidateRepository.save(technologyByCandidate);
        return technologyByCandidate;
    }

    @Override
    public List<TechnologyByCandidate> findAll(Long idCandidate) {
        return technologyByCandidateRepository.findAll().stream()
                .filter(technologyByCandidate -> technologyByCandidate.getCandidate().getIdCandidate()==(idCandidate))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTechnologyByCandidate(Long id) {
        technologyByCandidateRepository.findById(id)
                .ifPresentOrElse(technologyFind->{
                    technologyByCandidateRepository.delete(technologyFind);
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public TechnologyByCandidate getTechnologyById(Long id) {
        return technologyByCandidateRepository.findById(id).orElseThrow(
                ()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException();
                }
        );
    }

    @Override
    public void updateTechnologyByCandidate(Long id, TechnologyByCandidate technologyByCandidate) {
        technologyByCandidateRepository.findById(id)
                .ifPresentOrElse(technologyFind -> {
                    if(technologyByCandidate.getTechnology() != null) technologyFind.setTechnology(technologyByCandidate.getTechnology());
                    if(technologyByCandidate.getCandidate() != null ) technologyFind.setCandidate(technologyByCandidate.getCandidate());
                    if(technologyByCandidate.getYearsOfExperience() !=null && technologyByCandidate.getYearsOfExperience()>=0){
                        technologyFind.setYearsOfExperience(technologyByCandidate.getYearsOfExperience());
                    }
                    technologyByCandidateRepository.save(technologyFind);
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

}
