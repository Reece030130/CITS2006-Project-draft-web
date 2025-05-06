package com.example.restaurant.controller;


import com.example.restaurant.domain.CartVo;
import com.example.restaurant.domain.User;
import com.example.restaurant.service.CartService;
import com.example.restaurant.service.impl.CartServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shopCarServlet")
public class ShopCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        if (req.getSession().getAttribute("user")!=null){
            User user= (User) req.getSession().getAttribute("user");
            CartService cartService = new CartServiceImpl();
            List<CartVo> cartVos= cartService.findAllChartByUid(user.getUid());
            req.setAttribute("cartVos",cartVos);
            req.getRequestDispatcher("/shopCar.jsp").forward(req,resp);
        }else {
            resp.getWriter().println("<script>alert('请先登录！');location.href='login.jsp'</script>");
        }
    }
}











//            MenuService menuService = new MenuServiceImpl();
//            List<Cart> carts = cartService.findCartsByUid(user.getU_id());
//            Map<Cart,Menu> map = new HashMap<Cart,Menu>();
//            for (Cart cart : carts) {
//             map.put(cart,menuService.findMenuDetail(cart.getMid()));
//            }
//            req.setAttribute("carts",carts);
//            req.setAttribute("map",map);