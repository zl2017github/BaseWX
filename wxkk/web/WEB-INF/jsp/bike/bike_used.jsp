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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <meta HTTP-EQUIV="Expires" CONTENT="0">
	<base href="<%=basePath%>">
    <title>我要骑车</title>
    <link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/swiper.min.js"></script>
	
	<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js" > </script>  
	<%--<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>--%>
</head>
<body>
	<div class="wrapper">
		<!--header开始-->
		<div class="headerBox">
			<img src="images/backW.png" alt="" class="back_icon" onclick="javascript:history.go(-1)"/>
			<h1 class="head_tit">我要骑车</h1>
		</div>
		<!--header结束-->
		<div class="content">
			<div class="bicycleBox">
				<img src="images/et_black.png" class="bicycleImg"/>
				<p class="billingRules">计费规则：<span class="billingPrice">1</span>元/小时</p>
				<div class="bicycleDet">
					<input type="tel" placeholder="请输入单车编号" name="numScreen" class="left bicEntryNum" id="bicNumbered" maxlength="8"/>
					<a><button class="right bicycleBtn" onclick="usedBike()">确认</button></a>
				</div>
				<p class="bicRemind">输入单车编号，获取解锁密码</p>
				<p class="tripD_left">您所在的位置：</p>
				<p class="tripD_right" id="bicycleLocation"></p>
			</div>
			
			<!-- <div class="keyboard-box">
				<div class="keyboard-left">
					<ul class="num-list">
						<li onclick="command(1)"><a>1</a></li>
						<li onclick="command(2)"><a>2</a></li>
						<li onclick="command(3)" class="borderR-none"><a>3</a></li>
						<li onclick="command(4)"><a>4</a></li>
						<li onclick="command(5)"><a>5</a></li>
						<li onclick="command(6)" class="borderR-none"><a>6</a></li>
						<li onclick="command(7)"><a>7</a></li>
						<li onclick="command(8)"><a>8</a></li>
						<li onclick="command(9)" class="borderR-none"><a>9</a></li>
					</ul>
				</div>
				<div class="keyboard-right">
					<div class="inp-dele" onclick="del()">
						<img src="images/dele.png" alt="" class="dele-icon"/>
					</div>
					<div onclick="command(0)" class="zeroBtn"><a>0</a></div>
					<a><div class="pay-sure" onclick="usedBike()">确认</div></a>
				</div>
			</div> -->
			
		</div>
	</div>
	<!--wrapper结束-->
	<div class="cover"></div>
	<!--消息提示-->
	<div class="noticeAlert">
		<div class="noticeTit">
			<span class="noticeClose">X</span>
		</div>
		<div class="noticeCon">
			最新消息及优惠内容呈现
			<p class="noticeLink"><a href="#" class="noticeDet_link">详细内容</a></p>
		</div>
	</div>	
		
	<script type="text/javascript">
		var num=0,result=0,numshow="0"; 
		var operate=0; //判断输入状态的标志 
		var calcul=0; //判断计算状态的标志 
		var quit=0; //防止重复按键的标志 
		function command(num){ 
			var str=String(document.getElementById('bicNumbered').value); //获得当前显示数据 
			str=(str!="") ? ((operate=="") ? str : "") : "";
			str=str + String(num); //给当前值追加字符 
			document.getElementById('bicNumbered').value=str; //刷新显示 
			if($('.bicNumbered').val().length != 0){
				$('.pay-sure').css('background','#00a0e9');
			} 
//			$('.cursor').show;
			operate=""; //重置输入状态 
			quit=""; //重置防止重复按键的标志 
		} 
		function dzero(){ 
			var str=String(document.getElementById('bicNumbered').value); 
			str=(str!="") ? ((operate=="") ? str + "" : "") : ""; 
			document.getElementById('bicNumbered').value=str; 
			operate=""; 
		} 
		
		function del(){ //退格 
			var str=String(document.getElementById('bicNumbered').value); 
			str=(str!="") ? str : ""; 
			str=str.substr("",str.length-1); 
			if(str.length == 0) {
				$('.pay-sure').css('background','#8bcce9');
			}
			str=(str!="") ? str : ""; 
			document.getElementById('bicNumbered').value=str; 
		} 
		
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
			$("#bicycleLocation").html(address);
			if(addr == ""){
		    	alert("获取位置失败，请尝试    苹果用户：（设置） -> （通用） -> （还原）-> （还原位置和隐私）.安卓用户：浏览器里的设置  -> 清除浏览数据 -> 清除浏览器缓存。");
		    	return;
			}
		}
		
		//确认用车
		var flag = true;
		function usedBike(){
			//车牌号
			var bicNumbered = $("#bicNumbered").val();
			if (bicNumbered.length<1) {
				alert("请输入车牌号!");
				return false;
			}
			if(address == undefined){
				alert("获取定位失败，请稍后重试!");
				return false;
			}
			if(flag){
				flag = false;
				//登录
		    	$.ajax({
			    	type:"post",
			    	url:"<%=bikePath%>bike/usedBike.do",
			    	data:{bikeNum:bicNumbered},
			    	dataType:"json",
			    	success:function(jsonstring){
			    		flag = true;
			    		var msg = jsonstring.success;
			    		if(msg){
			    			location.href="<%=bikePath%>/bike/toBikePwd.do?bikeNum="+bicNumbered+"&address="+address+"&longitude="+lng+"&latitude="+lat;
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