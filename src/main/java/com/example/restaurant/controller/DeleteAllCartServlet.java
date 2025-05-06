package com.example.restaurant.controller;

import com.example.restaurant.domain.Cart;
import com.example.restaurant.service.CartService;
import com.example.restaurant.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAllCartServlet")
public class DeleteAllCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        Integer u_id=0;
        if (uid!=null){
             u_id = Integer.parseInt(uid);
        }
        CartService cartService = new CartServiceImpl();
        cartService.deleteAllCartByUid(u_id);
        resp.sendRedirect("/shopCarServlet");
    }
}
