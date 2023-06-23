package com.javacode.course.services;

import com.javacode.course.entities.Product;
import com.javacode.course.repositories.ProductRepository;
import com.javacode.course.services.exceptions.DatabaseException;
import com.javacode.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> product = repository.findById(id);

        return product.orElseThrow(() -> new ResourceNotFoundException(("Product not found or not exists.")));
    }

    @Override
    public Product create(Product product) {
        Product createdProduct = repository.save(product);
        return createdProduct;
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Product not found or not exists.");
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException(error.getMessage());
        }
    }

    @Override
    public Product update(Long id, Product newProductData) {
        try {
            Product product = repository.getReferenceById(id);
            updateData(product, newProductData);

            Product updatedProduct = repository.save(product);

            return updatedProduct;
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Product not found or not exists.");
        }
    }

    private void updateData(Product oldProductData, Product newProductData) {
        oldProductData.setName(newProductData.getName());
        oldProductData.setDescription(newProductData.getDescription());
        oldProductData.setPrice(newProductData.getPrice());
        oldProductData.setImgUrl(newProductData.getImgUrl());
    }
}
