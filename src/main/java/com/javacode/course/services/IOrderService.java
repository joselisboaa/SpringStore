package com.javacode.course.services;

import com.javacode.course.entities.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    Order getById(Long id);
    Order create(Order order);
    Order update(Long id, Order order);
    void delete(Long id);
}
