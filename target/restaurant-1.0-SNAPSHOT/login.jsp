<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>登录</title>
		<meta charset="utf-8" />
		<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.min.js"></script>
		<link href="css/style.css" rel='stylesheet' type='text/css' />
	</head>

	<body>
		<div class="header">
		<div class="container">
		  <div class="header_top">
			<ul class="phone">
				<li class="phone_left"></li>
				<li class="phone_right">欢迎来到订餐系统</li>
				<div class="clearfix"></div>
			</ul>
			
			
			<div class="clearfix"></div>
		</div>
		<div class="header_bottom">
		  <div class="header_nav">
      		<div class="logo">
				<a href="Main.jsp"><img src="images/logo.png" alt=""/><br></a>
			 </div>
			 <nav class="navbar navbar-default menu" role="navigation"><h3 class="nav_right"><a href="Main.jsp"><img src="images/logo.png" class="img-responsive" alt=""/></a></h3>
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
				<!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav menu1">
										<li><a href="Main.jsp" class="active">首页</a></li>
										<li><a href="orders.jsp">网上订餐</a></li>
										<li><a href="#">订餐帮助</a></li>
										<li><a href="#">客服中心</a></li>
										<li><a href="contact.html">联系我们</a></li>
									</ul>
		          <ul class="login">
		          	<a href="login.jsp"><li class="login_top"><i class="sign"> </i><span>登录</span></li></a>
		            <a href="register.jsp"><li class="login_bottom"><i class="register"> </i><span>注册</span></li></a>
		          </ul>
			      <div class="clearfix"></div>
			    </div><!-- /.navbar-collapse -->
			  </div><!-- /.container-fluid -->
			</nav>
            <div class="clearfix"></div>
           </div>
            
		 </div>  
		</div>
	</div>
		<div class="main">
		<div class="container">
		  <div class="Product_top">
		   <div class="row content">
		   	<div class="col-md-3">
		   	   
		   	 </div>
		     <div class="col-md-9">
		   	  
	          <div class="account_grid">
			   
			   <div class="col-md-6 login-right" style="margin-left: 25%;height: 270px;">
				   <p style="color: red">${requestScope.msg}</p>
			  	<form method="post" action="/loginServlet" name="login">
				  <div>
					<span style="margin: 20px 0 5px 3px;">用户名<label>*</label></span>
					<input type="text" name="u_name" id="u_name">
				  </div>
				  <div>
					<span style="margin: 20px 0 5px 3px;" >密码<label>*</label></span>
					<input type="text" name="u_pwd" id="u_pwd">
				  </div>
				  
				  <div style="margin: 20px 0 0 3px;">
				  <input style="float: left;" type="checkbox" value="7"/><span style="float: left;">自动登录</span>
				  <input style="float:right;margin-right:25px;    padding: 3px 30px;font-size: 16px;" type="submit" value="登录">
				  	</div>
				</form>
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
				 <li><a href=""> <i class="tw"> </i> </a></li>
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
			  	<li class="secure_img"><img src="images/secure.png" alt=""/></li> 
			  	<li class="secure_desc">Lorem ipsum dolor coadipiscing</li>
			  	<div class="clearfix"> </div>
			  </ul>
			  <ul class="secure">
			  	<li class="secure_img"><img src="images/order.png" alt=""/></li> 
			  	<li class="secure_desc">Lorem ipsum dolor coadipiscing</li>
			  	<div class="clearfix"> </div>
			 </ul>
		   </div>
		   <div class="clearfix"> </div>
		   <div class="copy">
		    <p>Copyright &copy; 2014.Company name All rights reserved.<a target="_blank" href="http://www.aspku.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
		</div>
	   </div>
	</div>
	</body>

</html>