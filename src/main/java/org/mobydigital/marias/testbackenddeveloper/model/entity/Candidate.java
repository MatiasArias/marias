package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.mobydigital.marias.testbackenddeveloper.model.enums.DocumentTypeEnum;

import java.util.Date;
@Entity
@Table(name="candidate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCandidate;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @NotBlank(message = "Email is mandatory")
    private DocumentTypeEnum documentType;
    @NotBlank(message = "Document is mandatory")
    private Integer documentNumber;
    @NotBlank(message = "Birthdate is mandatory")
    private Date birthdate;
}
