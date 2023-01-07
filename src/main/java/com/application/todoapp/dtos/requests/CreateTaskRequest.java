package com.application.todoapp.dtos.requests;

import lombok.Data;

@Data
public class CreateTaskRequest extends CreateRequest{
    private String categoryName;
}
