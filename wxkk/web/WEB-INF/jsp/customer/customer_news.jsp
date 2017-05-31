<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
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
    <title>我的消息</title>
    <link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script></script>
	<script type="text/javascript" src="js/common.js"></script></script>
	
</head>
<body>
	<div class="wrapper">
		<!--header开始-->
		<div class="headerBox">
			<img src="images/backW.png" alt="" class="back_icon" onclick="javascript:history.go(-1)"/>
			<h1 class="head_tit">我的消息</h1>
		</div>
		<!--header结束-->	
		<div class="content">
			<div class="imgCenter">
				<img src="images/newsNull.png" alt="" class="nullImg"/>
				<h4 class="nullTit">您还没有消息哦~~~</h4>
			</div>
		</div>
	</div>
	<!--wrapper结束-->
	<div class="cover"></div>
	<script type="text/javascript">
		
		
		
		
		//焦点图
	//  var swiper = new Swiper('.swiper-container', {
	//      pagination: '.swiper-pagination',
	//      nextButton: '.swiper-button-next',
	//      prevButton: '.swiper-button-prev',
	//      paginationClickable: true,
	//      spaceBetween: 30,
	//      centeredSlides: true,
	//      autoplay: 2500,
	//      autoplayDisableOnInteraction: false
	//  });
		
		
    </script>
</body>
</html>