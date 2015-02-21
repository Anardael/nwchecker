package com.nwchecker.server.exceptions;

/**
 * Created by Роман on 21.02.2015.
 */
public class ContestAccessDenied extends RuntimeException {

    public ContestAccessDenied(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
