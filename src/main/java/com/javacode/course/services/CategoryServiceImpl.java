package com.javacode.course.services;

import com.javacode.course.entities.Category;
import com.javacode.course.repositories.CategoryRepository;
import com.javacode.course.services.exceptions.DatabaseException;
import com.javacode.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return category.orElseThrow(() -> new ResourceNotFoundException(("Category not found or not exists.")));
    }

    @Override
    public Category create(Category category) {
        Category createdCategory = categoryRepository.save(category);

        return createdCategory;
    }

    @Override
    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Category not found or not exist.");
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException("This category has a product associated with it");
        }
    }

    @Override
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
