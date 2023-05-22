package org.mobydigital.marias.testbackenddeveloper.model.view;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TechnologyByCandidateDto {
    private Long idCandidate;
    private Long idTechnology;
    private Integer yearsOfExperience;
}
