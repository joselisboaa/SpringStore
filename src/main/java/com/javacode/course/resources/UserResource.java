package com.javacode.course.resources;

import com.javacode.course.entities.User;
import com.javacode.course.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/")
@Resource
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = service.getAll();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = service.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public void create(String name, String email, String number, String password) {
        service.create(name, email, number, password);
    }
}
