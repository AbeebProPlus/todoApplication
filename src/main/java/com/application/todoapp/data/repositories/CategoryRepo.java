package com.application.todoapp.data.repositories;

import com.application.todoapp.data.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category, String> {
    Category findByName(String name);
}
