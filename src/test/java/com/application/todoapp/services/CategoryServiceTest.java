package com.application.todoapp.services;

import com.application.todoapp.Exceptions.ExistingCategoryException;
import com.application.todoapp.data.models.Category;
import com.application.todoapp.data.models.Task;
import com.application.todoapp.dtos.requests.CreateCategoryRequest;
import com.application.todoapp.dtos.requests.DeleteCategoryRequest;
import com.application.todoapp.dtos.requests.RenameCategoryRequest;
import com.application.todoapp.dtos.responses.CreateCategoryResponse;
import com.application.todoapp.dtos.responses.DeleteCategoryResponse;
import com.application.todoapp.dtos.responses.RenameCategoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryServiceImpl categoryService;
    private CreateCategoryRequest createCategoryRequest;
    private CreateCategoryRequest createCategoryRequestTwo;
    @BeforeEach
    void setUp() {
        createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequestTwo = new CreateCategoryRequest();
        createCategoryRequestTwo.setName("Lifestyle");
        createCategoryRequest.setName("Sports");

    }

    @Test
    @DisplayName("Test that category can be created")
    void testCreateCategory() {
        CreateCategoryResponse response = categoryService.createCategory(createCategoryRequest);
        assertEquals(201, response.getStatusCode());
        CreateCategoryResponse responseTwo = categoryService.createCategory(createCategoryRequestTwo);
        assertEquals(201, responseTwo.getStatusCode());
    }
    @Test
    @DisplayName("Test that category cannot be duplicated and exception is thrown")
    void testCategoryNameDuplication(){
        assertThrows(ExistingCategoryException.class, ()->categoryService.createCategory(createCategoryRequest));
    }
    @Test
    @DisplayName("Test that category can be renamed")
    void testRenameCategory(){
        RenameCategoryRequest renameCategoryRequest = new RenameCategoryRequest();
        renameCategoryRequest.setOldName("Sports");
        renameCategoryRequest.setNewName("Exercise");
        RenameCategoryResponse response = categoryService.renameCategory(renameCategoryRequest);
        assertEquals(201, response.getStatusCode());
    }
    @Test
    @DisplayName("Test that category can be deleted")
    void testDeleteCategory(){
        DeleteCategoryRequest deleteCategoryRequest = new DeleteCategoryRequest();
        deleteCategoryRequest.setName("Exercise");
        DeleteCategoryResponse deleteCategoryResponse = categoryService.deleteCategory(deleteCategoryRequest);
        assertEquals(201, deleteCategoryResponse.getStatusCode());
    }
    @Test
    @DisplayName("Test that tasks in a category can be gotten")
    void testGetCategoryTasks(){
        List<Task> tasks = categoryService.getCategoryTasks("Lifestyle");
        assertEquals("Yeah yeah. Read the fucking manual fucking again", tasks.get(0).getTaskName());
    }
    @Test
    void testGetAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        assertEquals(2, categories.size());
    }
}