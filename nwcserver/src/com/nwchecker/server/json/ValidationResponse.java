package com.nwchecker.server.json;

import java.util.List;

/**
 * <h1>Validation Response</h1>
 * JSON object that encapsulates data about some action result.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
public class ValidationResponse {

    private String status;
    private String result;
    private List<ErrorMessage> errorMessageList;

    private ValidationResponse() {
    }

    public static ValidationResponse createValidationResponse() {
        return new ValidationResponse();
    }

    public static ValidationResponse createValidationResponse(String status) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.status = status;
        validationResponse.result = "";
        validationResponse.errorMessageList = null;
        return validationResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ErrorMessage> getErrorMessageList() {
        return this.errorMessageList;
    }

    public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

}
