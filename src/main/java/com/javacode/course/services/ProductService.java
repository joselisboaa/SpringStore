package com.javacode.course.services;

import com.javacode.course.entities.Product;
import com.javacode.course.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        Optional<Product> product = repository.findById(id);

        return product.get();
    }

    public Product create(Product product) {
        Product createdProduct = repository.save(product);
        return createdProduct;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Product update(Long id, Product newProductData) {
        Product product = repository.getReferenceById(id);
        updateData(product, newProductData);

        Product updatedProduct = repository.save(product);

        return updatedProduct;
    }

    private void updateData(Product oldProductData, Product newProductData) {
        oldProductData.setName(newProductData.getName());
        oldProductData.setDescription(newProductData.getDescription());
        oldProductData.setPrice(newProductData.getPrice());
        oldProductData.setImgUrl(newProductData.getImgUrl());
    }
}
