package com.javacode.course.services;

import com.javacode.course.entities.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Category getById(Long id);
    Category create(Category category);
    Category update(Long id, Category category);
    void delete(Long id);
}
