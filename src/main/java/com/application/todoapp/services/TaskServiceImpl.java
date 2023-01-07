package com.application.todoapp.services;

import com.application.todoapp.Exceptions.TaskNotFoundException;
import com.application.todoapp.data.models.Task;
import com.application.todoapp.data.repositories.TaskRepo;
import com.application.todoapp.dtos.requests.CreateTaskRequest;
import com.application.todoapp.dtos.requests.DeleteTaskRequest;
import com.application.todoapp.dtos.requests.RenameTaskRequest;
import com.application.todoapp.dtos.responses.CreateTaskResponse;
import com.application.todoapp.dtos.responses.DeleteTaskResponse;
import com.application.todoapp.dtos.responses.RenameTaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private CategoryService categoryService;
    @Override
    public CreateTaskResponse addTask(CreateTaskRequest createTaskRequest) {
        Task task = buildTask(createTaskRequest);
        Task savedTask = taskRepo.save(task);
        categoryService.addTask(createTaskRequest.getCategoryName(), savedTask);
        CreateTaskResponse response = taskResponse();
        return response;
    }

    @Override
    public RenameTaskResponse renameTask(RenameTaskRequest renameTaskRequest){
        Task foundTask = taskRepo.findByTaskId(renameTaskRequest.getTaskId());
        if (foundTask == null) throw new TaskNotFoundException("Task doesn't exist");
        foundTask.setTaskName(renameTaskRequest.getNewName());
        Task renamedTask = taskRepo.save(foundTask);
        categoryService.renameTaskInCategory(renameTaskRequest);
        RenameTaskResponse renameTaskResponse = renameResponse(renamedTask);
        return renameTaskResponse;
    }
    @Override
    public DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest){
        Task foundTask = taskRepo.findByTaskId(deleteTaskRequest.getTaskId());
        taskRepo.delete(foundTask);
        categoryService.deleteTask(deleteTaskRequest.getCategoryName(), foundTask);
        return getDeleteTaskResponse();
    }

    private DeleteTaskResponse getDeleteTaskResponse() {
        DeleteTaskResponse deleteTaskResponse = new DeleteTaskResponse();
        deleteTaskResponse.setMessage("Task deleted successfully");
        deleteTaskResponse.setStatusCode(201);
        return deleteTaskResponse;
    }

    private Task buildTask(CreateTaskRequest createTaskRequest){
        Task task = new Task();
        task.setTaskName(createTaskRequest.getName());
        return task;
    }
    private CreateTaskResponse taskResponse(){
        CreateTaskResponse response = new CreateTaskResponse();
        response.setStatusCode(201);
        response.setMessage("Task added successfully");
        return response;
    }
    private RenameTaskResponse renameResponse(Task task){
        RenameTaskResponse renameTaskResponse = new RenameTaskResponse();
        renameTaskResponse.setStatusCode(201);
        renameTaskResponse.setMessage(task.getTaskName() + " renamed successfully");
        return renameTaskResponse;
    }
}
