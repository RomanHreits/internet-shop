package com.internet.shop.controller.admin;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService cartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("id"));
        if (checkRole(userService.get(userId).getRoles(), "ADMIN")) {
            req.setAttribute("message", "You can't delete yourself or other administrators!!!");
            req.getRequestDispatcher(req.getContextPath() + "/users").forward(req, resp);
            return;
        }
        userService.deleteById(userId);
        ShoppingCart userCart = cartService.getByUserId(userId);
        cartService.delete(userCart);
        resp.sendRedirect(req.getContextPath() + "/users");
    }

    private boolean checkRole(Set<Role> roles, String roleName) {
        for (Role role : roles) {
            if (role.getRoleName().name().equals(roleName)) {
                return true;
            }
        }
        return false;
    }
}
