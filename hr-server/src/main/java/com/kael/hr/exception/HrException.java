package com.kael.hr.exception;

public class HrException extends RuntimeException {

    public HrException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
