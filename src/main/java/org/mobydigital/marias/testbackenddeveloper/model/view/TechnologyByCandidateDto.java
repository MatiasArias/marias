package org.mobydigital.marias.testbackenddeveloper.model.view;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnologyByCandidateDto {
    private Long id;
    private Long idCandidate;
    private Long idTechnology;
    private Integer yearsOfExperience;
}
