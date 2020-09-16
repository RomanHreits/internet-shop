package com.internet.shop.controller.admin;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final String USER_ID = "user_Id";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService cartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("id"));
        long sessionUserId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == sessionUserId) {
            req.setAttribute("message", "You can't delete yourself!!!");
            req.getRequestDispatcher(req.getContextPath() + "/users").forward(req, resp);
            return;
        }
        userService.deleteById(userId);
        cartService.getByUserId(userId)
                .ifPresent(shoppingCart -> cartService.delete(shoppingCart));
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
