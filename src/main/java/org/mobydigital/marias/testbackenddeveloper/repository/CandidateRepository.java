package org.mobydigital.marias.testbackenddeveloper.repository;

import org.mobydigital.marias.testbackenddeveloper.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
