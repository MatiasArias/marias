package org.mobydigital.marias.testbackenddeveloper.repositories;

import org.mobydigital.marias.testbackenddeveloper.models.entities.TechnologyByCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyByCandidateRepository extends JpaRepository<TechnologyByCandidate,Long> {

}
