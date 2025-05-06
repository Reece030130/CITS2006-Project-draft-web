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

@WebServlet("/addCartServlet")
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        if (req.getSession().getAttribute("user")!=null){
        CartService cartService = new CartServiceImpl();
        String m_id = req.getParameter("mid");
        String uid = req.getParameter("uid");
        int u_id = 0;
        int mid =0;
        Integer count=0;
        if (uid!=null&&m_id!=null){
            u_id=Integer.parseInt(uid);
            mid=Integer.parseInt(m_id);
        }
        Cart cart= null;
        try {
            cart = cartService.findCartsByMidAndUid(u_id,mid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cart!=null){
            count=cart.getCount()+1;
            cartService.updateCart(u_id,mid,count);
        }else {
            count=1;
            cartService.addCart(u_id,mid,count);
        }
        resp.sendRedirect("/shopCarServlet");
        }else {
            resp.getWriter().println("<script>alert('请先登录！');location.href='login.jsp'</script>");
        }
    }
}
