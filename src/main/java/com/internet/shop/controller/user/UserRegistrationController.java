package com.internet.shop.controller.user;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserRegistrationController extends HttpServlet {
    private static final String USER_ID = "user_Id";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService cartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (password.equals(req.getParameter("repeatPwd"))) {
            User createdUser = userService.create(new User(login, password));
            cartService.create(new ShoppingCart(createdUser.getId()));
            HttpSession session = req.getSession();
            session.setAttribute(USER_ID, createdUser.getId());
            resp.sendRedirect(req.getContextPath() + "/user");
        } else {
            req.setAttribute("message", "Your password and repeat "
                    + "password are not the same!");
            req.setAttribute("currentLogin", login);
            req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp").forward(req, resp);
        }
    }
}
