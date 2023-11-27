package com.vicaruis.vicaruis.elasticsearch.business.exceptions;

import java.io.Serial;

    public class DocumentIdNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DocumentIdNotFoundException(String message){
        super(message);
    }

    public DocumentIdNotFoundException() {
        super("Document id not found");
    }

}
