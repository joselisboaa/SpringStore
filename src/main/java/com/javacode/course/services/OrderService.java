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

    public Order create(Order order) {
        Order newOrder = orderRepository.save(order);

        return newOrder;
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Order update(Long id, Order newOrderData) {
        Order order = orderRepository.getReferenceById(id);

        updateData(order, newOrderData);
        orderRepository.save(order);

        return order;
    }

    public void updateData(Order oldOrderData, Order newOrderData) {
        oldOrderData.setMoment(newOrderData.getMoment());
        oldOrderData.setOrderStatus(newOrderData.getOrderStatus());
        oldOrderData.setPayment(newOrderData.getPayment());
    }
}
