package com.application.todoapp.services;

import com.application.todoapp.dtos.requests.CreateTaskRequest;
import com.application.todoapp.dtos.requests.DeleteCategoryRequest;
import com.application.todoapp.dtos.requests.DeleteTaskRequest;
import com.application.todoapp.dtos.requests.RenameTaskRequest;
import com.application.todoapp.dtos.responses.CreateTaskResponse;
import com.application.todoapp.dtos.responses.DeleteTaskResponse;
import com.application.todoapp.dtos.responses.RenameTaskResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;
    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Test that a task can be created")
    void testAddTask() {

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();

        createTaskRequest.setCategoryName("Lifestyle");
        createTaskRequest.setName("Read the fucking manual!");
        CreateTaskResponse taskResponse = taskService.addTask(createTaskRequest);
        assertEquals(201, taskResponse.getStatusCode());

        CreateTaskRequest createTaskRequestTwo = new CreateTaskRequest();

        createTaskRequestTwo.setCategoryName("Lifestyle");
        createTaskRequestTwo.setName("Read the fucking manual!");
        taskService.addTask(createTaskRequest);
    }
    @Test
    @DisplayName("Test that a task can be renamed")
    void testRenameTask(){
        RenameTaskRequest renameTaskRequest = new RenameTaskRequest();
        renameTaskRequest.setCategoryName("Lifestyle");
        renameTaskRequest.setTaskId("63b536441cb1a00b1fb573b0");
        renameTaskRequest.setNewName("Yeah yeah. Read the fucking manual fucking again");
        RenameTaskResponse renameTaskResponse = taskService.renameTask(renameTaskRequest);
        assertEquals(201, renameTaskResponse.getStatusCode());
    }
    @Test
    @DisplayName("Test that a task can be deleted")
    void testDeleteTask(){
        DeleteTaskRequest deleteTaskRequest = new DeleteTaskRequest();
        deleteTaskRequest.setCategoryName("Lifestyle");
        deleteTaskRequest.setTaskId("63b536451cb1a00b1fb573b1");
        DeleteTaskResponse deleteTaskResponse = taskService.deleteTask(deleteTaskRequest);
        assertEquals(201, deleteTaskResponse.getStatusCode());
    }
}