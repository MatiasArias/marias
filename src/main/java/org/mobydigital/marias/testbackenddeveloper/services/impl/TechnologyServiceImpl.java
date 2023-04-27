package org.mobydigital.marias.testbackenddeveloper.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.models.entities.Technology;
import org.mobydigital.marias.testbackenddeveloper.repositories.TechnologyRepository;
import org.mobydigital.marias.testbackenddeveloper.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    private final String ID_NOT_FOUND = "Technology not found -  id:";
    @Override
    public Technology createTechnology(Technology technology) {
        technologyRepository.save(technology);
        return technology;
    }

    @Override
    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }

    @Override
    public void deleteTechnology(Long id) {
        technologyRepository.findById(id)
                .ifPresentOrElse(technologyFind->{
                    technologyRepository.delete(technologyFind);
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public Technology getTechnologyById(Long id) {
        return technologyRepository.findById(id).orElseThrow(
                ()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException();
                }
        );
    }

    @Override
    public void updateTechnology(Long id, Technology technology) {
        technologyRepository.findById(id)
                .ifPresentOrElse(technologyFind -> {
                    technologyRepository.save(technologyFind);
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }
}
