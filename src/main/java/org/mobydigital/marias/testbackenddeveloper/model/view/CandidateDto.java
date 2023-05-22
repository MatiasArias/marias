package org.mobydigital.marias.testbackenddeveloper.model.view;

import lombok.Data;
import org.mobydigital.marias.testbackenddeveloper.model.enums.DocumentTypeEnum;

import java.util.Date;
@Data
public class CandidateDto {

    private String name;
    private String lastname;
    private DocumentTypeEnum documentType;
    private Integer documentNumber;
    private Date birthdate;
}
