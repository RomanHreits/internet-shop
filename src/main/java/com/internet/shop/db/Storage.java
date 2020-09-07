package com.internet.shop.db;

import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static Long productId = 0L;
    private static Long orderId = 0L;
    private static Long userId = 0L;
    private static Long shoppingCartId = 0L;

    public static Product addProduct(Product product) {
        product.setId(++productId);
        products.add(product);
        return product;
    }

    public static User addUser(User user) {
        user.setId(++userId);
        users.add(user);
        return user;
    }

    public static Order addOrder(Order order) {
        order.setId(++orderId);
        orders.add(order);
        return order;
    }

    public static ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(++shoppingCartId);
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

}
