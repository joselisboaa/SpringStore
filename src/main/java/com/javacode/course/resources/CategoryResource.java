package com.javacode.course.resources;

import com.javacode.course.entities.Category;
import com.javacode.course.services.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Resource
@RestController
@RequestMapping("/categories/")
public class CategoryResource {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category = service.getById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category newCategory = service.create(category);

        return ResponseEntity.ok().body(newCategory);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

