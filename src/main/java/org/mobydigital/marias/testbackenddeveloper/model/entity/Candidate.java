package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.mobydigital.marias.testbackenddeveloper.model.enums.DocumentTypeEnum;

import java.util.Date;
@Entity
@Table(name="candidate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidate")
    private long idCandidate;
    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(name = "lastname")
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @Column(name = "document_type")
    @NotNull(message = "Document type is mandatory")
    private DocumentTypeEnum documentType;
    @Column(name = "document_number")
    @NotBlank(message = "Document is mandatory")
    private Integer documentNumber;
    @Column(name = "birthdate")
    @NotBlank(message = "Birthdate is mandatory")
    private Date birthdate;
}
