package com.javacode.course.services;

import com.javacode.course.entities.Order;
import com.javacode.course.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        return order.get();
    }
}
