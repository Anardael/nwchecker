/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.validators;

import com.nwchecker.server.model.Contest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Роман
 */
@Component
public class ContestValidator implements Validator {

    private String titleRegex = "[0-9a-zA-Zа-яіїєА-ЯІЇЄ ,.'()-]{0,}";

    @Override
    public boolean supports(Class<?> clazz) {
        return Contest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contest c = (Contest) target;
        //Contest validation:
        if (c.getTitle().length() == 0) {
            errors.rejectValue("title", "NotEmpty.title");
        }
        if (!c.getTitle().matches(titleRegex)) {
            errors.rejectValue("title", "Pattern.title");
        }
        if (c.getTitle().length() > 100) {
            errors.rejectValue("title", "Size.title");
        }
        if (c.getDescription().length() == 0) {
            errors.rejectValue("description", "NotEmpty.description");
        }
        //starts validation:
        if (errors.hasFieldErrors("starts")) {
            errors.rejectValue("starts", "Pattern.starts");
        }
        if (errors.hasFieldErrors("duration")) {
            errors.rejectValue("duration", "Pattern.duration");
        }
    }

}
