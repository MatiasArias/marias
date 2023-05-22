package org.mobydigital.marias.testbackenddeveloper.service;

import org.mobydigital.marias.testbackenddeveloper.model.view.CandidateDto;

import java.util.List;

public interface CandidateService {
    CandidateDto createCandidate(CandidateDto candidate);
    List<CandidateDto> findAll();
    void deleteCandidate(Long id);
    CandidateDto getCandidateById(Long id);
    void updateCandidate(Long id, CandidateDto candidate);

}
