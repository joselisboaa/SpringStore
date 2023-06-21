package com.javacode.course.resources;

import com.javacode.course.entities.OrderItem;
import com.javacode.course.services.OrderItemService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Resource
@RequestMapping(value = "/order-items/")
public class OrderItemResource {
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAll() {
        List<OrderItem> orderItems = orderItemService.getAll();
        return ResponseEntity.ok().body(orderItems);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.getById(id);
        return ResponseEntity.ok().body(orderItem);
    }
}
