/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.validators;

import com.nwchecker.server.model.Task;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TaskValidator implements Validator {

    private String titleRegex = "[0-9a-zA-Zа-яіїєА-ЯІЇЄ ]{0,}";

    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
        if (task.getTitle().length() == 0) {

            errors.rejectValue("title", "NotEmpty.title");
        }
        if (!task.getTitle().matches(titleRegex)) {

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
