package com.example.restaurant.controller;

import com.example.restaurant.service.CartService;
import com.example.restaurant.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCartByCountServlet")
public class UpdateCartByCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("count");
        String C_id = req.getParameter("cid");
        Integer count = 0;
        Integer cid =0;
        if (num!=null&&C_id!=null){
         count = Integer.parseInt(num);
         cid=Integer.parseInt(C_id);
        }
        CartService cartService = new CartServiceImpl();
        cartService.updateCartByCount(count,cid);
        resp.sendRedirect("/shopCarServlet");
    }
}
