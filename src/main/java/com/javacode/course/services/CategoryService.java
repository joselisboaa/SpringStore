package com.javacode.course.services;

import com.javacode.course.entities.Category;
import com.javacode.course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService  {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Long id) {
        Optional<Category> category = repository.findById(id);

        return category.get();
    }

    public Category create(Category category) {
        Category newCategory = repository.save(category);

        return newCategory;
    }
}
