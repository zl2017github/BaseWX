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
    <title>提醒详情</title>
    <link rel="stylesheet" href="static/css/reset.css" />
	<link rel="stylesheet" href="static/css/common.css" />
	<link rel="stylesheet" href="static/css/style.css" />
	<script type="text/javascript" src="static/js/jquery-1.7.1.min.js"></script></script>
	<script type="text/javascript" src="static/js/common.js"></script></script>
	
</head>
<body>
	<div class="wrapper">
		<!--header开始-->
		<div class="headerBox">
			<img src="images/backW.png" alt="" class="back_icon" onclick="javascript:history.go(-1)"/>
			<!--<img src="images/f-page.jpg" alt="" class="logo"/>-->
			<h1 class="head_tit">KAKE单车</h1>
			<!--<a href="#" class="head_notice"><img src="images/notice2.png" class="noticeIcon noticeClick"/></a>-->
			
		</div>
		<!--header结束-->	
		<div class="content">
			<img src="images/focus1.png" alt="" class="focusImg"/>
			<div class="con">
				<ul class="businessDet">
					<li>
						<p class="businessL">商家名称：</p>
						<p class="businessR">凯克网络技术有限公司</p>
					</li>
					<li>
						<p class="businessL">联系方式：</p>
						<p class="businessR"><a href="tel:13592684467" style="color:#ef3c3c;text-decoration:none">13592684467</a>/<a href="tel:18501002130" style="color:#ef3c3c;text-decoration:none">18501002130</a></p>
					</li>
				</ul>
				<div class="noticeExplain">
					<div class="noticeExplain">
					<h1 class="explainTit">使用说明</h1>
					<p>
						用户通过公众号KAKE后，进行手机号注册便可使用KAKE所提供的自行车租赁及相关衍生服务项目。用户通过扫描车身二维码进入公众号，点击【我要骑车】租赁KAKE车辆，在使用行为完成后，点击公众号中【结算】按钮，并使用进行租赁费用支付。<br/>
						用户单次最长租车时间为24小时，若用户实际需求用车时间超过24小时，可在还车结算后再次租车。<br/>
						1) 计费标准<br/>
							收费标准如下：<br/>
							1元/次（租车时间每小时计费一次）<br/>
							用户应按照KAKE通过微信、APP、微博、官网等渠道公布的最新收费标准支付租车费用。KAKE会根据市场需求更新收费标准，用户应注意查看和关注。<br/>
						2) 约车计费标准<br/>
							约车时间&nbsp;&nbsp;收费标准<br/>
							1小时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1元<br/>
							2小时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2元<br/>
							3小时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3元<br/>
							4小时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4元<br/>
							5小时以上	5元<br/>
						3) 缴费和结算方法<br/>
							缴纳保证金：用户可以通过微信支付公众号中缴纳保证金。<br/>
							结算租车费用：用户结算租车费用时，KAKE服务系统在用户账户余额充足的情况下将直接扣除相应数额。<br/>
					</p>
					<h1 class="explainTit">抵扣说明：</h1>
					<p>用户在KAKE公寓入住可抵扣房租，用户在其他公寓不可抵扣房租。</p>
					<h1 class="explainTit">如何抵扣：</h1>
					<p>用户每次骑行将会按时计费，每小时1元，超过1小时则累加费用，按照超出1元/小时收费，最高一次收费骑行费用5元。用户点击骑行结束后则会显示一个抵扣券，抵扣券金额为行程结束后的计费金额相同，例如：骑行结束后计费为1元，则抵扣券金额为1元，以此类推。（注：抵扣券只可抵扣房租，不可提现，也不得用于其他优惠抵扣！）</p>
					<h1 class="explainTit">抵扣方式：</h1>
					<p>用户点击【我的】进入个人资料界面，随后点击【抵扣券】则会显示一张抵扣券或多张抵扣券，均以用户骑行后结束计费累加的金额为主。用户需要点击用券数量及金额来抵扣房租等同的金额。</p>
				</div>
				</div>
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