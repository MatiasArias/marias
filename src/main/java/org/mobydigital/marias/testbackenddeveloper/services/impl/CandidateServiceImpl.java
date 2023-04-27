package org.mobydigital.marias.testbackenddeveloper.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.models.entities.Candidate;
import org.mobydigital.marias.testbackenddeveloper.repositories.CandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.sql.SQLOutput;
import java.util.List;
@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    private final String ID_NOT_FOUND = "Candidate not found -  id:";

    @Override
    public Candidate createCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
        return candidate;
    }

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.findById(id)
                .ifPresentOrElse(candidateFind->{
                    candidateRepository.delete(candidateFind);
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(
                        ()->{
                        log.error(ID_NOT_FOUND+id);
                        throw new EntityNotFoundException(ID_NOT_FOUND + id);
                        }
                );
    }

    @Override
    public void updateCandidate(Long id, Candidate candidate) {
        candidateRepository.findById(id)
                .ifPresentOrElse(candidateFind->{
                    if(candidate.getName() != null && !candidate.getName().isBlank()) candidateFind.setName(candidate.getName());
                    if(candidate.getLastname() != null && !candidate.getLastname().isBlank()) candidateFind.setLastname(candidate.getLastname());
                    if(candidate.getBirthday() != null) candidateFind.setBirthday(candidate.getBirthday());
                    if(candidate.getDocumentType() != null ) candidateFind.setDocumentType(candidate.getDocumentType());
                    if(candidate.getDocumentNumber() != null && candidate.getDocumentNumber()>0)candidateFind.setDocumentNumber(candidate.getDocumentNumber());
            candidateRepository.save(candidateFind);
        },()->{
            log.error(ID_NOT_FOUND+id);
            throw new EntityNotFoundException(ID_NOT_FOUND+id);
        });
    }
}
