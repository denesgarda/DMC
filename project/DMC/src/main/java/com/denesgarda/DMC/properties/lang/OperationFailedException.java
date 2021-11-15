package com.denesgarda.DMC.properties.lang;

public class OperationFailedException extends RuntimeException {
    public OperationFailedException(String errorMessage) {
        super(errorMessage);
    }
}
