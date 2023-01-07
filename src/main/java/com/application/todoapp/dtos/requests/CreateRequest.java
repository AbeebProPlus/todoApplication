package com.application.todoapp.dtos.requests;

import lombok.Data;

@Data
public abstract class CreateRequest {
    private String name;
}