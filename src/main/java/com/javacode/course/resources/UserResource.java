package com.javacode.course.resources;

import com.javacode.course.entities.User;
import com.javacode.course.services.IUserService;
import com.javacode.course.services.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users/")
@Resource
public class UserResource {

    @Autowired
    private IUserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = service.getAll();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        try {
            User user = service.getById(id);
            return ResponseEntity.ok().body(user);
        } catch (ResponseStatusException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = service.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(uri).body(createdUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> put(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = service.update(id, user);

        return ResponseEntity.ok().body(updatedUser);
    }
}
