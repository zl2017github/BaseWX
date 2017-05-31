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
    <title>充值</title>
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
			<h1 class="head_tit">充值</h1>
		</div>
		<!--header结束-->	
		<div class="content">
			<div class="surplusMoney">
				我的余额（元）
				<p class="surplusNum">¥
					<c:if test="${wallet.remainingPrice == null}">0</c:if>
					<c:if test="${wallet.remainingPrice != null}">${wallet.remainingPrice}</c:if>
				</p>
			</div>
			<h1 class="chageTit">充值金额</h1>
			<ul class="chargeList">
				<li class="chaActive">
					<div class="chargeLBox">
						<p class="actualCharge">充值20元</p>
						<p class="obtainCharge">得20元</p>
					</div>
					
				</li>
				<li>
					<div class="chargeLBox">
						<p class="actualCharge">充值50元</p>
						<p class="obtainCharge">得60元</p>
					</div>
					
				</li>
				<li>
					<div class="chargeLBox">
						<p class="actualCharge">充值100元</p>
						<p class="obtainCharge">得150元</p>
					</div>
					
				</li>
			</ul>
			<div class="comBtnBox">
				<a href="#" class="comBtn">立即充值</a>
			</div>
			<p class="chargeRemind">点击立即充值，即表示您已同意<a class="chargeAgree">《充值活动协议》</a></p>
		</div>
	</div>
	<!--wrapper结束-->
	<script type="text/javascript">
		$(function(){
			$('.chargeList li').on('click',function(){
				$(this).addClass('chaActive').siblings().removeClass('chaActive');
			})
		})
		
		
    </script>
</body>
</html>