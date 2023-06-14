package com.javacode.course.config;

import com.javacode.course.entities.User;

import com.javacode.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepositor;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("Maria Brown", "maria@gmail.com", "9921312393", "11231432");
        User u2 = new User("Alex Green", "alex@gmail.com", "9914359322", "11231432");

        userRepositor.saveAll(Arrays.asList(u1, u2));
    }

}
