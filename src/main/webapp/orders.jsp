<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<title>网上订餐</title>
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
							<a href="index.html"><img src="images/logo.png" alt="" /><br></a>
						</div>
						<nav class="navbar navbar-default menu" role="navigation">
							<h3 class="nav_right"><a href="index.html"><img src="images/logo.png" class="img-responsive" width="100%"/></a></h3>
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
										<li><a href="index.html" class="active">首页</a></li>
										<li><a href="orders.jsp">网上订餐</a></li>
										<li><a href="#">订餐帮助</a></li>
										<li><a href="#">客服中心</a></li>
										<li><a href="contact.html">联系我们</a></li>
									</ul>
									<ul class="login">
										<a href="login.html">
											<li class="login_top"><i class="sign"> </i><span>登录</span></li>
										</a>
										<a href="register.html">
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
		<div class="main">
			<div class="container">
				<div class="single">
					<div class="row content">
						<div class="col-md-9">
							<div style="width:100%; margin:0 auto;">
								<div class="clearfix">
									<p class="shaixuan-tj floatLeft clearfix">
										<span><a href="">全部结果：</a></span></p>
									<p class="shaixuan-btn clearfix"><span><em>确认筛选</em></span></p>
								</div>
								<div id="page-search-store" class="mb10 border sxcon">
									<div class="search-by by-category relative">
										<dl class="relative clearfix">
											<dt class="floatLeft"><a href="">菜系：</a></dt>
											<dd class="floatLeft show-con">
												<a href="" class="">川菜</a>
												<a href="" class="">粤菜</a>
												<a href="" class="">湘菜</a>
												<a href="" class="">平台</a>
												<a href="" class="">川菜</a>
												<a href="" class="">粤菜</a>
												<a href="" class="">湘菜</a> <a href="" class="">川菜</a>
												<a href="" class="">粤菜</a>
												<a href="" class="">湘菜</a>
												<a href="" class="">平台</a>
												<a href="" class="">川菜</a>
												<a href="" class="">粤菜</a>
												<a href="" class="">湘菜</a> <a href="" class="">川菜</a>
												<a href="" class="">粤菜</a>
												<a href="" class="">湘菜</a>
												<a href="" class="">平台</a>
												<a href="" class="">川菜</a>
												<a href="" class="">粤菜</a>
												<a href="" class="">湘菜</a>
											</dd>
											<dd class="floatLeft show-more">
												<h3 class="pointer clearfix"><span>更多</span><i class="icon-angle-down"></i></h3></dd>
										</dl>
										<dl class="relative clearfix" style="border-bottom:0">
											<dt class="floatLeft"><a href="">价格：</a></dt>
											<dd class="floatLeft show-con">
												<a href="" class="">1-10</a>
												<a href="" class="">11-20</a>
											</dd>
										</dl>
									</div>
								</div>
							</div>
							<div class="row content"></div>
							<div class="row content_bottom1">
								<c:forEach items="${sessionScope.menusLimited}" var="menu" varStatus="s">
									<div class="col-md-3">
										<div class="content_box">
											<a href="detail.html">
												<div class="view view-fifth">
												<img src="images/p5.jpg" class="img-responsive" width="100%" />
												<div class="content_box-grid">
													<p class="m_1">${menu.mname}</p>
													<div class="price">Price:
														<span class="actual">${menu.mprice}</span>
													</div>
													<ul class="product_but">
														<li class="but3">Buy</li>
														<li class="like"><span>120</span><i class="like1"> </i></li>
														<div class="clearfix"> </div>
													</ul>

												</div>
											</div>
										</a>
									</div>
								</div>
								</c:forEach>
							</div>
							<ul class="dc_pagination dc_paginationA dc_paginationA06">
								<li><a href="javascript:limit(4,'MainPage')" class="current">首页</a></li>
								<li><a href="javascript:limit(4,'lastPage')">上一页</a></li>
								<li><a href="javascript:limit(4,'nextPage')">下一页</a></li>
								<li><a href="javascript:limit(4,'leastPage')" class="next">尾页</a></li>
								当前第${requestScope.currentPage}页/共<%Integer menuCount = (Integer) request.getSession().getAttribute("menuCount");Integer totalPage = menuCount/4;%><%=totalPage%>页       共${sessionScope.menuCount}条
							</ul>
						</div>
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
<script>
	$(function() {
		$(".shaixuan-tj span.crumb-select-item").live('hover', function(event) {
			if (event.type == 'mouseenter') {
				$(this).addClass("crumb-select-itemon");
			} else {
				$(this).removeClass("crumb-select-itemon");
			}
		});
		$(".shaixuan-tj span.crumb-select-item").live('click', function(event) {
			event.preventDefault();
			$(this).remove();
			var TTR = $(this).find("em").text();
			$(".show-con a").each(function() {
				var TT = $(this).text();
				THI = $(this);
				THIPP = $(this).parents("dl");
				if (TTR == TT) {
					THI.removeClass("nzw12");
					THIPP.css("display", "block");
				}
			})
		});
		$(".show-con a").click(function(event) {
			event.preventDefault();
			THIP = $(this).parents("dl");
			if ($(this).hasClass("nzw12")) {} else {
				$(this).addClass("nzw12");
				var zhiclass = $(this).parents("dd").siblings("dt").find("a").text();
				zhicon = $(this).text();
				tianjaneir = "<span class='crumb-select-item'><a href=''><b>" + zhiclass + "</b><em>" + zhicon + "</em><i class='icon-remove'></i></a></span>"
				$(".shaixuan-tj").children().last().after(tianjaneir);
				THIP.css("display", "none");
			}
		});
		$(".show-more").click(function(event) {
			event.preventDefault();
			var ticon = $(this).find("i");
			tspan = $(this).find("span");
			if ($(this).hasClass("zk")) {
				$(this).siblings(".show-con").css("height", "30px");
				ticon.removeClass("icon-angle-up");
				ticon.addClass("icon-angle-down");
				tspan.html("更多");
				$(this).removeClass("zk")
			} else {
				$(this).siblings(".show-con").css("height", "auto");
				ticon.removeClass("icon-angle-down");
				ticon.addClass("icon-angle-up");
				tspan.html("收起");
				$(this).addClass("zk")
			}
		});
		$("#sxbtn").click(function(event) {
			event.preventDefault();
			var xicon = $(this).find("span i");
			xspan = $(this).find("span em");
			if ($(this).hasClass("zkon")) {
				xspan.text("收起筛选");
				xicon.removeClass("icon-angle-down");
				xicon.addClass("icon-angle-up");
				$(".sxcon").slideDown();
				$(this).removeClass("zkon")
			} else {
				xspan.text("查看筛选");
				xicon.removeClass("icon-angle-up");
				xicon.addClass("icon-angle-down");
				$(".sxcon").slideUp();
				$(this).addClass("zkon")
			}
		})
	})
</script>