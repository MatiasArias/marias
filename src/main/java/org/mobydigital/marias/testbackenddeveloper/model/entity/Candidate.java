package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.*;
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
    private String name;
    private String lastname;
    private DocumentTypeEnum documentType;
    private Integer documentNumber;
    private Date birthdate;
}
