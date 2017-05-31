<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String aboutPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="aplus-terminal" content="1"/>
	<meta name="apple-mobile-web-app-title" content="SHOPDZ"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>关于我们</title>
    <link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	
</head>
<body>
	<div class="wrapper">
		<!--header开始-->
		<div class="headerBox">
			<img src="images/backW.png" alt="" class="back_icon" onclick="javascript:history.go(-1)"/>
			<!--<img src="images/f-page.jpg" alt="" class="logo"/>-->
			<h1 class="head_tit">关于我们</h1>
			<!--<a href="#" class="head_notice"><img src="images/notice2.png" class="noticeIcon noticeClick"/></a>-->
			
		</div>
		<!--header结束-->	
		<div class="content">
			<div class="personalHead aboutUs_head">
				<div class="perPortrait">
					<img src="images/et_black.png" alt="" class="perImg" onclick="javascript:history.go(-1)"/>
				</div>
				<p class="nickname">KAKE单车</p>
				<p class="et_edition">V1.0.0</p>
			</div>
			<div class="perCon">
				<ul class="perLinkList aboutList">
					<li class="myTrip">
						<a>
							<p class="perLinkTit">商家名称：</p>
							<p class="perLinkDet">凯克网络技术有限公司</p>
						</a>
					</li>
					<li class="myTrip">
						<a>
							<p class="perLinkTit">微信公众号:</p>
							<p class="perLinkDet">KAKE生活圈</p>
						</a>
					</li>
					<li class="myWallet">
						<a>
							<p class="perLinkTit">联系电话:</p>
							<p class="perLinkDet"><a href="tel:13592684467" style="color:#ef3c3c;text-decoration:none">13592684467</a>/<a href="tel:18501002130" style="color:#ef3c3c;text-decoration:none">18501002130</a></p>
						</a>
					</li>
					<li class="bondCoupon">
						<a>
							<p class="perLinkTit">电子邮箱:</p>
							<p class="perLinkDet">18501002130@163.com</p>
						</a>
					</li>
					<li class="bondCoupon" onclick="javascript:location.href='<%=aboutPath%>customer/deduction/description.do'">
						<a>
							<p class="perLinkTit">费用说明:</p>
							<p class="perLinkDet">点击查看</p>
						</a>
					</li>
					<li class="bondCoupon" onclick="javascript:location.href='<%=aboutPath%>customer/toMyInstructions.do'">
						<a>
							<p class="perLinkTit">用户须知:</p>
							<p class="perLinkDet">点击查看</p>
						</a>
					</li>
				</ul>
			</div>
		</div>
	<!--wrapper结束-->
	<div class="cover"></div>
	<script type="text/javascript">
    </script>
</body>
</html>