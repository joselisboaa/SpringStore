package com.javacode.course.resources;

import com.javacode.course.entities.Order;
import com.javacode.course.services.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders/")
@Resource
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Order getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
