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
    <title>抵扣券</title>
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
			<h1 class="head_tit">抵扣券</h1>
		</div>
		<!--header结束-->	
		<div class="content">
			<div class="textC">
				<img src="images/money2.png" alt="" class="moneyIcon deduMoney"/>
			</div>
			<div class="surplusMoney deduction">
				<p class="surplusNum">
					<c:if test="${deduction.notDeductionSum == null }">¥0.00</c:if>
					<c:if test="${deduction.notDeductionSum != null }">¥${deduction.notDeductionSum }</c:if>
				</p>
				共计可抵扣房租的总额
			</div>
			<ul class="perLinkList deductionDet">
				<c:forEach items="${historyList}" var="history">
					<li class="myWallet" onclick="deductionDes('${history.price }','<fmt:formatDate value="${history.beginTime }" pattern="yyyy-MM-dd HH:mm:ss" />','<fmt:formatDate value="${history.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />','${history.beginLocation }','${history.endLocation }','${history.bikeNum }')">
						<p class="perLinkTit">可抵扣房租金额</p>
						<p class="perLinkDet">¥${history.price }</p>
						<img src="images/images/jtr.png" alt="" class="perLinkJt"/>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<!--wrapper结束-->
	<div class="cover"></div>
	<!--消息提示-->
	<div class="noticeAlert">
		<div class="noticeTit">
			<span class="noticeClose">X</span>
		</div>
		<div class="noticeCon" style="padding-left:20px">
		</div>
	</div>	
	<script type="text/javascript">
		$(function(){
			$('.noticeClose').on('click',function(){
				$('.cover').hide();
				$('.noticeAlert').hide();
			});	
		});
		
		//查看抵扣详情信息
		function deductionDes(price,beginTime,endTime,beginLocation,endLocation,bikeNum){
			var html = '<a>'+
						'<ul class="tripDetList">'+
								'<li>'+
									'<p class="tripD_left">骑行消费：</p>'+
									'<p class="tripD_right consumeNum">¥'+price+'</p>'+
								'</li>'+
								'<li>'+
									'<p class="tripD_left">开始时间：</p>'+
									'<p class="tripD_right">'+beginTime+'</p>'+
								'</li>'+
								'<li>'+
									'<p class="tripD_left">结束时间：</p>'+
									'<p class="tripD_right">'+endTime+'</p>'+
								'</li>'+
								'<li>'+
									'<p class="tripD_left">开始位置：</p>'+
									'<p class="tripD_right">'+beginLocation+'</p>'+
								'</li>'+
								'<li>'+
									'<p class="tripD_left">结束位置：</p>'+
									'<p class="tripD_right">'+endLocation+'</p>'+
								'</li>'+
								'<li>'+
									'<p class="tripD_left">单车编号：</p>'+
									'<p class="tripD_right bicycleNum">'+bikeNum+'</p>'+
								'</li>'+
							'</ul>'+
						'</a>';
			$('.noticeCon').html(html);
			$('.cover').show();
			$('.noticeAlert').show();
		}
    </script>
</body>
</html>