<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>

	<head>
		<script>
			function limit(limit,state){
				location.href="/limitServlet?limit="+limit+"&state="+state;
			}
		</script>
		<meta charset="utf-8" />
		<title>main page</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/style.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/reset.css">
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/styles.css">
		<script src="js/jquery.min1.js"></script>
	</head>

	<body>
		<div class="header">
			<div class="container">
				<div class="header_top">
					<ul class="phone">
						<li class="phone_left"><i> </i><span></span></li>
						<li class="phone_right">trdvytdvjht</li>
						<div class="clearfix"></div>
					</ul>

					<ul class="account" style="margin-left:680px;">
						<li><a href="/myOrdersServlet?uid=${sessionScope.user.uid}">my order</a></li>
					</ul>
					<ul class="shopping_cart" style="margin-left:5px;margin-top:-3px;">
						<a href="/shopCarServlet">
							<li class="shop_left"><i class="cart"> </i><span>shop car</span></li>
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
										<li><a href="Main.jsp" class="active">mainpage</a></li>
										<li><a href="javascript:limit(4,'MainPage')">online order</a></li>
										<li><a href="#">help</a></li>
										<li><a href="#">customer help</a></li>
										<li><a href="contact.html">Contact us</a></li>
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
					<form name="searchMenu" method="post" action="/searchMenuServlet">
					<div class="search">
						<input type="text" class="text" placeholder="菜品名称" name="menuName" id="menuName" value="${sessionScope.menuName}">
						<input type="submit" value="Search">
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="container">
				<div class="banner">
					<img src="images/banner.jpg" class="img-responsive" width="100%" />
				</div>
				<div class="row content">
					<div class="col-md-9">
						<ul class="feature">
							<h3><i class="arrow"> </i><span>最新菜品</span></h3>
						</ul>
						<div class="row content_bottom">
							<c:forEach items="${sessionScope.lists}" var="list" varStatus="s">
								<div class="col-md-3">
									<div class="content_box">
										<a href="detailServlet?mid=${list.mid}">
											<div class="view view-fifth">
												<img src="images/${list.mimg}" class="img-responsive" width="100%" />
												<div class="content_box-grid">
													<p class="m_1">${list.mname} </p>
													<div class="price">Price:
														<span class="actual">${list.mprice}</span>
													</div>
													<ul class="product_but">
														<li class="but3">Buy</li>
														<li class="like"><span>120</span><i class="like1"> </i></li>
														<div class="clearfix"> </div>
													</ul>
													<div class="mask">
														<div class="info">Quick View</div>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<ul class="dc_pagination dc_paginationA dc_paginationA06">
					</ul>
					</div>
				</div>
			</div>
		</div>
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