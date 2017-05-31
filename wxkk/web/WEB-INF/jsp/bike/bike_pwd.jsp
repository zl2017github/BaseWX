<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String bikePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>E.T单车</title>
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
			<h1 class="head_tit">KAKE单车</h1>
		</div>
		<!--header结束-->	
		<div class="content">
			<img src="images/add-code.png" alt="单车开锁位置图片" class="lockPosi"/>
			<p class="bicNumBox">编号为<span class="bicNumber">${bike.bikeNum }</span>的解锁码</p>
			<ul class="lockPassList">
				<li><span class="lockNum">${bikepwd[1] }</span></li>
				<li><span class="lockNum">${bikepwd[2] }</span></li>
				<li><span class="lockNum">${bikepwd[3] }</span></li>
				<li><span class="lockNum">${bikepwd[4] }</span></li>
			</ul>
			<p class="lockRemind"><span class="countDown">120</span>秒倒计时开始计费，请检查单车是否有损坏，如果损坏，请<a href="<%=bikePath%>bike/toReport.do?historyId=${historyId}" class="immeRepair">立即报修</a></p>
		</div>
	</div>
	<!--wrapper结束-->
	<script type="text/javascript">
		$(function(){
			setInterval(function() { 
				settime();
			}, 1000); 
		});
		
		var countdown = 5; 
		function settime() { 
			if (countdown == 0) { 
				location.href = "<%=bikePath%>/bike/toBikeFinish.do?historyId=${historyId}";
			} else { 
				$(".countDown").html(countdown);
				countdown = countdown - 1; 
			}
		} 
    </script>
</body>
</html>