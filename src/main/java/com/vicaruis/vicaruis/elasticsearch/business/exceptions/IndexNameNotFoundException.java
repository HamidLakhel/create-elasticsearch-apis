package com.vicaruis.vicaruis.elasticsearch.business.exceptions;

import java.io.Serial;

public class IndexNameNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public IndexNameNotFoundException(String message){
        super(message);
    }

    public IndexNameNotFoundException() {
        super("Index name not found");
    }

}
