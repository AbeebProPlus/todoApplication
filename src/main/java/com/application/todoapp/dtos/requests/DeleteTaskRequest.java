package com.application.todoapp.dtos.requests;

import lombok.Data;

@Data
public class DeleteTaskRequest {
    private String taskId;
    private String categoryName;
}
