package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="technology_by_candidate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TechnologyByCandidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_technology_by_candidate")
    private Long id;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_candidate")
    @Column(name = "candidate")
    private Candidate candidate;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_technology")
    @Column(name = "technology")
    private Technology technology;
    @Column(name = "years_of_experience")
    @NotEmpty(message = "Years of experience is mandatory")
    private Integer yearsOfExperience;
}
