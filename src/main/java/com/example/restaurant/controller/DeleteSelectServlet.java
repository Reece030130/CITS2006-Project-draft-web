package com.example.restaurant.controller;

import com.example.restaurant.service.CartService;
import com.example.restaurant.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectServlet")
public class DeleteSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] strings = req.getParameterValues("cid");
        CartService cartService = new CartServiceImpl();
        Integer cid =0;
        for (String string : strings) {
            if (string!=null){
                cid=Integer.parseInt(string);
                cartService.deleteCartByCid(cid);
            }
        }
        resp.sendRedirect("/shopCarServlet");
    }
}
