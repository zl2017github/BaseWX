<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String customerPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>个人中心</title>
    <link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	
</head>
<body>
	<div class="wrapper">
		<!--header开始-->
		<div class="headerBox">
			<img src="images/backW.png" alt="" class="back_icon" onclick="javascript:history.go(-1)"/>
			<h1 class="head_tit">KAKE单车</h1>
			
		</div>
		<!--header结束-->	
		<div class="content">
			<div class="personalHead">
				<div class="perPortrait">
					<img src="images/et_black.png" alt="" class="perImg" onclick="location.href='<%=customerPath%>customer/index.do'"/>
				</div>
				<p class="nickname">${customer.mobile}</p>
			</div>
			<div class="perCon">
				<!--用户没有缴纳押金和认证，不显示这一块-->	
					<ul class="perLinkList">
						<li class="myTrip" onclick="javascript:location.href='<%=customerPath%>customer/toMyTrip.do?customerId=${customer.id}'">
							<a>
								<p class="perLinkTit">我的行程</p>
								<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
							</a>
						</li>
						<li class="myWallet" onclick="javascript:location.href='<%=customerPath%>customer/toMyWallet.do?customerId=${customer.id}'">
							<a>
								<p class="perLinkTit">我的钱包</p>
								<p class="perLinkDet">
									<c:if test="${wallet.remainingPrice == null }">¥0.00</c:if>
									<c:if test="${wallet.remainingPrice != null }">¥${wallet.remainingPrice }</c:if>
								</p>
								<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
							</a>
						</li>
						<li class="bondCoupon" onclick="javascript:location.href='<%=customerPath%>customer/toMyDeduction.do?customerId=${customer.id}'">
							<a>
								<p class="perLinkTit">抵扣券</p>
								<p class="perLinkDet">
									<c:if test="${deduction.notDeductionSum == null }">¥0.00</c:if>
									<c:if test="${deduction.notDeductionSum != null }">¥${deduction.notDeductionSum }</c:if>
								</p>
								<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
							</a>
						</li>
						<li class="myMessage" onclick="javascript:location.href='<%=customerPath%>customer/toMyNews.do'">
							<a>
								<p class="perLinkTit">我的消息</p>
								<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
							</a>
						</li>
					</ul>
					
					<ul class="perLinkList marginT1">
						<li class="perAboutUs" onclick="javascript:location.href='<%=customerPath%>bike/toReport.do'">
							<a>
								<p class="perLinkTit">车辆报修</p>
								<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
							</a>
						</li>
						<li class="perAboutUs" onclick="javascript:location.href='<%=customerPath%>customer/toFeedBack.do'">
							<a>
								<p class="perLinkTit">意见反馈</p>
								<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
							</a>
						</li>
					</ul>
				
				<ul class="perLinkList marginT1">
					<li class="useGuide" onclick="javascript:location.href='<%=customerPath%>customer/deduction/description.do'">
						<a>
							<p class="perLinkTit">抵扣说明</p>
							<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
						</a>
					</li>
					<li class="perAboutUs" onclick="javascript:location.href='<%=customerPath%>kk/toAbout.do'">
						<a>
							<p class="perLinkTit">关于我们</p>
							<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
						</a>
					</li>
					<li class="perAboutUs" onclick="javascript:location.href='<%=customerPath%>customer/toMyInstructions.do'">
						<a>
							<p class="perLinkTit">用户须知</p>
							<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
						</a>
					</li>
				</ul>
				
			</div>
			<div class="sign-btn1">
				<input type="button" value="退出登录" class="signbtn radius5 send-out-bg" id="exitBtn"/>
			</div>
		</div>
	</div>
	<!--wrapper结束-->
	<div class="cover"></div>
	<script type="text/javascript">
		
		$(function(){
			
			$("#exitBtn").bind('click',function(){
				//退出登录
		    	$.ajax({
			    	type:"post",
			    	url:"<%=customerPath%>system/loginOut.do",
			    	data:{},
			    	dataType:"json",
			    	success:function(jsonstring){
			    		var msg = jsonstring.success;
			    		if(msg){
			    			location.href="<%=customerPath%>/system/toLogin.do";
			    		}else{
			    			alert(jsonstring.error);
			    		}
			    	}
			    });
		    });
		});
    </script>
</body>
</html>