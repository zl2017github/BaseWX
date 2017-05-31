<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String backPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<title>意见反馈</title>
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
				<h1 class="head_tit">意见反馈</h1>
			</div>
			<!--header结束-->
			<div class="content">
				<div class="textarea-box">
					<p class="textcount">剩余<span id="textCount">500</span>个字</p> 
					<textarea name="textarea" id="TextArea1" class="options" onkeyup="words_deal();" onfocus="if(this.value=='期待您的意见') {this.value='';}this.style.color='#333';" onblur="if(this.value=='') {this.value='期待您的意见';this.style.color='#333';}"maxlength="500">期待您的意见</textarea> 
				</div>
				<div class="comBtnBox">
					<input type="button" value="提交" class="comBtn" onclick="sub()"/>
				</div>
				
			</div>
		</div>
		<script type="text/javascript">
			//限制文本域只能输入500字，并提示可输入的剩余字数
			function words_deal(){ 
				var curLength=$("#TextArea1").val().length; 
				if(curLength>500){ 
					var num=$("#TextArea1").val().substr(0,500); 
					$("#TextArea1").val(num); 
					//alert("超过字数限制，多出的字将被截断！" ); 
				} 
				else{ 
					$("#textCount").text(500-$("#TextArea1").val().length); 
				} 
			} 
			
			/** 提交*/
			function sub(){
				var text = $("#TextArea1").val();
				$.ajax({
			    	type:"post",
			    	url:"<%=backPath%>customer/feedBack.do",
			    	data:{text:text},
			    	dataType:"json",
			    	success:function(jsonstring){
			    		alert("感谢您的意见反馈！");
			    		location.href="<%=backPath%>customer/toMy.do";
			    	}
			    });
			}
		</script>
		
	</body>
</html>
