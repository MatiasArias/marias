package org.mobydigital.marias.testbackenddeveloper.exceptions;
public class RequiredFieldException extends RuntimeException{
    public RequiredFieldException(String message) {
        super(message);
    }
}
