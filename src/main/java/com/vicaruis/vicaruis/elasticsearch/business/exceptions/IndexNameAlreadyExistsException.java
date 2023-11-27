package com.vicaruis.vicaruis.elasticsearch.business.exceptions;

import java.io.Serial;

public class IndexNameAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public IndexNameAlreadyExistsException(String message){
        super(message);
    }

    public IndexNameAlreadyExistsException() {
        super("Index name already exists");
    }

}
