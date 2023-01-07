package com.application.todoapp.services;

import com.application.todoapp.data.models.Category;
import com.application.todoapp.data.models.Task;
import com.application.todoapp.dtos.requests.CreateCategoryRequest;
import com.application.todoapp.dtos.requests.DeleteCategoryRequest;
import com.application.todoapp.dtos.requests.RenameCategoryRequest;
import com.application.todoapp.dtos.requests.RenameTaskRequest;
import com.application.todoapp.dtos.responses.CreateCategoryResponse;
import com.application.todoapp.dtos.responses.DeleteCategoryResponse;
import com.application.todoapp.dtos.responses.RenameCategoryResponse;

import java.util.List;

public interface CategoryService {
    CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
    RenameCategoryResponse renameCategory(RenameCategoryRequest renameCategoryRequest);
    DeleteCategoryResponse deleteCategory(DeleteCategoryRequest deleteCategoryRequest);
    void addTask(String categoryId, Task task);
    List<Task> getCategoryTasks(String categoryId);
    void deleteTask(String categoryId, Task task);
    void renameTaskInCategory(RenameTaskRequest renameTaskRequest);
    List<Category> getAllCategories();
}
