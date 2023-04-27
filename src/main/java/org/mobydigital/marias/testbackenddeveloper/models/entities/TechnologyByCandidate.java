package org.mobydigital.marias.testbackenddeveloper.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_candidate")
    private Candidate candidate;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_technology")
    private Technology technology;
    private Integer yearsOfExperience;
}
