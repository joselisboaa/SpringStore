package com.javacode.course.config;

import com.javacode.course.entities.Category;
import com.javacode.course.entities.Order;
import com.javacode.course.entities.Product;
import com.javacode.course.entities.User;

import com.javacode.course.enums.OrderStatus;
import com.javacode.course.repositories.CategoryRepository;
import com.javacode.course.repositories.OrderRepository;
import com.javacode.course.repositories.ProductRepository;
import com.javacode.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepositor;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("Maria Brown", "maria@gmail.com", "9921312393", "11231432");
        User u2 = new User("Alex Green", "alex@gmail.com", "9914359322", "11231432");

        Order o1 = new Order(Instant.parse("2022-06-20T12:05:02Z"), OrderStatus.valueOf("WAITING_PAYMENT"), u1);
        Order o2 = new Order(Instant.parse("2023-06-15T00:02:55Z"), OrderStatus.valueOf("DELIVERED"), u2);
        Order o3 = new Order(Instant.parse("2023-02-06T23:22:32Z"), OrderStatus.valueOf("CANCELED"), u1);

        Category c1 = new Category("Miscelaneous");

        Product p1 = new Product("Rubber duck", 9.99, "https://t4.ftcdn.net/jpg/05/30/31/97/240_F_530319725_6fc5qCb5px0pH2klGTiKprXW71tDbJiK.jpg");

        userRepositor.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(c1));
        productRepository.saveAll(Arrays.asList(p1));
    }

}
