package com.nwchecker.server.handlers;

import com.nwchecker.server.exceptions.ContestAccessDenied;
import com.nwchecker.server.json.ErrorMessage;
import com.nwchecker.server.json.ValidationResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

/**
 * <h1>Global Exceptions Handler</h1>
 * Exception handler that used to handle global system exceptions.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionsHandler {

    private static final Logger LOG
            = Logger.getLogger(GlobalExceptionsHandler.class);

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public
    @ResponseBody
    ValidationResponse handleMaxUploadException(MaxUploadSizeExceededException e,
                                                HttpServletRequest request, HttpServletResponse response) {
        ValidationResponse validationResponse = ValidationResponse.createValidationResponse();
        validationResponse.setStatus("FAIL");
        LinkedList<ErrorMessage> errors = new LinkedList<ErrorMessage>();
        errors.add(ErrorMessage.createErrorMessage("uploadSize", null));
        validationResponse.setErrorMessageList(errors);
        return validationResponse;
    }

    @ExceptionHandler(ContestAccessDenied.class)
    @ResponseBody
    public ValidationResponse handleAccessDenied(ContestAccessDenied contestAccessDenied) {
        LOG.warn(contestAccessDenied.getMessage());
        ValidationResponse jsonResult = ValidationResponse.createValidationResponse();
        jsonResult.setStatus("FAIL");
        LinkedList<ErrorMessage> linkedList = new LinkedList<ErrorMessage>();
        ErrorMessage errorMessage =
                ErrorMessage.createErrorMessage("denied", "You have no permissions for this action.");
        linkedList.add(errorMessage);
        jsonResult.setErrorMessageList(linkedList);
        return jsonResult;
    }
}
