package com.javacode.course.services;

import com.javacode.course.entities.Category;
import com.javacode.course.repositories.CategoryRepository;
import com.javacode.course.services.exceptions.DatabaseException;
import com.javacode.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService  {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return category.orElseThrow(() -> new ResourceNotFoundException(("Category not found or not exists.")));
    }

    public Category create(Category category) {
        Category createdCategory = categoryRepository.save(category);

        return createdCategory;
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Category not found or not exist.");
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException("This category has a product associated with it");
        }
    }

    public Category update(Long id, Category newCategory) {
        try {
            Category category = categoryRepository.getReferenceById(id);

            updateData(category, newCategory);
            Category updatedCategory = categoryRepository.save(category);

            return updatedCategory;
        } catch (EntityNotFoundException error) {
            throw new DatabaseException(error.getMessage());
        }
    }

    private void updateData(Category oldCategoryData, Category newCategoryData) {
        oldCategoryData.setName(newCategoryData.getName());
    }
}
