<%@ page import="com.example.restaurant.domain.CartVo" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>

	<head>
		<meta charset="utf-8" />
		<title>首页</title>
		<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.min.js"></script>
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<script src="js/jquery.min1.js"></script>
		<style>
			table {
				font-size: 14px;
			}
			
			#tb td {
				border: 1px solid #CCC;
				line-height: 35px
			}
			#tb td input{
				line-height: 25px;
			}
			#btntb td a{
				height: 50px;
			}
		</style>
	</head>

	<body>
		<div class="header">
			<div class="container">
				<div class="header_top">
					<ul class="phone">
						<li class="phone_left"><i> </i><span></span></li>
						<li class="phone_right">欢迎来到订餐系统</li>
						<div class="clearfix"></div>
					</ul>

					<ul class="account" style="margin-left:680px;">
						<li><a href="myorders.html">我的订单</a></li>
					</ul>
					<ul class="shopping_cart" style="margin-left:5px;margin-top:-3px;">
						<a href="#">
							<li class="shop_left"><i class="cart"> </i><span>购物车</span></li>
						</a>
						<div class="clearfix"> </div>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="header_bottom">
					<div class="header_nav">
						<div class="logo">
							<a href="Main.jsp"><img src="images/logo.png" alt="" /><br></a>
						</div>
						<nav class="navbar navbar-default menu" role="navigation">
							<h3 class="nav_right"><a href="Main.jsp"><img src="images/logo.png" class="img-responsive" width="100%"/></a></h3>
							<div class="container-fluid">
								<!-- Brand and toggle get grouped for better mobile display -->
								<div class="navbar-header">
									<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
								</div>
								<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									<ul class="nav navbar-nav menu1">
										<li><a href="Main.jsp" class="active">首页</a></li>
										<li><a href="orders.jsp">网上订餐</a></li>
										<li><a href="#">订餐帮助</a></li>
										<li><a href="#">客服中心</a></li>
										<li><a href="contact.html">联系我们</a></li>
									</ul>
									<ul class="login">
										<a href="login.jsp">
											<li class="login_top"><i class="sign"> </i><span>登录</span></li>
										</a>
										<a href="register.jsp">
											<li class="login_bottom"><i class="register"> </i><span>注册</span></li>
										</a>
									</ul>
									<div class="clearfix"></div>
								</div>
							</div>
						</nav>
						<div class="clearfix"></div>
					</div>
					<div class="search">
						<input type="text" class="text" placeholder="菜品名称">
						<input type="submit" value="Search">
					</div>
				</div>
			</div>
		</div>
		<center>
		<div class="main" style="height: 400px;padding-top: 25px;">
		<table width="90%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" height="100%">
			<tr valign="top">
				<td>
					<table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
						<tr valign="bottom">
							<td height="30">
								<font color="#000000">您的订单详情:</font>
							</td>
						</tr>
					</table>
					
					<table width="98%" border="0" cellspacing="2" cellpadding="0" align="center">
						<tr>
							<td height="5"></td>
						</tr>
					</table>
					<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td align="left">
								<table id="tb" width="100%" border="0" align="CENTER" cellpadding="5" cellspacing="1">
									<tr bgcolor="#FFFFCC">
										<td width="50" height="22">
											<div align="CENTER">
												<font color="#000000">编号</font>
											</div>
										</td>
										<td width="610" height="22">
											<div align="CENTER">
												<font color="#000000">商品名称</font>
											</div>
										</td>
										<td width="104" height="22">
											<div align="CENTER">
												<font color="#000000">单价</font>
											</div>
										</td>
										<td width="100" height="22">
											<div align="CENTER">
												<font color="#000000">数量</font>
											</div>
										</td>
										<td width="116" height="22">
											<div align="CENTER">
												<font color="#000000">小计</font>
											</div>
										</td>
									</tr>
									<c:forEach items="${sessionScope.cartVos}" var="cartVo" varStatus="s">
									<tr bgcolor="#ffffff">
										<td width="50" align="center" height="22">
											<font color="#000000">${s.count}
											</font>
										</td>
										<td align="center" height="22">
											<font color="#000000">${cartVo.mname}</font>
											<input type="hidden" name="prodid" value="500047"></td>
										<td width="104" align="center" height="22">
											<font color="#000000">￥${cartVo.mprice}</font>
										</td>
										<td width="100" class="hh" align="center" height="22">
											${cartVo.count}</td>
										<td width="116" class="bb" align="center" height="22">
											<font color="#000000">￥${cartVo.mprice*cartVo.count}</font>
										</td>
									</tr>
									</c:forEach>

									<tr bgcolor="#FFFFCC">
										<td width="50" height="22" align="center">
											<font color="#000000">合计</font>
										</td>
										<td height="22" align="center">
											<font color="#000000">-</font>
										</td>
										<td width="104" height="22" align="center">
											<font color="#000000">-</font>
										</td>
										<td width="100" height="22" align="center" class="hh">
											<font color="#000000">-</font>
										</td>
										<td width="116" height="22" align="center" class="bb" id="total">
											<font color="#000000">￥<%
												List<CartVo> carts = (List<CartVo>) request.getSession().getAttribute("cartVos");
												double total=0;
												for (CartVo cartVos : carts) {
													total+=cartVos.getCount()*cartVos.getMprice();}%>
												<%=total%> </font>
										</td>
									</tr>
								</table>
                                <p>&nbsp;</p>
                                <p>用户名：${sessionScope.user.u_name}                               </p>
                                <p>联系电话：${sessionScope.user.u_phone}                             </p>
                                <p>收货地址：${sessionScope.user.u_address} </p>
                                <p>&nbsp;</p>
                                <form name="form1" method="post" action="">
				备注：<textarea name="remark" cols="100" rows="3" id="remark"></textarea>
								   <p align="right"> <input type="button" name="button" id="button1" value="确认订单" onclick="confirmOrders()">&nbsp;&nbsp;<input type="button" name="button" id="button" value="取消订单"></p>

                            </form>								<br></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
		</center>
