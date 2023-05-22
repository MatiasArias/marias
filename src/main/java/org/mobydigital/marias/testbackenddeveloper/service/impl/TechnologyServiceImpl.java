package org.mobydigital.marias.testbackenddeveloper.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mobydigital.marias.testbackenddeveloper.model.entity.Technology;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyDto;
import org.mobydigital.marias.testbackenddeveloper.repository.TechnologyRepository;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    private final String ID_NOT_FOUND = "Technology not found -  id:";
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public TechnologyDto createTechnology(TechnologyDto technologyDto) {
        Technology technology = modelMapper.map(technologyDto,Technology.class);
        technologyRepository.save(technology);
        log.info(String.format("Technology %s created successfully ",technology.getName()));
        return modelMapper.map(technology,TechnologyDto.class);
    }

    @Override
    public List<TechnologyDto> findAll() {
        return technologyRepository.findAll().stream().map(
                technology ->{
                    TechnologyDto technologyDto = modelMapper.map(technology,TechnologyDto.class);
                    return technologyDto;
                }
        ).collect(Collectors.toList());
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
    public TechnologyDto getTechnologyById(Long id) {
        return technologyRepository.findById(id).map(technology -> modelMapper.map(technology,TechnologyDto.class))
                .orElseThrow(
                ()->{
                    log.error(ID_NOT_FOUND+id);
                    throw new EntityNotFoundException();
                }
        );
    }

    @Override
    public void updateTechnology(Long id, TechnologyDto technology) {
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
