package org.mobydigital.marias.testbackenddeveloper.repository;

import org.mobydigital.marias.testbackenddeveloper.model.entity.TechnologyByCandidate;
import org.mobydigital.marias.testbackenddeveloper.model.projection.TechnologyByCandidateView;
import org.mobydigital.marias.testbackenddeveloper.model.projection.CandidateView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TechnologyByCandidateRepository extends JpaRepository<TechnologyByCandidate,Long> {
    @Query("SELECT tc.candidate AS candidate, tc.yearsOfExperience as yearsOfExperience " +
            "FROM TechnologyByCandidate tc " +
            "INNER JOIN tc.candidate " +
            "INNER JOIN tc.technology t " +
            "WHERE t.name = :technologyName")
    List<TechnologyByCandidateView> getCandidatesByTechnology(@Param("technologyName") String technologyName);

}
