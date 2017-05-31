<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String walletPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>我的钱包</title>
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
			<h1 class="head_tit">我的钱包</h1>
		</div>
		<!--header结束-->	
		<div class="content">
			<div class="textC">
				<img src="images/money.png" alt="" class="moneyIcon"/>
			</div>
			
			<div class="surplusMoney">
				剩余金额
				<p class="surplusNum">¥
					<c:if test="${wallet.remainingPrice == null}">0</c:if>
					<c:if test="${wallet.remainingPrice != null}">${wallet.remainingPrice}</c:if>
				</p>
			</div>
			<div class="comBtnBox">
				<a href="<%=walletPath%>customer/toMyWalletRecharge.do?customer=${wallet.customerId}" class="comBtn">充值</a>
			</div>
			<p class="deposit">我的押金<span class="depositNum">
				<c:if test="${wallet.depositPrice == null}">0</c:if>
				<c:if test="${wallet.depositPrice != null}">${wallet.depositPrice}</c:if>
			</span>元，<a class="depositRefund" onclick="exitDeposit()">押金退款</a></p>
			
			
		</div>
	</div>
	<!--wrapper结束-->
	<div class="cover"></div>
	<script type="text/javascript">
		function exitDeposit(){
			if(confirm("退还押金以后将不能使用单车，是否确定退还押金？")){
				$.ajax({
			    	type:"post",
			    	url:"<%=walletPath%>customer/exitDeposit.do",
			    	data:{},
			    	dataType:"json",
			    	success:function(jsonstring){
			    		flag = true;
			    		var success = jsonstring.success;
			    		if(success){
			    			alert("押金将于三个工作日退还您原有账户，请注意查收!");
			    			location.href="<%=walletPath%>customer/toMy.do";
			    		}else{
			    			alert(jsonstring.error);
			    		}
			    	}
			    });
			}
		}
		
		
    </script>
</body>
</html>