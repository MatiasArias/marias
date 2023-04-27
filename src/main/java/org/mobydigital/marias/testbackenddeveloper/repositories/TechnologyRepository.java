package org.mobydigital.marias.testbackenddeveloper.repositories;

import org.mobydigital.marias.testbackenddeveloper.models.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology,Long> {
}
