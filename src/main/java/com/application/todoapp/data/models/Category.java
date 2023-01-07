package com.application.todoapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Category {
    @Id
    private String id;
    private String name;
    private String oldName;
    private List<Task> tasks = new ArrayList<>();
}
