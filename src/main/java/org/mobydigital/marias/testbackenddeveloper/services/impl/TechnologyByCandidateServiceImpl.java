package org.mobydigital.marias.testbackenddeveloper.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.models.entities.TechnologyByCandidate;
import org.mobydigital.marias.testbackenddeveloper.models.views.TechnologyByCandidateDto;
import org.mobydigital.marias.testbackenddeveloper.repositories.TechnologyByCandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.services.CandidateService;
import org.mobydigital.marias.testbackenddeveloper.services.TechnologyByCandidateService;
import org.mobydigital.marias.testbackenddeveloper.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TechnologyByCandidateServiceImpl implements TechnologyByCandidateService {
    @Autowired
    TechnologyByCandidateRepository technologyByCandidateRepository;

    @Autowired
    TechnologyService technologyService;

    @Autowired
    CandidateService candidateService;
    private final String ID_NOT_FOUND = "Technology by Candidate not found -  id:";
    @Override
    public TechnologyByCandidate createTechnologyByCandidate(TechnologyByCandidateDto technologyByCandidateDto) {
        TechnologyByCandidate technologyByCandidate= new TechnologyByCandidate();
        technologyByCandidate.setCandidate(candidateService.getCandidateById(technologyByCandidateDto.getIdCandidate()));
        technologyByCandidate.setTechnology(technologyService.getTechnologyById(technologyByCandidateDto.getIdTechnology()));
        technologyByCandidate.setYearsOfExperience(technologyByCandidateDto.getYearsOfExperience());
        technologyByCandidateRepository.save(technologyByCandidate);
        log.info(String.format("Technology by Candidate created successfully"));
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
                    log.info("Technology by Candidate deleted successfully");
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
    public void updateTechnologyByCandidate(Long id, TechnologyByCandidateDto technologyByCandidate) {
        technologyByCandidateRepository.findById(id)
                .ifPresentOrElse(technologyFind -> {
                    if(technologyByCandidate.getIdTechnology() != null) {
                        technologyFind.setTechnology(technologyService.getTechnologyById(technologyByCandidate.getIdTechnology()));
                    }
                    if(technologyByCandidate.getIdCandidate() != null ) {
                        technologyFind.setCandidate(candidateService.getCandidateById(technologyByCandidate.getIdCandidate()));
                    }
                    if(technologyByCandidate.getYearsOfExperience() !=null && technologyByCandidate.getYearsOfExperience()>=0){
                        technologyFind.setYearsOfExperience(technologyByCandidate.getYearsOfExperience());
                    }
                    technologyByCandidateRepository.save(technologyFind);
                    log.info(String.format("Technology by Candidate created successfully"));
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

}
