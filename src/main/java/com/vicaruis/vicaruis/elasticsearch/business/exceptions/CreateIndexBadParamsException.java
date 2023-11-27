package com.vicaruis.vicaruis.elasticsearch.business.exceptions;

import java.io.Serial;

public class CreateIndexBadParamsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CreateIndexBadParamsException(String message){
        super(message);
    }

    public CreateIndexBadParamsException() {
        super("Bad parameters for creating index");
    }

}
