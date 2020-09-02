package com.internet.shop.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private List<Product> products;
    private double price;

    public Order(List<Product> products, double price) {
        this.products = products;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        return Double.compare(order.price, price) == 0
                && Objects.equals(id, order.id)
                && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, price);
    }
}
