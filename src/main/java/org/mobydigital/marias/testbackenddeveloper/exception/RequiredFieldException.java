package org.mobydigital.marias.testbackenddeveloper.exception;
public class RequiredFieldException extends RuntimeException{
    public RequiredFieldException(String message) {
        super(message);
    }
}
