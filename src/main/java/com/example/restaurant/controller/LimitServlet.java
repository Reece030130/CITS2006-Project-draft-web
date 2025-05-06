package com.example.restaurant.controller;

import com.example.restaurant.util.LimitedOrders;
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

@WebServlet("/limitServlet")
public class LimitServlet extends HttpServlet {
    LimitedOrders limitedOrders = new LimitedOrders();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String limit1 = req.getParameter("limit");
        MenuService menuService = new MenuServiceImpl();
        String state=req.getParameter("state");
        Integer limit =0;
        Integer menuCount= (Integer) req.getSession().getAttribute("menuCount");
        System.out.println(menuCount);
        List<Menu> menus = new ArrayList<Menu>();
        if (limit1!=null){
            limit = Integer.parseInt(limit1);
        }
        switch (state){
            case "MainPage":
               limitedOrders.setCurrentPage(0);
//               menus = menuService.limitedMenuList(limit,limitedOrders.getCurrentPage());
               req.getSession().setAttribute("menusLimited",menus);
               req.setAttribute("currentPage",(limitedOrders.getCurrentPage()/limit)+1);
               req.getRequestDispatcher("/orders.jsp").forward(req,resp);
               break;
            case "lastPage":
                if (limitedOrders.getCurrentPage()!=0){
                    limitedOrders.setCurrentPage(limitedOrders.getCurrentPage()-limit);
//                    menus = menuService.limitedMenuList(limit,limitedOrders.getCurrentPage());
                    req.getSession().setAttribute("menusLimited",menus);
                    req.setAttribute("currentPage",(limitedOrders.getCurrentPage()/limit)+1);
                    req.getRequestDispatcher("/orders.jsp").forward(req,resp);
                } else {
                    resp.getWriter().println("<script>alert('已经是首页！');location.href='orders.jsp'</script>");
                }
                break;
            case "nextPage":
                if (limitedOrders.getCurrentPage()==menuCount-limit){
                    resp.getWriter().println("<script>alert('已经是尾页！');location.href='orders.jsp'</script>");
                }else{
                    limitedOrders.setCurrentPage(limitedOrders.getCurrentPage()+limit);
//                    menus = menuService.limitedMenuList(limit,limitedOrders.getCurrentPage());
                    req.getSession().setAttribute("menusLimited",menus);
                    req.setAttribute("currentPage",(limitedOrders.getCurrentPage()/limit)+1);
                    req.getRequestDispatcher("/orders.jsp").forward(req,resp);
                }
                break;

            case "leastPage":
                limitedOrders.setCurrentPage(menuCount-limit);
//                menus=menuService.limitedMenuList(limit,limitedOrders.getCurrentPage());
                req.getSession().setAttribute("menusLimited",menus);
                req.setAttribute("currentPage",(limitedOrders.getCurrentPage()/limit)+1);
                req.getRequestDispatcher("/orders.jsp").forward(req,resp);
                break;
        }
    }
}
