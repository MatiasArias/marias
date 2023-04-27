package org.mobydigital.marias.testbackenddeveloper.models.views;

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
