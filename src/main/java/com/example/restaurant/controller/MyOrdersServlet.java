package com.example.restaurant.controller;

import com.example.restaurant.domain.Orders;
import com.example.restaurant.service.OrderService;
import com.example.restaurant.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myOrdersServlet")
public class MyOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u_id = req.getParameter("uid");
        Integer uid =0;
        if (u_id!=null){
            uid = Integer.parseInt(u_id);
        }
        OrderService orderService = new OrderServiceImpl();
       List<Orders> orders = orderService.findOrderListsByUid(uid);
       req.getSession().setAttribute("orders",orders);
       resp.sendRedirect("myOrders.jsp");
    }
}
