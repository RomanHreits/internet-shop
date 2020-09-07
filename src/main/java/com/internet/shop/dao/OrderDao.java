package com.internet.shop.dao;

import com.internet.shop.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Order create(Order order);

    Optional<Order> get(Long id);

    boolean delete(Long id);

    List<Order> getAllByUserId(Long userId);

    List<Order> getAll();
}
