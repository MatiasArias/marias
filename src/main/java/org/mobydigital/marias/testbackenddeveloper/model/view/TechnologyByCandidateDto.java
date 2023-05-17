package org.mobydigital.marias.testbackenddeveloper.model.view;

import lombok.*;

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
