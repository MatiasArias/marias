package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "Years of experience is mandatory")
    private Integer yearsOfExperience;
}
