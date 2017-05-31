<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String reportPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>单车报修</title>
    <link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js" > </script>  
	
</head>
<body>
	<div class="wrapper">
		<!--header开始-->
		<div class="headerBox">
			<img src="images/backW.png" alt="" class="back_icon" onclick="javascript:history.go(-1)"/>
			<h1 class="head_tit">单车报修</h1>
			<!--<a href="#" class="head_notice"><img src="images/notice2.png" class="noticeIcon noticeClick"/></a>-->
			
		</div>
		<!--header结束-->	
		<div class="content">
			<ul class="damageReason">
				<li>
					<input type="hidden" value="1">
					<p class="reasonWord">私锁私用 </p>
					<img src="images/yes.png" alt="" class="choiceReason"/>
				</li>
				<li>
					<input type="hidden" value="2">
					<p class="reasonWord">车辆损坏</p>
					<img src="images/yes.png" alt="" class="choiceReason"/>
				</li>
				<li>
					<input type="hidden" value="3">
					<p class="reasonWord">违规乱停</p>
					<img src="images/yes.png" alt="" class="choiceReason"/>
				</li>
				<li>
					<input type="hidden" value="4">
					<p class="reasonWord">其他</p>
					<img src="images/yes.png" alt="" class="choiceReason"/>
				</li>
			</ul>
			<div class="comBtnBox">
				<a class="comBtn" onclick="report()">车辆报修</a>
			</div>
		</div>
	</div>
	<!--wrapper结束-->
	
		
<script type="text/javascript">
	var reportList = new Array();
	$(function(){
		$('.damageReason>li').on('click',function(){
			$(this).find('.choiceReason').toggle();
			var i = $(this).children("input").val();
			reportList.push(i);
		});
	});
	
	/** 初始化，获取用户的位置*/
	$(function(){
		var geolocation = new qq.maps.Geolocation("OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77", "myapp");  
        if(geolocation){  
            var options = {timeout: 5000};  
            geolocation.getLocation(showPosition, showErr, options);  
        }else{  
            alert("定位尚未加载");  
        }
	});
	//获取地理位置错误
	function showErr(error){
		alert("获取位置失败，请尝试    苹果用户：（设置） -> （通用） -> （还原）-> （还原位置和隐私）.       安卓用户：浏览器里的设置  -> 清除浏览数据 -> 清除浏览器缓存。");
	}
	//获取地理位置成功
	var address;//位置
	var lng;//经度
	var lat;//纬度
	function showPosition(position){
		var nation = position.nation;//国家
		var province = position.province;//省份
		var city = position.city;//市
		var district = position.district;//区
		var addr = position.addr;//详细地址
		lng = position.lng;//经度
		lat = position.lat;//维度
		address = nation+province+city+district+addr;
		if(address == ""){
	    	alert("获取位置失败，请尝试    苹果用户：（设置） -> （通用） -> （还原）-> （还原位置和隐私）.安卓用户：浏览器里的设置  -> 清除浏览数据 -> 清除浏览器缓存。");
	    	return;
		}
	}
	
	/** 报修*/
	function report(){
		var types = reportList.join(",");
		$.ajax({
	    	type:"post",
	    	url:"<%=reportPath%>bike/report.do",
	    	data:{types:types,address:address},
	    	dataType:"json",
	    	success:function(jsonstring){
	    		alert("感谢您的报修！");
	    		location.href="<%=reportPath%>/customer/index";
	    	}
	    });
	}
</script>
</body>
</html>