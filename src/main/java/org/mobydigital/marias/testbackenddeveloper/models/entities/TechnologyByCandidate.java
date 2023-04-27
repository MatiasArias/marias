package org.mobydigital.marias.testbackenddeveloper.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="technology_by_candidate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TechnologyByCandidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "id_candidate")
    private Candidate candidate;
    @ManyToOne()
    @JoinColumn(name = "id_technology")
    private Technology technology;
    private Integer yearsOfExperience;
}
