package com.application.todoapp.dtos.responses;

import lombok.Data;

@Data
public class Response {
    private int statusCode;
    private String message;
    private String name;
}
