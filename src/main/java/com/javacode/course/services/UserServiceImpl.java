package com.javacode.course.services;

import com.javacode.course.entities.User;
import com.javacode.course.repositories.UserRepository;
import com.javacode.course.services.exceptions.DatabaseException;
import com.javacode.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new ResourceNotFoundException(("User not found or not exists.")));
    }

    @Override
    public User create(User user) {
        User createdUser = userRepository.save(user);

        return createdUser;
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException("User not found or not exists.");
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException(error.getMessage());
        }
    }

    @Override
    public User update(Long id, User user) {
        try {
            User previousUserData = userRepository.getReferenceById(id);
            updateData(previousUserData, user);
            User updatedUser = userRepository.save(user);

            return updatedUser;
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("User not found or not exists.");
        }
    }

    private void updateData(User oldUserData, User newUserData) {
         oldUserData.setName(newUserData.getName());
         oldUserData.setEmail(newUserData.getEmail());
         oldUserData.setNumber(newUserData.getNumber());
    }
}
