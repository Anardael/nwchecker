package com.nwchecker.server.validators;

import com.nwchecker.server.model.Contest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;

/**
 * <h1>Contest Validator</h1>
 * Validator that used to validate data from Contest Create page.
 * <p/>
 *
 * @author Roman Zayats
 * @version 1.0
 */
@Component
public class ContestValidator implements Validator {

    private static final String TITLE_REGEX = "[0-9a-zA-Zа-яіїєА-ЯІЇЄ ,.'()-]{0,}";

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
        if (!c.getTitle().matches(TITLE_REGEX)) {
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
        if (c.getStarts()!= null && c.getStarts().getTime() < (System.currentTimeMillis())) {
            errors.rejectValue("starts", "Contest.startTime.less");
        }
        else  if (c.getStarts()== null) {
//        	errors.rejectValue("starts", "Contest.startTime.default");
            c.setStarts(Calendar.getInstance().getTime());           
        }
        
        if (errors.hasFieldErrors("duration")) {
            errors.rejectValue("duration", "Pattern.duration");
        }
    }

}
