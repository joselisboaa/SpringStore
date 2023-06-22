package com.javacode.course.services;

import com.javacode.course.entities.Order;
import com.javacode.course.repositories.OrderRepository;
import com.javacode.course.services.exceptions.DatabaseException;
import com.javacode.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

        return order.orElseThrow(() -> new ResourceNotFoundException(("Order not found or not exists.")));
    }

    public Order create(Order order) {
        Order createdOrder = orderRepository.save(order);

        return createdOrder;
    }

    public void delete(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Order not found or not exist.");
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException(error.getMessage());
        }
    }

    public Order update(Long id, Order newOrderData) {
        try {
            Order order = orderRepository.getReferenceById(id);

            updateData(order, newOrderData);
            Order updatedOrder = orderRepository.save(order);

            return updatedOrder;
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Order not found or not exist.");
        }
    }

    public void updateData(Order oldOrderData, Order newOrderData) {
        oldOrderData.setMoment(newOrderData.getMoment());
        oldOrderData.setOrderStatus(newOrderData.getOrderStatus());
        oldOrderData.setPayment(newOrderData.getPayment());
    }
}
