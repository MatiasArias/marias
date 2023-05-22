package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Data;
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
    @NotNull(message = "Document type is mandatory")
    private DocumentTypeEnum documentType;
    @NotBlank(message = "Document is mandatory")
    private Integer documentNumber;
    @NotBlank(message = "Birthdate is mandatory")
    private Date birthdate;
}
