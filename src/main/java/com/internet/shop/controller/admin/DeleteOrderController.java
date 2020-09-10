package com.internet.shop.controller.admin;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        orderService.deleteById(id);
        resp.sendRedirect(req.getContextPath() + "/storage/orders");
    }
}
