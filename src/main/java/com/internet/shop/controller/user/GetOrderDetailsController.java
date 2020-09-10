package com.internet.shop.controller.user;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.OrderService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetOrderDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        List<Product> products = orderService.get(id).getProducts();
        Double sum = products.stream()
                .map(product -> product.getPrice())
                .reduce(0.0, (x, y) -> x + y);
        req.setAttribute("sum", sum);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/order/orderDetails.jsp").forward(req, resp);
    }
}
