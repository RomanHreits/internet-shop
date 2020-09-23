package com.internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private List<Product> products;
    private Long userId;
    private String date;

    public Order(Long userId) {
        this.products = new ArrayList<>();
        this.userId = userId;
    }

    public Order(Long id, Long userId) {
        this.id = id;
        this.products = new ArrayList<>();
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id)
                && Objects.equals(products, order.products)
                && Objects.equals(userId, order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, userId);
    }

    @Override
    public String toString() {
        return "Order {"
                + "id =" + id
                + ", products =" + products.toString()
                + ", userId =" + userId + '}';
    }
}
