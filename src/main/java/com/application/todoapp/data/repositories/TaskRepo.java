package com.application.todoapp.data.repositories;

import com.application.todoapp.data.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepo extends MongoRepository<Task, String> {
    Task findByTaskId(String taskId);
}