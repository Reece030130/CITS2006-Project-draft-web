package com.example.restaurant.controller;

import com.example.restaurant.domain.User;
import com.example.restaurant.service.UserService;
import com.example.restaurant.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        Map<String,String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(user);
        if(loginUser!=null){
            HttpSession session = req.getSession();
            session.setAttribute("user",loginUser);
            req.setAttribute("msg","登陆成功！");
            resp.sendRedirect("/findAllMenuServlet");
        }else {
            req.setAttribute("msg","登陆失败！");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

}
