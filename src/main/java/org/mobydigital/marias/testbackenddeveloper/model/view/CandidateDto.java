package org.mobydigital.marias.testbackenddeveloper.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mobydigital.marias.testbackenddeveloper.model.enums.DocumentTypeEnum;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDto {

    private String name;
    private String lastname;
    private DocumentTypeEnum documentType;
    private Integer documentNumber;
    private Date birthdate;
}
