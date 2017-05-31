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
    <title>我的行程</title>
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
			<h1 class="head_tit">我的行程</h1>
		</div>
		<!--header结束-->	
		
		<div class="content">
			<div class="con">
				<ul class="tripList">
					<c:forEach items="${historyList}" var="history">
						<li>
							<a>
								<ul class="tripDetList">
									<li>
										<p class="tripD_left">骑行消费：</p>
										<p class="tripD_right consumeNum">¥${history.price }</p>
									</li>
									<li>
										<p class="tripD_left">开始时间：</p>
										<p class="tripD_right"><fmt:formatDate value="${history.beginTime }" pattern="yyyy-MM-dd HH:mm:ss" /></p>
									</li>
									<li>
										<p class="tripD_left">结束时间：</p>
										<p class="tripD_right"><fmt:formatDate value="${history.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></p>
									</li>
									<li>
										<p class="tripD_left">开始位置：</p>
										<p class="tripD_right">${history.beginLocation }</p>
									</li>
									<li>
										<p class="tripD_left">结束位置：</p>
										<p class="tripD_right">${history.endLocation }</p>
									</li>
									<li>
										<p class="tripD_left">单车编号：</p>
										<p class="tripD_right bicycleNum">${history.bikeNum }</p>
									</li>
								</ul>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
	</div>
	<!--wrapper结束-->
	<div class="cover"></div>
	<script type="text/javascript">
    </script>
    </div>
</body>
</html>