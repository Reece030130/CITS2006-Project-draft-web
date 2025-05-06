package com.example.restaurant.controller;

import com.example.restaurant.service.CartService;
import com.example.restaurant.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String c_id= req.getParameter("cid");
        Integer cid =0;
        if (c_id!=null){
             cid = Integer.parseInt(c_id);
        }
        CartService cartService = new CartServiceImpl();
        cartService.deleteCartByCid(cid);
        resp.sendRedirect("/shopCarServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
