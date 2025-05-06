package com.example.restaurant.controller;

import com.example.restaurant.domain.Menu;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.service.impl.MenuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/detailServlet")
public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("mid");
        int mid =0;
        if (id!=null){
             mid= Integer.parseInt(id);
        }
        MenuService menuService = new MenuServiceImpl();
        Menu menu = null;
        try {
            menu = menuService.findMenuDetail(mid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("menu", menu);
        req.getRequestDispatcher("/detail.jsp").forward(req,resp);
    }
}
