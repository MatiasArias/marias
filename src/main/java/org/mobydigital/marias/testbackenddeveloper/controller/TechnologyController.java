package org.mobydigital.marias.testbackenddeveloper.controller;

import org.mobydigital.marias.testbackenddeveloper.model.entity.Technology;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;

    @GetMapping
    public ResponseEntity<List<Technology>> getTechnologies(){
        return new ResponseEntity<>(technologyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technology> getTechonologyById(@PathVariable("id") Long id){
        return new ResponseEntity<>(technologyService.getTechnologyById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Technology> saveTechnology(@RequestBody Technology technology){
        return new ResponseEntity<>(technologyService.createTechnology(technology),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTechnology(@RequestBody Technology technology, @PathVariable("id") Long id){
        technologyService.updateTechnology(id,technology);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTechnology(@PathVariable("id") Long id){
        technologyService.deleteTechnology(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
