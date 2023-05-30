package org.mobydigital.marias.testbackenddeveloper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mobydigital.marias.testbackenddeveloper.model.view.TechnologyDto;
import org.mobydigital.marias.testbackenddeveloper.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/technology")
@Tag(name="Technology controller")
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<TechnologyDto>> getTechnologies(){
        return new ResponseEntity<>(technologyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "getTechnologyById")
    public ResponseEntity<TechnologyDto> getTechonologyById(@PathVariable("id") Long id){
        return new ResponseEntity<>(technologyService.getTechnologyById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createTechnology")
    public ResponseEntity<TechnologyDto> saveTechnology(@RequestBody TechnologyDto technology){
        return new ResponseEntity<>(technologyService.createTechnology(technology),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateTechnology")
    public ResponseEntity<HttpStatus> updateTechnology(@RequestBody TechnologyDto technology, @PathVariable("id") Long id){
        technologyService.updateTechnology(id,technology);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteTechnology")
    public ResponseEntity<HttpStatus> deleteTechnology(@PathVariable("id") Long id){
        technologyService.deleteTechnology(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
