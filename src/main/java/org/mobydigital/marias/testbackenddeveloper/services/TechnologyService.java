package org.mobydigital.marias.testbackenddeveloper.services;

import org.mobydigital.marias.testbackenddeveloper.models.entities.Technology;

import java.util.List;

public interface TechnologyService {
    Technology createTechnology(Technology technology);
    List<Technology> findAll();
    void deleteTechnology(Long id);
    Technology getTechnologyById(Long id);
    void updateTechnology(Long id, Technology technology);
}
