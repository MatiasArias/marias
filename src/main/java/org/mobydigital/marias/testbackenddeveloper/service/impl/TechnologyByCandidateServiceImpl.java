package org.mobydigital.marias.testbackenddeveloper.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.model.entity.Candidate;
import org.mobydigital.marias.testbackenddeveloper.model.entity.Technology;
import org.mobydigital.marias.testbackenddeveloper.model.entity.TechnologyByCandidate;
import org.mobydigital.marias.testbackenddeveloper.model.projection.TechnologyByCandidateView;
import org.mobydigital.marias.testbackenddeveloper.model.view.CandidateDto;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyByCandidateDto;
import org.mobydigital.marias.testbackenddeveloper.repository.CandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.repository.TechnologyByCandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.repository.TechnologyRepository;
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
    TechnologyRepository technologyRepository;

    @Autowired
    CandidateRepository candidateRepository;
    private static final String ID_NOT_FOUND = "Technology by Candidate not found -  id:";
    private static final String ID_TECHNOLOGY_NOT_FOUND = "Technology not found - id:";
    private static final String ID_CANDIDATE_NOT_FOUND = "Candidate not found - id:";


    private final ModelMapper modelMapper = new ModelMapper();
    @Override
    public TechnologyByCandidateDto createTechnologyByCandidate(TechnologyByCandidateDto technologyByCandidateDto) {
        Candidate candidate = candidateRepository.findById(technologyByCandidateDto.getIdCandidate()).orElseThrow(
                ()->{
                    log.error(ID_CANDIDATE_NOT_FOUND+ technologyByCandidateDto.getIdCandidate(), new EntityNotFoundException(ID_CANDIDATE_NOT_FOUND));
                    throw new EntityNotFoundException(ID_CANDIDATE_NOT_FOUND);
                });
        Technology technology = technologyRepository.findById(technologyByCandidateDto.getIdTechnology()).orElseThrow(
                ()->{
                    log.error(ID_TECHNOLOGY_NOT_FOUND+technologyByCandidateDto.getIdTechnology(), new EntityNotFoundException(ID_TECHNOLOGY_NOT_FOUND));
                    throw new EntityNotFoundException(ID_TECHNOLOGY_NOT_FOUND);
                });
        TechnologyByCandidate technologyByCandidate = modelMapper.map(technologyByCandidateDto, TechnologyByCandidate.class);
        technologyByCandidate.setCandidate(candidate);
        technologyByCandidate.setTechnology(technology);
        technologyByCandidateRepository.save(technologyByCandidate);
        log.info("Technology by Candidate created successfully");
        return technologyByCandidateDto;
    }

    @Override
    public List<TechnologyByCandidateDto> findAll(Long idCandidate) {
        return technologyByCandidateRepository.findAll().stream()
                .filter(technologyByCandidate -> technologyByCandidate.getCandidate().getIdCandidate()==(idCandidate))
                .map(technologyByCandidate -> modelMapper.map(technologyByCandidate, TechnologyByCandidateDto.class))
                .toList();
    }

    @Override
    public void deleteTechnologyByCandidate(Long id) {
        technologyByCandidateRepository.findById(id)
                .ifPresentOrElse(technologyFind->{
                    technologyByCandidateRepository.delete(technologyFind);
                    log.info("Technology by Candidate deleted successfully");
                },()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public TechnologyByCandidateDto getTechnologyById(Long id) {
        return technologyByCandidateRepository.findById(id)
                .map(technologyByCandidate -> modelMapper.map(technologyByCandidate, TechnologyByCandidateDto.class))
                .orElseThrow(
                ()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                }
        );
    }

    @Override
    public void updateTechnologyByCandidate(Long id, TechnologyByCandidateDto technologyByCandidateDto) {
        technologyByCandidateRepository.findById(id)
                .ifPresentOrElse(technologyFind -> {
                    technologyFind = modelMapper.map(technologyByCandidateDto, TechnologyByCandidate.class);
                    technologyByCandidateRepository.save(technologyFind);
                    log.info("Technology by Candidate created successfully");
                },()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public List<TechnologyByCandidateView> getCandidatesByTechnology(String technologyName) {
        return technologyByCandidateRepository.getCandidatesByTechnology(technologyName);
    }

}
