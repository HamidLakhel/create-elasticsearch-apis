package com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id;

import com.vicaruis.vicaruis.elasticsearch.business.exceptions.DocumentIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CetDocumentByIdErrorsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DocumentIdNotFoundException.class)
    public Map<String, String> handleBusinessException(DocumentIdNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return errorMap;
    }
}
