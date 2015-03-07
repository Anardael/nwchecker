package com.nwchecker.server.exceptions;

/**
 * <h1>Contest Access Denied</h1>
 * Exception for Access Denied situation.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-21
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
