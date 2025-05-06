package com.example.restaurant.controller;

import com.example.restaurant.domain.CartVo;
import com.example.restaurant.domain.Orders;
import com.example.restaurant.service.OrderService;
import com.example.restaurant.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
//生成订单，和详情，删除数据库中此用户的购物车
@WebServlet("/confirmOrdersServlet")
public class ConfirmOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");//设置解码
        String u_id = req.getParameter("uid");//从jsp中得到uid
        String remark1 =req.getParameter("remark");//从jsp中得到反馈的内容
        String remark = new String(remark1.getBytes("iso-8859-1"),"utf-8");//设置文本框中的文字格式，反馈的解码。
        OrderService orderService = new OrderServiceImpl();//实例化业务层对象
        double total=0;//设置total。

       List<CartVo> cartVos= (List<CartVo>) req.getSession().getAttribute("cartVos");//从session中获取cartVos的集合，并进行操作。
       String a = "(";//生成内容为cid的字符串，并用（？，？，？）的方式拼接，在数据层中用delete from table where cid in ?(字符串a)--->像（24，46，79）.代码为39-45行。
        for (CartVo cartVo : cartVos) {
            total+=cartVo.getCount()*cartVo.getMprice();//计算所有菜品的合计价格。
        }
        for (int i =0;i<cartVos.size();i++){
            if (i==cartVos.size()-1){
                a += cartVos.get(i).getCid()+")";
            }else {
                a += cartVos.get(i).getCid()+",";
            }
        }
        Integer uid =0;
        if (u_id!=null){
            uid = Integer.parseInt(u_id);

        }
        String oid = UUID.randomUUID().toString();//生成随机的订单号码
        Orders orders = new Orders(oid,uid,remark,"未配送",total);//生成Orders对象并赋值。
        req.getSession().setAttribute("oid",oid);
        req.getSession().setAttribute("state",orders.getState());
        req.getSession().setAttribute("total",total);
        try {
            orderService.confirmOrders(cartVos,orders,a);//调用OrderService进行生成订单，订单详情和删除购物车的事务。
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/myOrders.jsp");//从定向到确定myOrders的页面中去
    }
}


