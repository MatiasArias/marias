package org.mobydigital.marias.testbackenddeveloper.models.views;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TecnologyByCandidateDto {
    private Long id_candidate;
    private Long id_technology;
    private Integer yearsOfExperience;
}
