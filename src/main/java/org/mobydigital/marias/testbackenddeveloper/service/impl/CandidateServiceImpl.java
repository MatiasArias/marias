package org.mobydigital.marias.testbackenddeveloper.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.exception.InvalidDatatypeException;
import org.mobydigital.marias.testbackenddeveloper.exception.RequiredFieldException;
import org.mobydigital.marias.testbackenddeveloper.model.entity.Candidate;
import org.mobydigital.marias.testbackenddeveloper.model.view.CandidateDto;
import org.mobydigital.marias.testbackenddeveloper.repository.CandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final  String ID_NOT_FOUND = "Candidate not found -  id:";

    @Override
    public CandidateDto createCandidate(CandidateDto candidateDto) {
        Candidate candidate = modelMapper.map(candidateDto, Candidate.class);
        checkCreateCandidate(candidate);
        candidateRepository.save(candidate);
        candidateDto = modelMapper.map(candidate, CandidateDto.class);
        log.info(String.format("Candidate %s created successfully ",candidate.getName()));
        return candidateDto;
    }

    @Override
    public List<CandidateDto> findAll() {
        return candidateRepository.findAll().stream()
                .map(
                candidate -> modelMapper.map(candidate,CandidateDto.class))
                .toList();
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.findById(id)
                .ifPresentOrElse(candidateFind->{
                    candidateRepository.delete(candidateFind);
                    log.info("Candidate deleted successfully");
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public CandidateDto getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .map(candidate -> modelMapper.map(candidate, CandidateDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public void updateCandidate(Long id, CandidateDto candidate) {
        candidateRepository.findById(id)
                .ifPresentOrElse(candidateFind->{
                    if(candidate.getName() != null && !candidate.getName().isBlank()) candidateFind.setName(candidate.getName());
                    if(candidate.getLastname() != null && !candidate.getLastname().isBlank()) candidateFind.setLastname(candidate.getLastname());
                    if(candidate.getBirthdate() != null) candidateFind.setBirthdate(candidate.getBirthdate());
                    if(candidate.getDocumentType() != null ) candidateFind.setDocumentType(candidate.getDocumentType());
                    if(candidate.getDocumentNumber() != null && candidate.getDocumentNumber()>0)candidateFind.setDocumentNumber(candidate.getDocumentNumber());
            candidateRepository.save(candidateFind);
            log.info(String.format("Candidate %s updated successfully ",candidateFind.getName()));
        },()->{
            log.error(ID_NOT_FOUND+id);
            throw new EntityNotFoundException(ID_NOT_FOUND+id);
        });
    }

    private void checkCreateCandidate(Candidate candidate){
        if(candidate.getName() == null ||
                candidate.getLastname()==null ||
                candidate.getDocumentNumber()==null ||
                candidate.getDocumentType()==null ||
                candidate.getBirthdate()==null){
            log.error("Incomplete candidate data");
            throw new RequiredFieldException("Incomplete candidate data");
        }
        if(candidate.getBirthdate().after(new Date())){
            log.error("Invalid candidate birthday");
            throw new InvalidDatatypeException("Invalid candidate birthday");
        }
        if(candidate.getDocumentNumber()<0){
            log.error("Invalid candidate document number");
            throw new InvalidDatatypeException("Invalid candidate document number");
        }
        if(candidate.getIdCandidate()>0) {
            log.error("Invalid candidate id");
        }
    }
}
