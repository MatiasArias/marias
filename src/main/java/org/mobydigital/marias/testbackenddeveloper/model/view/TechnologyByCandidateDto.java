package org.mobydigital.marias.testbackenddeveloper.model.view;


import lombok.Data;


@Data
public class TechnologyByCandidateDto {
    private Long id;
    private Long idCandidate;
    private Long idTechnology;
    private Integer yearsOfExperience;
}
