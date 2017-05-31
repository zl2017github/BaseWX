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
    <title>当前耗时</title>
    <link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js" > </script>  
	
</head>
<body>
	<input type="hidden" value="${bikeHistory.id }" name="historyId" id="historyId"/>
	<div class="wrapper">
		<!--header开始-->
		<div class="headerBox">
			<h1 class="head_tit">结束骑行</h1>
		</div>
		<!--header结束-->	
		<div class="content">
			<p class="curTimeTit">开始时间</p>
			<p class="curTimeNum"><fmt:formatDate value="${bikeHistory.beginTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></p>
			<p class="curTimeTit">开始位置</p>
			<p class="curTimeNum">${bikeHistory.beginLocation }</p>
			<div class="comBtnBox">
				<button class="comBtn" onclick="finishBike()">结束骑行</button>
			</div>
			<p class="overRemind">将车锁好后，请把密码打乱</p>
		</div>
	</div>
	
	<!--消息提示-->
	<div class="cover"></div>
	<div class="noticeAlert">
		<div class="noticeTit">
			<span class="noticeClose">X</span>
		</div>
		<div class="noticeCon">
			恭喜您获得了￥<span id="price"></span>抵扣券
			<p class="noticeLink"><a href="<%=bikePath%>customer/deduction/description.do" class="noticeDet_link">详细内容</a></p>
		</div>
	</div>	
	<!--wrapper结束-->
	<script type="text/javascript">
	$(function(){
		$('.noticeClose').on('click',function(){
			$('.cover').hide();
			$('.noticeAlert').hide();
			location.href="<%=bikePath%>customer/index.do";
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
		if(addr == ""){
	    	alert("获取位置失败，请尝试    苹果用户：（设置） -> （通用） -> （还原）-> （还原位置和隐私）.安卓用户：浏览器里的设置  -> 清除浏览数据 -> 清除浏览器缓存。");
	    	return;
		}
	}
	
	//结束骑行
	function finishBike(){
		if(address == undefined){
			alert("获取定位失败，请稍后重试!");
			return false;
		}
		//id
		var historyId = $("#historyId").val();
		//结束
    	$.ajax({
	    	type:"post",
	    	url:"<%=bikePath%>bike/finishBike.do",
	    	data:{historyId:historyId,address:address,longitude:lng,latitude:lat},
	    	dataType:"json",
	    	success:function(jsonstring){
	    		var msg = jsonstring.success;
	    		if(msg){
	    			var price = jsonstring.price;
	    			$("#price").html(price);
	    			//显示抵扣券
	    			$('.cover').show();
	    			$('.noticeAlert').show();
	    		}else{
	    			alert(jsonstring.error);
	    		}
	    	}
	    });
	}
    </script>
</body>
</html>