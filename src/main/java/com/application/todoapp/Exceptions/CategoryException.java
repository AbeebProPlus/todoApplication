package com.application.todoapp.Exceptions;

public abstract class CategoryException extends RuntimeException{
    public CategoryException(String message){
        super(message);
    }
}
