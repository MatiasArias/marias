package org.mobydigital.marias.testbackenddeveloper.repositories;

import org.mobydigital.marias.testbackenddeveloper.models.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
