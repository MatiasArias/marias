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


@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final  String ID_NOT_FOUND = "Candidate not found -  id:";
    private static final  String INVALID_BIRTHDAY = "Invalid candidate birthday";
    private static final  String INVALID_DOCUMENT = "Invalid candidate document number";
    private static final  String INCOMPLETE_DATA = "Incomplete candidate data";

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
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public CandidateDto getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .map(candidate -> modelMapper.map(candidate, CandidateDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public void updateCandidate(Long id, CandidateDto candidate) {
        candidateRepository.findById(id)
                .ifPresentOrElse(candidateFind->{
            candidateRepository.save(modelMapper.map(candidateFind, Candidate.class));
            log.info(String.format("Candidate %s updated successfully ",candidateFind.getName()));
        },()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
        });
    }

    private void checkCreateCandidate(Candidate candidate){
        if(candidate.getName() == null ||
                candidate.getLastname()==null ||
                candidate.getDocumentNumber()==null ||
                candidate.getDocumentType()==null ||
                candidate.getBirthdate()==null){
            log.error(INCOMPLETE_DATA, new RequiredFieldException(INCOMPLETE_DATA));
            throw new RequiredFieldException(INCOMPLETE_DATA);
        }
        if(candidate.getBirthdate().after(new Date())){
            log.error(INVALID_BIRTHDAY, new InvalidDatatypeException(INVALID_BIRTHDAY));
            throw new InvalidDatatypeException(INVALID_BIRTHDAY);
        }
        if(candidate.getDocumentNumber()<0){
            log.error(INVALID_DOCUMENT, new InvalidDatatypeException(INVALID_DOCUMENT));
            throw new InvalidDatatypeException(INVALID_DOCUMENT);
        }
        if(candidate.getIdCandidate()>0) {
            log.error("Invalid candidate id");
        }
    }
}
