package com.javacode.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javacode.course.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private User client;

    public Order() {}

    public Order(Instant moment, OrderStatus orderStatus, User client) {
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(moment, order.moment) && orderStatus == order.orderStatus && Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moment, orderStatus, client);
    }
}
