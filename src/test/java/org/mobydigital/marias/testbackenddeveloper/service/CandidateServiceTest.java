package org.mobydigital.marias.testbackenddeveloper.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mobydigital.marias.testbackenddeveloper.model.entity.Candidate;
import org.mobydigital.marias.testbackenddeveloper.model.enums.DocumentTypeEnum;
import org.mobydigital.marias.testbackenddeveloper.model.view.CandidateDto;
import org.mobydigital.marias.testbackenddeveloper.repository.CandidateRepository;
import org.mobydigital.marias.testbackenddeveloper.service.impl.CandidateServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {
    @Mock
    private CandidateRepository candidateRepository;
    @InjectMocks
    private CandidateServiceImpl candidateService;

    private CandidateDto candidateDto;
    private Candidate candidate;
    private final ModelMapper mapper = new ModelMapper();
    @BeforeEach
    public void setup() {

        candidateDto = new CandidateDto();
        candidateDto.setName("Matias");
        candidateDto.setLastname("Arias");
        candidateDto.setDocumentNumber(43132313);
        candidateDto.setDocumentType(DocumentTypeEnum.DNI);
        candidateDto.setBirthdate(new Date());

        candidate = mapper.map(candidateDto, Candidate.class);
    }

    @Test
    @DisplayName("CreateCandidate - Success")
    void testCreateCandidate() {
        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        CandidateDto createdCandidate = candidateService.createCandidate(candidateDto);

        verify(candidateRepository).save(any(Candidate.class));

        assertEquals(candidate.getName(), createdCandidate.getName());
    }

    @Test
    @DisplayName("FindAllCandidates - Success")
    void testFindAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate);
        when(candidateRepository.findAll()).thenReturn(candidates);

        List<CandidateDto> candidatesDto = candidateService.findAll();

        assertNotNull(candidatesDto);
        assertEquals(candidate.getName(),candidates.get(0).getName());
    }

    @Test
    @DisplayName("DeleteCandidate - Success")
    void testDeleteCandidate() {
        Long candidateId = 1L;
        Optional<Candidate> optionalCandidate = Optional.of(candidate);
        when(candidateRepository.findById(candidateId)).thenReturn(optionalCandidate);

        candidateService.deleteCandidate(candidateId);

        verify(candidateRepository, times(1)).delete(candidate);
    }

    @Test
    @DisplayName("GetCandidateById - Success")
    void testGetCandidateById() {
        Long candidateId = 1L;
        Optional<Candidate> optionalCandidate = Optional.of(candidate);
        when(candidateRepository.findById(candidateId)).thenReturn(optionalCandidate);

        CandidateDto candidateResult = candidateService.getCandidateById(candidateId);

        assertNotNull(candidateResult);
        assertEquals(candidateDto.getName(), candidateResult.getName());
        assertEquals(candidateDto.getLastname(), candidateResult.getLastname());
    }

    @Test
    @DisplayName("GetCandidateById - Candidate not found")
    void testGetCandidateByIdNotFound() {
        Long candidateId = 1L;
        Optional<Candidate> optionalCandidate = Optional.empty();
        when(candidateRepository.findById(candidateId)).thenReturn(optionalCandidate);

        assertThrows(EntityNotFoundException.class, () -> candidateService.getCandidateById(candidateId));
    }

    @Test
    @DisplayName("UpdateCandidate - Success")
    void testUpdateCandidate() {
        Long candidateId = 1L;
        Optional<Candidate> optionalCandidate = Optional.of(candidate);
        when(candidateRepository.findById(candidateId)).thenReturn(optionalCandidate);
        when(candidateRepository.save(candidate)).thenReturn(candidate);

        candidateService.updateCandidate(candidateId, candidateDto);

        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    @DisplayName("Update Candidate - Candidate not found")
    void testUpdateCandidateNotFound() {
        Long candidateId = 1L;
        Optional<Candidate> optionalCandidate = Optional.empty();
        when(candidateRepository.findById(candidateId)).thenReturn(optionalCandidate);

        assertThrows(EntityNotFoundException.class, () -> candidateService.updateCandidate(candidateId, candidateDto));
    }
}
