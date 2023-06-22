package com.javacode.course.services;

import com.javacode.course.entities.OrderItem;
import com.javacode.course.repositories.OrderItemRepository;

import com.javacode.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository repository;

    public List<OrderItem> getAll() {
        return repository.findAll();
    }

    public OrderItem getById(Long id) {
        Optional<OrderItem> orderItem = repository.findById(id);
        return orderItem.orElseThrow(() -> new ResourceNotFoundException(("Order item not found or not exists")));
    }
}
