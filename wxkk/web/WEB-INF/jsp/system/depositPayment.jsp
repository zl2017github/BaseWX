<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String payPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<title>押金缴纳</title>
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
				<h1 class="head_tit">押金缴纳</h1>
			</div>
			<!--header结束-->
			<div class="content">
				<div class="stepBox">
					<ul class="step-list">
						<li class="step-num">
							<div class="num-box com-bg">1</div>
							<p class="step-word com_font">手机验证</p>
						</li>
						<li class="step-line com-bg"></li>
						<li class="step-num">
							<div class="num-box com-bg">2</div>
							<p class="step-word com_font">身份认证</p>
						</li>
						<li class="step-line com-bg"></li>
						<li class="step-num">
							<div class="num-box com-bg">3</div>
							<p class="step-word com_font">押金缴纳</p>
						</li>
						<li class="step-line"></li>
						<li class="step-num">
							<div class="num-box">4</div>
							<p class="step-word">立即用车</p>
						</li>
					</ul>
				</div>
				<div class="depositBox">
					<div class="left depoTit">押金<span>（可退）</span></div>
					<div class="y depositMoney">${deposit}<span>元</span></div>
				</div>
				<div class="depoRemind">为确保合规用车，请缴纳押金，可随时退回付款账户</div>
				<div class="wxPay">
					<img src="images/wxPay.png" alt="微信支付" class="left wxPay_icon"/>
					<span class="left wxPay_word">微信支付</span>
					<div class="right button-holder">
						<p class="radiobox">
							<input type="radio" id="radio-1-a" name="type" class="regular-radio" checked="checked">
							<label for="radio-1-a"></label>
						</p>
					</div>
					
				</div>
				<div class="comBtnBox">
					<input type="button" value="押金充值" class="comBtn" onclick="sub()"/>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function sub(){
				$.ajax({
			    	type:"post",
			    	url:"<%=payPath%>customer/depositPayment.do",
			    	data:{},
			    	dataType:"json",
			    	success:function(jsonstring){
			    		flag = true;
			    		var success = jsonstring.success;
			    		if(success){
			    			location.href="<%=payPath%>customer/index.do";
			    		}else{
			    			alert(jsonstring.error);
			    		}
			    	}
			    });
			}
		</script>
	</body>
</html>
