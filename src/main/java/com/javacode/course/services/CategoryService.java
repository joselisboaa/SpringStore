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
        Category createdCategory = repository.save(category);

        return createdCategory;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Category update(Long id, Category newCategory) {
        Category category = repository.getReferenceById(id);

        updateData(category, newCategory);
        Category updatedCategory = repository.save(category);

        return updatedCategory;
    }

    private void updateData(Category oldCategoryData, Category newCategoryData) {
        oldCategoryData.setName(newCategoryData.getName());
    }
}
