package com.application.todoapp.controller;

import com.application.todoapp.dtos.requests.CreateTaskRequest;
import com.application.todoapp.dtos.requests.DeleteTaskRequest;
import com.application.todoapp.dtos.requests.RenameTaskRequest;
import com.application.todoapp.dtos.responses.CreateTaskResponse;
import com.application.todoapp.dtos.responses.DeleteTaskResponse;
import com.application.todoapp.dtos.responses.RenameTaskResponse;
import com.application.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/add-task")
    public CreateTaskResponse createCategory(@RequestBody CreateTaskRequest createTaskRequest){
        return taskService.addTask(createTaskRequest);
    }
    @DeleteMapping("/delete-task")
    public DeleteTaskResponse deleteTask(@RequestBody DeleteTaskRequest deleteTaskRequest){
        return taskService.deleteTask(deleteTaskRequest);
    }
    @PatchMapping("/rename-task")
    public RenameTaskResponse renameTask(@RequestBody RenameTaskRequest renameTaskRequest){
        return taskService.renameTask(renameTaskRequest);
    }
}
