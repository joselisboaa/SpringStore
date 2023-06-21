package com.javacode.course.config;

import com.javacode.course.entities.*;

import com.javacode.course.enums.OrderStatus;
import com.javacode.course.repositories.*;
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

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("Maria Brown", "maria@gmail.com", "9921312393", "11231432");
        User u2 = new User("Alex Green", "alex@gmail.com", "9914359322", "11231432");

        Order o1 = new Order(Instant.parse("2022-06-20T12:05:02Z"), OrderStatus.valueOf("WAITING_PAYMENT"), u1);
        Order o2 = new Order(Instant.parse("2023-06-15T00:02:55Z"), OrderStatus.valueOf("DELIVERED"), u2);
        Order o3 = new Order(Instant.parse("2023-02-06T23:22:32Z"), OrderStatus.valueOf("CANCELED"), u1);

        Category c1 = new Category("Miscellaneous");
        Category c2 = new Category("Rubber Objects");

        Product p1 = new Product("Rubber duck", 9.99, "A cool rubber duck","https://t4.ftcdn.net/jpg/05/30/31/97/240_F_530319725_6fc5qCb5px0pH2klGTiKprXW71tDbJiK.jpg");
        Product p2 = new Product("Rubber duck 2", 9.99, "A second cool rubber duck","https://t4.ftcdn.net/jpg/05/30/31/97/240_F_530319725_6fc5qCb5px0pH2klGTiKprXW71tDbJiK.jpg");

        OrderItem oi1 = new OrderItem(o1, p1, 5, p1.getPrice());
        OrderItem oi2 = new OrderItem(o2, p2, 15, p1.getPrice());
        OrderItem oi3 = new OrderItem(o1, p2, 2, p1.getPrice());

        p1.getCategories().add(c1);
        p1.getCategories().add(c2);
        p2.getCategories().add(c1);

        userRepositor.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(c1, c2));
        productRepository.saveAll(Arrays.asList(p1, p2));
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
    }

}
