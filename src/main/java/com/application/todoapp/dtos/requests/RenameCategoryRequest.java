package com.application.todoapp.dtos.requests;

import lombok.Data;

@Data
public class RenameCategoryRequest{
    private String oldName;
    private String newName;
}
