package com.example.restaurant.controller;

import com.example.restaurant.domain.CartVo;
import com.example.restaurant.service.CartService;
import com.example.restaurant.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("Utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        String u_id = req.getParameter("uid");
        String[] cids = req.getParameterValues("cid");
        if (cids!=null){
            CartService cartService = new CartServiceImpl();
            if (u_id!=null){
                Integer uid = Integer.parseInt(u_id);
                List<CartVo> cartVos = cartService.findAllChartByUidAndCids(uid,cids);
                req.getSession().setAttribute("cartVos",cartVos);;
                req.getRequestDispatcher("/order.jsp").forward(req,resp);
            }
        }else {
            resp.getWriter().println("<script>alert('请选择您的商品哟');location.href='/shopCarServlet'</script>");
        }
    }
}
