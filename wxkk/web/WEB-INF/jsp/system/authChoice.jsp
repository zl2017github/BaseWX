<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String choicePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<title>选择认证方式</title>
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
				<h1 class="head_tit">选择认证方式</h1>
			</div>
			<!--header结束-->
			<div class="content">
				<div class="authBox">
					<a onclick="javascript:location.href='<%=choicePath %>system/toNameAuth.do'">
						<img src="images/name.png" alt="" class="authType_icon"/>
						<span class="authType_word">姓名认证</span>
					</a>
				</div>
				<div class="authBox">
					<a onclick="javascript:location.href='<%=choicePath %>system/toCertification.do'">
						<img src="images/id.png" alt="" class="authType_icon"/>
						<span class="authType_word">身份证认证</span>
					</a>
				</div>
			</div>
		</div>
	</body>
</html>
