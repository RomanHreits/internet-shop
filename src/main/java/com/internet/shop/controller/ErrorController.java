package com.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");
        String requestUri = (String)
                req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        String servletName = (String)
                req.getAttribute("javax.servlet.error.servlet_name");
        req.setAttribute("exception", exception.getClass());
        req.setAttribute("errorMessage", exception.getMessage());
        req.setAttribute("servletName", servletName);
        req.setAttribute("requestUri", requestUri);
        req.getRequestDispatcher("/WEB-INF/views/exception.jsp").forward(req, resp);
    }
}
