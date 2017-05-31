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
		<meta charset="UTF-8">
		<meta name="aplus-terminal" content="1"/>
		<meta name="apple-mobile-web-app-title" content="KAKE"/>
		<meta name="apple-mobile-web-app-capable" content="yes"/>
		<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
		<title>房屋信息</title>
		<link rel="stylesheet" href="css/reset.css" />
		<link rel="stylesheet" href="css/common.css" />
		<link rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script></script>
		<script type="text/javascript" src="js/common.js"></script></script>
	</head>
	<body>
		<div class="wrapper bgf9">
			<!--header开始-->
			<div class="headerBox">
				<img src="images/backW.png" alt="" class="back_icon" onclick="javascript:history.go(-1)"/>
				<h1 class="head_tit">房屋信息</h1>
			</div>
			<!--header结束-->
			<div class="content">
				<c:forEach items="${houseList}" var="house">
					<div class="houseDetBox">
						<div class="hoPrice">房屋价格：<span class="hoPriNum">${house.price }</span>元</div>
						<div class="hoPosi">房屋位置：${house.location }</div>
						<div class="hoRemarks">房屋备注：<span class="hoRemWord">${house.note }</span></div>
						<div class="hoOperation">
							<a href="tel:13592684467" class="right comS_Btn">联系房东</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<script type="text/javascript">
			
		</script>
		
	</body>
</html>
