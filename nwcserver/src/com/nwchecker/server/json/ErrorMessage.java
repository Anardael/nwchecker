package com.nwchecker.server.json;

/**
 * <h1>Error Message</h1>
 * JSON object that encapsulates data about some Error.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
public class ErrorMessage {

    private String fieldName;
    private String message;

    private ErrorMessage() {
    }

    public static ErrorMessage createErrorMessage(String fieldName, String message) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.fieldName = fieldName;
        errorMessage.message = message;
        return errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

}
