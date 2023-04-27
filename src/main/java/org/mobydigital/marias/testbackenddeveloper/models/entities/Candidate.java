package org.mobydigital.marias.testbackenddeveloper.models.entities;

import org.mobydigital.marias.testbackenddeveloper.models.enums.DocumentTypeEnum;

import java.util.Date;

public class Candidate {
    private long id;
    private String name;
    private String lastname;
    private DocumentTypeEnum documentType;
    private Integer documentNumber;
    private Date birthday;
}
