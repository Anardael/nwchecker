package com.nwchecker.server.validators;

import com.nwchecker.server.model.Task;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Task Validator</h1>
 * Validator that used to validate data from Task Create modal form.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
@Component
public class TaskValidator implements Validator {

    private static final String TITLE_REGEX = "[0-9a-zA-Zа-яіїєА-ЯІЇЄ ,.'()-]{0,}";

    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
        try {
            task.setTitle(new String(task.getTitle().getBytes("iso-8859-1"), "UTF-8"));
            task.setDescription(new String(task.getDescription().getBytes("iso-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TaskValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (task.getTitle().length() == 0) {

            errors.rejectValue("title", "NotEmpty.title");
        }
        if (!task.getTitle().matches(TITLE_REGEX)) {

            errors.rejectValue("title", "Pattern.title");
        }
        if (task.getTitle().length() > 100) {

            errors.rejectValue("title", "Size.title");
        }
        if (task.getDescription().length() == 0) {

            errors.rejectValue("description", "NotEmpty.description");
        }
        if (task.getComplexity() > 100) {

            errors.rejectValue("complexity", "Max.complexity");
        }
        if (task.getComplexity() < 0) {

            errors.rejectValue("complexity", "Min.complexity");
        }
        if (task.getRate() > 100) {

            errors.rejectValue("rate", "Max.rate");
        }
        if (task.getRate() < 0) {

            errors.rejectValue("rate", "Min.rate");
        }
        if (task.getInputFileName().length() == 0) {

            errors.rejectValue("inputFileName", "NotEmpty.inputFileName");
        }
        if (task.getInputFileName().length() > 60) {

            errors.rejectValue("inputFileName", "Size.inputFileName");
        }
        if (task.getOutputFileName().length() == 0) {

            errors.rejectValue("outputFileName", "NotEmpty.outputFileName");
        }
        if (task.getOutputFileName().length() > 60) {

            errors.rejectValue("outputFileName", "Size.outputFileName");
        }
        if (task.getMemoryLimit() < 0) {

            errors.rejectValue("memoryLimit", "Min.memoryLimit");
        }
        if (task.getTimeLimit() < 0) {

            errors.rejectValue("timeLimit", "Min.timeLimit");
        }
        if (task.getForumLink() != null) {
            if (task.getForumLink().length() > 500) {

                errors.rejectValue("forumLink", "Size.forumLink");
            }
        }
    }

}