<script>
   function confirmOrders(){
	   var remark = document.getElementById("remark").value;
	   location.href="/confirmOrdersServlet?remark="+remark+"&uid="+${sessionScope.user.uid};
   }
</script>
		<div class="footer">
			<div class="container">
				<div class="footer-grid footer-grid1">
					<h3 class="m_2">Company</h3>
					<ul class="list1">
						<li><a href="#">Home</a></li>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Blog</a></li>
						<li><a href="#">Latest News</a></li>
						<li><a href="#">Login</a></li>
						<li><a href="#">Join Us</a></li>
					</ul>
				</div>
				<div class="footer-grid footer-grid2">
					<h3 class="m_2">Company</h3>
					<ul class="list1">
						<li><a href="#">Lorem ipsum dolor sit amet</a></li>
						<li><a href="#">diam nonummy nibh euismod</a></li>
						<li><a href="#">nostrud exerci tation</a></li>
						<li><a href="#">hendrerit in vulputate velit</a></li>
						<li><a href="#">soluta nobis eleifend option</a></li>
						<li><a href="#">dynamicus, qui sequitur</a></li>
					</ul>
				</div>
				<div class="footer-grid footer-grid3">
					<h3 class="m_2">Information</h3>
					<ul class="list1">
						<li><a href="#">My Account</a></li>
						<li><a href="#">Rewards</a></li>
						<li><a href="#">Terms & Conditions</a></li>
						<li><a href="#">Buying Guide</a></li>
						<li><a href="#">FAQ</a></li>
					</ul>
				</div>
				<div class="footer-grid footer-grid4">
					<h3 class="m_2">Let's be friends</h3>
					<ul class="footer_social">
						<li>
							<a href=""> <i class="tw"> </i> </a>
						</li>
						<li><a href=""><i class="fb"> </i> </a></li>
						<li><a href=""><i class="rss"> </i> </a></li>
						<li><a href=""><i class="msg"> </i> </a></li>
						<div class="clearfix"> </div>
					</ul>
					<h3 class="m_3">Subscribe</h3>
					<p class="m_4">aliquam erat volutpat. Ut wisi</p>
					<div class="footer_search">
						<input type="text" class="text" value="Enter Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter Email';}">
						<input type="submit" value="Search">
					</div>
				</div>
				<div class="footer-grid footer-grid_last">
					<ul class="secure">
						<li class="secure_img"><img src="images/secure.png" alt="" /></li>
						<li class="secure_desc">Lorem ipsum dolor coadipiscing</li>
						<div class="clearfix"> </div>
					</ul>
					<ul class="secure">
						<li class="secure_img"><img src="images/order.png" alt="" /></li>
						<li class="secure_desc">Lorem ipsum dolor coadipiscing</li>
						<div class="clearfix"> </div>
					</ul>
				</div>
				<div class="clearfix"> </div>
				<div class="copy">
					<p>Copyright &copy; 2014.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
				</div>
			</div>
		</div>
	</body>

</html>