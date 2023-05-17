package org.mobydigital.marias.testbackenddeveloper.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.model.entity.TechnologyByCandidate;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyByCandidateDto;
import org.mobydigital.marias.testbackenddeveloper.repository.TechnologyByCandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.service.CandidateService;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyByCandidateService;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyService;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper mapper = new ModelMapper();
    @Override
    public TechnologyByCandidate createTechnologyByCandidate(TechnologyByCandidateDto technologyByCandidateDto) {
        TechnologyByCandidate technologyByCandidate = mapper.map(technologyByCandidateDto, TechnologyByCandidate.class);
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
                    technologyFind = mapper.map(technologyByCandidate, TechnologyByCandidate.class);
                    technologyByCandidateRepository.save(technologyFind);
                    log.info(String.format("Technology by Candidate created successfully"));
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

}
