package com.application.todoapp.Exceptions;

public class TaskNotFoundException extends CategoryException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
