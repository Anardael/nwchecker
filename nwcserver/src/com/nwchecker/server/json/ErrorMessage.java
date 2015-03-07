package com.nwchecker.server.json;

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
