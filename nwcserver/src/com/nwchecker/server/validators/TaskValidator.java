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

    private int taskIndex;
    private String titleRegex = "[0-9a-zA-Zа-яіїєА-ЯІЇЄ ]{0,}";

    public TaskValidator(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean failed = false;
        Task task = (Task) target;
        if (task.getTitle().length() == 0) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].title", "NotEmpty.title");
        }
        if (!task.getTitle().matches(titleRegex)) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].title", "Pattern.title");
        }
        if (task.getTitle().length() > 100) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].title", "Size.title");
        }
        if (task.getDescription().length() == 0) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].description", "NotEmpty.description");
        }
        if (task.getComplexity() > 100) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].complexity", "Max.complexity");
        }
        if (task.getComplexity() < 0) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].complexity", "Min.complexity");
        }
        if (task.getRate() > 100) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].rate", "Max.rate");
        }
        if (task.getRate() < 0) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].rate", "Min.rate");
        }
        if (task.getInputFileName().length() == 0) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].inputFileName", "NotEmpty.inputFileName");
        }
        if (task.getInputFileName().length() > 60) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].inputFileName", "Size.inputFileName");
        }
        if (task.getOutputFileName().length() == 0) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].outputFileName", "NotEmpty.outputFileName");
        }
        if (task.getOutputFileName().length() > 60) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].outputFileName", "Size.outputFileName");
        }
        if (task.getMemoryLimit() < 0) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].memoryLimit", "Min.memoryLimit");
        }
        if (task.getTimeLimit() < 0) {
            failed = true;
            errors.rejectValue("tasks[" + taskIndex + "].timeLimit", "Min.timeLimit");
        }
        if (task.getForumLink() != null) {
            if (task.getForumLink().length() > 500) {
                failed = true;
                errors.rejectValue("tasks[" + taskIndex + "].forumLink", "Size.forumLink");
            }
        }
        if (failed == true) {
            errors.rejectValue("tasks[" + taskIndex + "]", "Task.incorrect");
        }
    }

}
