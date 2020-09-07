package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {

        Product keyboard = new Product("Keyboard", 1200);
        Product headphone = new Product("headphone", 2000);
        Product laptop = new Product("laptop", 25400);

        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        productService.create(keyboard);
        productService.create(headphone);
        productService.create(laptop);

        UserService userService = (UserService) injector.getInstance(UserService.class);
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        User customerRoman = new User("Roman", "login", "password");
        User customerBohdan = new User("Bohdan", "loginB", "passwordB");

        ShoppingCart shoppingCartRoman = new ShoppingCart(1L);
        userService.create(customerRoman);
        shoppingCartService.create(shoppingCartRoman);
        ShoppingCart shoppingCartBohdan = new ShoppingCart(2L);
        userService.create(customerBohdan);
        shoppingCartService.create(shoppingCartBohdan);

        shoppingCartService.addProduct(shoppingCartRoman, productService.get(1L));
        shoppingCartService.addProduct(shoppingCartRoman, productService.get(3L));
        shoppingCartService.deleteProduct(shoppingCartRoman, productService.get(1L));
        System.out.println(shoppingCartService
                .getByUserId(userService.get(1L).getId()).toString());
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        Order order = orderService.completeOrder(shoppingCartRoman);
        System.out.println(shoppingCartService
                .getByUserId(userService.get(1L).getId()).toString());
        System.out.println(order.toString());

        shoppingCartService.addProduct(shoppingCartBohdan, productService.get(1L));
        shoppingCartService.addProduct(shoppingCartBohdan, productService.get(3L));
        System.out.println(shoppingCartService
                .getByUserId(userService.get(2L).getId()).toString());
        Order orderBohdan = orderService.completeOrder(shoppingCartBohdan);
        System.out.println(shoppingCartService
                .getByUserId(userService.get(2L).getId()).toString());
        System.out.println(orderBohdan.toString());
        System.out.println("---><---");
        System.out.println(orderService.getAll().toString());
    }
}
