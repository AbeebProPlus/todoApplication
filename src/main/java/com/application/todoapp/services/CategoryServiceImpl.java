package com.application.todoapp.services;

import com.application.todoapp.Exceptions.CategoryNotFoundException;
import com.application.todoapp.Exceptions.ExistingCategoryException;
import com.application.todoapp.data.models.Category;
import com.application.todoapp.data.models.Task;
import com.application.todoapp.data.repositories.CategoryRepo;
import com.application.todoapp.dtos.requests.CreateCategoryRequest;
import com.application.todoapp.dtos.requests.DeleteCategoryRequest;
import com.application.todoapp.dtos.requests.RenameCategoryRequest;
import com.application.todoapp.dtos.requests.RenameTaskRequest;
import com.application.todoapp.dtos.responses.CreateCategoryResponse;
import com.application.todoapp.dtos.responses.DeleteCategoryResponse;
import com.application.todoapp.dtos.responses.RenameCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest){
        Category newCategory = buildCategory(createCategoryRequest);
        Category savedCategory = categoryRepo.save(newCategory);
        CreateCategoryResponse response = buildCategoryResponse(savedCategory);
        return response;
    }

    @Override
    public RenameCategoryResponse renameCategory(RenameCategoryRequest renameCategoryRequest){
        Category foundCategory = categoryRepo.findByName(renameCategoryRequest.getOldName());
        foundCategory.setName(renameCategoryRequest.getNewName());
        Category renamedCategory = categoryRepo.save(foundCategory);
        RenameCategoryResponse renameCategoryResponse = buildRenamingResponse(renamedCategory);
        return renameCategoryResponse;
    }

    @Override
    public DeleteCategoryResponse deleteCategory(DeleteCategoryRequest deleteCategoryRequest){
        Category foundCategory = categoryRepo.findByName(deleteCategoryRequest.getName());
        categoryRepo.delete(foundCategory);
        DeleteCategoryResponse deleteCategoryResponse = categoryDeletion(foundCategory);
        return deleteCategoryResponse;
    }

    @Override
    public void addTask(String categoryName, Task task){
        Category foundCategory = categoryRepo.findByName(categoryName);
        if (foundCategory == null) throw new CategoryNotFoundException("Category doesn't exist");
        foundCategory.getTasks().add(task);
        categoryRepo.save(foundCategory);
    }
    @Override
    public List<Task> getCategoryTasks(String categoryName){
        Category foundCategory = categoryRepo.findByName(categoryName);
        return foundCategory.getTasks();
    }

    @Override
    public void deleteTask(String categoryName, Task task){
        Category foundCategory = categoryRepo.findByName(categoryName);
        foundCategory.getTasks().remove(task);
        categoryRepo.save(foundCategory);
    }
    @Override
    public void renameTaskInCategory(RenameTaskRequest renameTaskRequest){
        Category foundCategory = categoryRepo.findByName(renameTaskRequest.getCategoryName());
        List<Task> categoryTasks = foundCategory.getTasks();
        for (Task categoryTask : categoryTasks) {
            if (Objects.equals(categoryTask.getTaskId(), renameTaskRequest.getTaskId())) {
                System.out.println(categoryTask);
                categoryTask.setTaskName(renameTaskRequest.getNewName());
            }
        }
        categoryRepo.save(foundCategory);
    }

    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        categoryRepo.findAll().forEach(categories::add);
        return categories;
    }
    private Category buildCategory(CreateCategoryRequest createCategoryRequest){
        Category category = new Category();
        Category existingCategory = categoryRepo.findByName(createCategoryRequest.getName());
        if (existingCategory != null) throw new ExistingCategoryException("Category already exists");
        category.setName(createCategoryRequest.getName());
        return category;
    }

    private CreateCategoryResponse buildCategoryResponse(Category category){
        CreateCategoryResponse response = new CreateCategoryResponse();
        response.setStatusCode(201);
        response.setMessage(category.getName() + " created succesfully");
        response.setName(category.getName());
        return response;
    }
    private RenameCategoryResponse buildRenamingResponse(Category category){
        RenameCategoryResponse renameCategoryResponse = new RenameCategoryResponse();
        renameCategoryResponse.setStatusCode(201);
        renameCategoryResponse.setMessage(category.getName() + " renamed successfully");
        return renameCategoryResponse;
    }
    private DeleteCategoryResponse categoryDeletion(Category category){
        DeleteCategoryResponse deleteCategoryResponse = new DeleteCategoryResponse();
        deleteCategoryResponse.setStatusCode(201);
        deleteCategoryResponse.setMessage(category.getName() + " deleted successfully");
        return deleteCategoryResponse;
    }
}
