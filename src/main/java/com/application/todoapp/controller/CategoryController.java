package com.application.todoapp.controller;

import com.application.todoapp.data.models.Category;
import com.application.todoapp.dtos.requests.CreateCategoryRequest;
import com.application.todoapp.dtos.requests.DeleteCategoryRequest;
import com.application.todoapp.dtos.requests.RenameCategoryRequest;
import com.application.todoapp.dtos.responses.CreateCategoryResponse;
import com.application.todoapp.dtos.responses.DeleteCategoryResponse;
import com.application.todoapp.dtos.responses.RenameCategoryResponse;
import com.application.todoapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create-category")
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.createCategory(createCategoryRequest);
    }
    @GetMapping("/get-categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @DeleteMapping("/delete-category")
    public DeleteCategoryResponse deleteCategory(@RequestBody DeleteCategoryRequest deleteCategoryRequest){
        return categoryService.deleteCategory(deleteCategoryRequest);
    }
    @PatchMapping("/edit-category")
    public RenameCategoryResponse renameCategoryResponse(@RequestBody RenameCategoryRequest renameCategoryRequest){
        return categoryService.renameCategory(renameCategoryRequest);
    }
}
