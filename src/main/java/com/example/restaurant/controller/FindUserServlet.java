package com.example.restaurant.controller;

import com.example.restaurant.service.UserService;
import com.example.restaurant.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String remark = req.getParameter("userName");
        String userName = new String(remark.getBytes("iso-8859-1"),"utf-8");
        HashMap<String,Object> map = new HashMap<String,Object>();
        resp.setContentType("application/json;charset= utf-8");
        UserService userService = new UserServiceImpl();
        if (userService.findUsername(userName)!=null){
            map.put("isExist",true);
            map.put("msg","此用户名已存在");
        }else {
            map.put("isExist",false);
            map.put("msg","此用户名可用");
        }
        ObjectMapper mapper = new ObjectMapper();
        //将map的数据转换为json并放入输出流
        mapper.writeValue(resp.getWriter(),map);
    }
}
