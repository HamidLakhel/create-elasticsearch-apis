package com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document;

import com.vicaruis.vicaruis.elasticsearch.business.exceptions.IndexNameAlreadyExistsException;
import com.vicaruis.vicaruis.elasticsearch.business.exceptions.IndexNameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CreateDocumentErrorsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IndexNameNotFoundException.class)
    public Map<String, String> handleBusinessException(IndexNameAlreadyExistsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return errorMap;
    }
}
