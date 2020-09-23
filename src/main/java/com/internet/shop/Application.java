package com.internet.shop;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.dao.UserDao;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Application {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private static ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);
    private static OrderDao orderDao = (OrderDao) injector.getInstance(OrderDao.class);
    private static ShoppingCartDao cartDao = (ShoppingCartDao) injector
            .getInstance(ShoppingCartDao.class);
    private static UserDao userDao = (UserDao) injector.getInstance(UserDao.class);
    private static final Product keyboard = new Product("Keyboard", 1200);
    private static final Product headphone = new Product("headphone", 2000);
    private static final Product laptop = new Product("laptop", 25400);

    public static void main(String[] args) {
        Product product = productService.create(keyboard);
        Product product1 = productService.create(headphone);
        Product product2 = productService.create(laptop);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        products.add(product2);
        System.out.println(productService.get(1L).toString());
        System.out.println(productService.getAll().toString());
        productService.update(new Product(1L, "updateProduct", 111));
        System.out.println(productService.get(1L).toString());
        productService.deleteById(1L);
        System.out.println(productService.getAll().toString());
        System.out.println(orderDao.get(2L).get().toString());
        System.out.println(orderDao.getAll().toString());
        Order order = new Order(2L);
        order.setProducts(products);
        orderDao.create(order);
        orderDao.delete(2L);
        System.out.println(orderDao.getAll().toString());
        ShoppingCart cart = new ShoppingCart(4L,1L);
        ShoppingCart cart2 = new ShoppingCart(4L, 1L);
        cartDao.create(cart);
        cart.setProducts(List.of(new Product(16L,"Keyboard", 1200),
                new Product(17L,"headphone", 2000), new Product(18L,"laptop", 25400)));
        cart2.setProducts(List.of(new Product(16L,"Keyboard", 1200)));
        System.out.println(cartDao.get(4L).get());
        cartDao.update(cart);
        System.out.println(cartDao.get(4L).get());
        cartDao.update(cart2);
        System.out.println(cartDao.get(4L).get());
        User user = new User("Bob", "bob", "12345bob");
        user.setRoles(Set.of());
        System.out.println(userDao.get(1L).get());
        userDao.getAll().toString();
    }
}
