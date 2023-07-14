package com.javacode.course.resources;

import com.javacode.course.dto.UserDTO;
import com.javacode.course.entities.User;
import com.javacode.course.services.IUserService;
import com.javacode.course.services.UserServiceImpl;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users/")
@Resource
public class UserResource {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> userList = service.getAll();
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        try {
            User user = service.getById(id);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);

            return ResponseEntity.ok().body(userDTO);
        } catch (ResponseStatusException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody User user) {
        User createdUser = service.create(user);

        UserDTO userDTO = modelMapper.map(createdUser, UserDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(uri).body(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = service.update(id, user);
        UserDTO userDTO = modelMapper.map(updatedUser, UserDTO.class);

        return ResponseEntity.ok().body(userDTO);
    }
}
