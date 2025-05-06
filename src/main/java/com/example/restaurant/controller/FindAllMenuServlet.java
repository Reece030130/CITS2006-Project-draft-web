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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findAllMenuServlet")
public class FindAllMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        MenuService menuService = new MenuServiceImpl();
        List<Menu> lists = new ArrayList<Menu>();
        try {
            lists = menuService.findAllMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int menuCount = menuService.findMenuCount();
        if (lists!=null){
//            req.getSession().setAttribute("menuCount",menuCount);
            req.getSession().setAttribute("lists",lists);
        req.getRequestDispatcher("/Main.jsp").forward(req,resp);
        }
    }
}
