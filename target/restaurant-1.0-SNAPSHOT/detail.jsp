<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>

	<head>
		<meta charset="utf-8" />
		<title>详细信息</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<link rel="stylesheet" href="css/etalage.css">
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.etalage.min.js"></script>
		<script>
			jQuery(document).ready(function($) {
				$('#etalage').etalage({
					thumb_image_width: 300,
					thumb_image_height: 400,
					show_hint: true,
					click_callback: function(image_anchor, instance_id) {
						alert('Callback example:\nYou clicked on an image with the anchor: "' + image_anchor + '"\n(in Etalage instance: "' + instance_id + '")');
					}
				});
				$('.dropdownlist').change(function() {
					etalage_show($(this).find('option:selected').attr('class'));
				});
			});
		</script>
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
					<form name="searchMenu" method="post" action="/searchMenuServlet">
						<div class="search">
							<input type="text" class="text" placeholder="菜品名称" name="menuName" id="menuName">
							<input type="submit" value="Search">
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="container">
				<div class="single">
					<div class="row content">
						<div class="col-md-9">
							<div class="single_image">
								<ul id="etalage">
									<li>
										<a href="optionallink.html">
											<img class="etalage_thumb_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
											<img class="etalage_source_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
										</a>
									</li>
									<li>
										<img class="etalage_thumb_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
										<img class="etalage_source_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
									</li>
									<li>
										<img class="etalage_thumb_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
										<img class="etalage_source_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
									</li>
									<li>
										<img class="etalage_thumb_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
										<img class="etalage_source_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
									</li>
									<li>
										<img class="etalage_thumb_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
										<img class="etalage_source_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
									</li>
									<li>
										<img class="etalage_thumb_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
										<img class="etalage_source_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
									</li>
									<li>

								<img class="etalage_thumb_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
								<img class="etalage_source_image" src="images/${requestScope.menu.mimg}" class="img-responsive" alt="" />
								</li>
								</ul>
							</div>
							<div class="single_right" style="margin-top: 40px;">
								<span style="font-size: 22px;color: #6b6b6b;">菜名 </span>
								<p class="m_5">${requestScope.menu.mname}</p>
								<div class="price_single" style="float: left;width: 200px;">
									<span style="font-size: 22px;color: #6b6b6b;">单价：</span>
									<span class="actual1">${requestScope.menu.mprice}</span>
								</div>
								<div class="btn_form" style="margin:10px 0 0 500px;">
									<form action="/addCartServlet?mid=${requestScope.menu.mid}&uid=${sessionScope.user.uid}" method="post">
										<input type="submit" value="加入购物车" title="">
									</form>
								</div>
	
	
							<!----product-rewies---->
							<div class="product-reviwes" style="margin-top:30px;">
								<!--vertical Tabs-script-->
								<!---responsive-tabs---->
								<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
								<script type="text/javascript">
									$(document).ready(function() {
										$('#horizontalTab').easyResponsiveTabs({
											type: 'default', //Types: default, vertical, accordion           
											width: 'auto', //auto or any width like 600px
											fit: true, // 100% fit in a container
											closed: 'accordion', // Start closed if in accordion view
											activate: function(event) { // Callback function if tab is switched
												var $tab = $(this);
												var $info = $('#tabInfo');
												var $name = $('span', $info);
												$name.text($tab.text());
												$info.show();
											}
										});
										$('#verticalTab').easyResponsiveTabs({
											type: 'vertical',
											width: 'auto',
											fit: true
										});
									});
								</script>
								<!---//responsive-tabs---->
								<!--//vertical Tabs-script-->
								<!--vertical Tabs-->
								<div id="verticalTab" style="float: left;">
									
									<div>
										<div>
											<h3>详情：</h3>
											<p style="line-height: 30px;">${requestScope.menu.mintroduce}</p>
										</div>
									</div>
								</div>
								<div class="clearfix"> </div>
								
								
							</div>
							<div class="clearfix"> </div>
						
							</div>
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