package org.mobydigital.marias.testbackenddeveloper.exception.handler;

import org.mobydigital.marias.testbackenddeveloper.exception.InvalidDatatypeException;
import org.mobydigital.marias.testbackenddeveloper.exception.RequiredFieldException;
import org.mobydigital.marias.testbackenddeveloper.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidDatatypeException.class)
    public ResponseEntity<ResponseMessage> handleInvalidDatatypeException(InvalidDatatypeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.builder()
                        .message(exception.getMessage()).build());
    }
    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<ResponseMessage> handleRequiredFieldException(RequiredFieldException exception){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ResponseMessage.builder()
                        .message(exception.getMessage()).build());
    }
}
