package com.express.todo.todostuffs.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.ArrayList;
import java.util.List;

public class ToDoValidationError {
    @JsonInclude(Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    private final String errorMessage;

    public ToDoValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public void addValidationError(String error) {
        errors.add(error);
    }
    public List<String> getErrors() {
        return errors;
    }
    public String getErrorMessage() {
        return  errorMessage;
    }
}
