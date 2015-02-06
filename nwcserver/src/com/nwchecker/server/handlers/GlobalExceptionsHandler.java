/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.handlers;

import com.nwchecker.server.json.ErrorMessage;
import com.nwchecker.server.json.ValidationResponse;
import java.util.LinkedList;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Роман
 */
@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public @ResponseBody
    ValidationResponse handleMaxUploadException(MaxUploadSizeExceededException e,
            HttpServletRequest request, HttpServletResponse response) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setStatus("FAIL");
        LinkedList<ErrorMessage> errors = new LinkedList<ErrorMessage>();
        errors.add(new ErrorMessage("uploadSize", null));
        validationResponse.setErrorMessageList(errors);

        return validationResponse;
    }
}
