package com.application.todoapp.services;

import com.application.todoapp.dtos.requests.CreateTaskRequest;
import com.application.todoapp.dtos.requests.DeleteTaskRequest;
import com.application.todoapp.dtos.requests.RenameTaskRequest;
import com.application.todoapp.dtos.responses.CreateTaskResponse;
import com.application.todoapp.dtos.responses.DeleteTaskResponse;
import com.application.todoapp.dtos.responses.RenameTaskResponse;

public interface TaskService {
    CreateTaskResponse addTask(CreateTaskRequest createTaskRequest);
    RenameTaskResponse renameTask(RenameTaskRequest renameTaskRequest);
    DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest);
}
