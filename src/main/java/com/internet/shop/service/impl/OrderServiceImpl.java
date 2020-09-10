package com.internet.shop.service.impl;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ShoppingCartService;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order(shoppingCart.getUserId());
        order.setProducts(List.copyOf(shoppingCart.getProducts()));
        order.setDate(LocalDate.now().toString());
        shoppingCartService.clear(shoppingCart);
        return orderDao.create(order);
    }

    @Override
    public List<Order> getAllUserOrders(Long userId) {
        return orderDao.getUsersOrders(userId);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public boolean delete(Order item) {
        return orderDao.delete(item.getId());
    }

    @Override
    public Order update(Order item) {
        return orderDao.update(item);
    }

    @Override
    public Order create(Order item) {
        return orderDao.create(item);
    }
}
