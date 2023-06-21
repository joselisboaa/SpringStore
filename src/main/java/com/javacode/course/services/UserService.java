package com.javacode.course.services;

import com.javacode.course.entities.User;
import com.javacode.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.get();
    }

    public User create(User user) {
        User createdUser = userRepository.save(user);

        return createdUser;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id, User user) {
        User previousUserData = userRepository.getReferenceById(id);
        updateData(previousUserData, user);
        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    private void updateData(User oldUserData, User newUserData) {
         oldUserData.setName(newUserData.getName());
         oldUserData.setEmail(newUserData.getEmail());
         oldUserData.setNumber(newUserData.getNumber());
    }
}
