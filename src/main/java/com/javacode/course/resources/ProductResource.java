package com.javacode.course.resources;

import com.javacode.course.entities.Product;
import com.javacode.course.services.ProductService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/")
@Resource
public class ProductResource {
    @Autowired
    ProductService service;

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
