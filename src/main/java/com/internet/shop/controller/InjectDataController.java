package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User roman = new User("roman", "pwdR");
        roman.setRoles(Set.of(Role.of("USER")));
        userService.create(roman);

        User alice = new User("alice", "pwdA");
        alice.setRoles(Set.of(Role.of("USER")));
        userService.create(alice);

        User admin = new User("admin", "1");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);

        Product iphoneXR = new Product("iphoneXR", 21000);
        productService.create(iphoneXR);
        Product macBookPro = new Product("macBookPro", 46000);
        productService.create(macBookPro);
        Product laptopAcer = new Product("AcerAspire5", 22000);
        productService.create(laptopAcer);

        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
