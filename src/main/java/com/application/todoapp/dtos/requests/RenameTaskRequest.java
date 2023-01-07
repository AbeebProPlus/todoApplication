package com.application.todoapp.dtos.requests;

import lombok.Data;

@Data
public class RenameTaskRequest{
    private String taskId;
    private String newName;
    private String categoryName;
}
