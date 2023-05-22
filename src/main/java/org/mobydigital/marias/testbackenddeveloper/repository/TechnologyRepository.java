package org.mobydigital.marias.testbackenddeveloper.repository;

import org.mobydigital.marias.testbackenddeveloper.model.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology,Long> {
}
