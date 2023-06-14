package com.javacode.course.resources;

import com.javacode.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> getAll() {
        User u = new User("João", "gmail", "558599999999", "joao123");

        return ResponseEntity.ok().body(u);
    }
}
