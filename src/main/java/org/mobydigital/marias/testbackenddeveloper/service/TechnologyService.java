package org.mobydigital.marias.testbackenddeveloper.service;

import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyDto;

import java.util.List;

public interface TechnologyService {
    TechnologyDto createTechnology(TechnologyDto technology);
    List<TechnologyDto> findAll();
    void deleteTechnology(Long id);
    TechnologyDto getTechnologyById(Long id);
    void updateTechnology(Long id, TechnologyDto technology);
}
