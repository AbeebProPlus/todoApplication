package com.application.todoapp.Exceptions;

public class ExistingCategoryException extends CategoryException{
    public ExistingCategoryException(String message) {
        super(message);
    }
}
