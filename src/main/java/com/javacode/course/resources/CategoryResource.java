package com.javacode.course.resources;

import com.javacode.course.entities.Category;
import com.javacode.course.services.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Resource
@RestController
@RequestMapping("/categories/")
public class CategoryResource {
    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getById(id);
    }
}

