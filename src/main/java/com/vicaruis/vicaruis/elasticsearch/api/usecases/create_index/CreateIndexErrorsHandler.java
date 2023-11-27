package com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import com.vicaruis.vicaruis.elasticsearch.business.exceptions.IndexNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CreateIndexErrorsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IndexNameAlreadyExistsException.class)
    public Map<String, String> handleBusinessException(IndexNameAlreadyExistsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ElasticsearchException.class)
    public Map<String, String> handleBusinessException(ElasticsearchException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return errorMap;
    }
}
