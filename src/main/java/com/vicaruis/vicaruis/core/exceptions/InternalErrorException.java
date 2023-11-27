package com.vicaruis.vicaruis.core.exceptions;

import java.io.Serial;

public class InternalErrorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InternalErrorException(String message){
        super(message);
    }

}
