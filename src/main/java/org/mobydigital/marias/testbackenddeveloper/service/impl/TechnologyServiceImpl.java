package org.mobydigital.marias.testbackenddeveloper.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.model.entity.Technology;
import org.mobydigital.marias.testbackenddeveloper.repository.TechnologyRepository;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyService;
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
        log.info(String.format("Technology %s created successfully ",technology.getName()));
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
                    log.info("Technology deleted successfully");
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
                    if(technology.getName() != null && !technology.getName().isBlank()) technologyFind.setName(technology.getName());
                    if(technology.getVersion() != null && !technology.getVersion().isBlank()) technologyFind.setVersion(technology.getVersion());
                    technologyRepository.save(technologyFind);
                    log.info(String.format("Technology %s updated successfully ",technologyFind.getName()));
                },()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }
}
