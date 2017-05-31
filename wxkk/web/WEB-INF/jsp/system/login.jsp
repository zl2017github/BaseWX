<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String loginPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>登录</title>
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
			<h1 class="head_tit">KAKE单车</h1>
		</div>
		<!--header结束-->	
		<div class="content">
			<a><img src="images/loginImg.png" class="sign-img1"/></a>
			<div class="signbox">
				<div class="signIn-box account">
					<img src="images/accunt.png" class="sign-icon">
					<input type="tel" name="userMobile" id="userMobile" placeholder="请输入手机号" id="register-phone" class="sign-inp phone"/>
					<span class="dele"></span>
				</div>
				<div class="signIn-box passW">
					<img src="images/pwd.png" class="sign-icon"/>
					<input type="tel" name="code" id="code" style="ime-mode:disabled;" placeholder="获取验证码" id="register-pword" class="sign-inp pword"/>
					<p id="sendCode" class="send-out send-out-bg">获取验证码</p>
				</div>
				<%-- <div class="signIn-box account">
					<img src="images/verification.png" class="sign-icon">
					<input type="number" value="${wechatId }" style="ime-mode:disabled;" placeholder="请输入图形验证码" id="register-verification" class="sign-inp"/>
					<img id="click_verify" rel-src="" alt="图形验证码" class="verification">
				</div> --%>
				<div class="sign-btn1">
					<input type="button" value="登录" class="signbtn radius5 send-out-bg" id="signBtn"/>
					<%-- <div class="forget-box">
						<p class="register right" id="register1" onclick="javascript:location.href='<%=loginPath%>system/toRegister.do'">注册账号？</p>
					</div> --%>
				</div>
				<div class="errorRemind">输入的验证码错误</div>
				<input type="hidden" value="${wechatId }" class="wechatId">
			</div>
		</div>
	</div>
	<!--wrapper结束-->
	<script type="text/javascript">
		$(function(){
			$('.sign-inp').bind({ 
				keyup:function(){
					if (this.value != ''){
						$(this).next('.dele').css('display','block');
					} else {
						this.value = this.defaultValue; 
					}
				}, 
				blur:function(){ 
					if (this.value == ""){
						this.value = this.defaultValue; 
						$(this).next('.dele').css('display','none');
					} 
				} 
			});
			$('.dele').bind('click',function(){
				$(this).prev('.sign-inp').val("");
				$(this).css('display','none');
				$('#signBtn').removeClass('send-out-bg');
			});
			
		    
		    //获取验证码
		    var issend = true;
			$("#sendCode").bind('click',function(){
				
				if(issend){
					var userMobile = $("#userMobile").val();
					var myreg = /^1[34578]\d{9}$/;
					if (!myreg.test(userMobile)) {
						flag = true;
						alert("手机号输入有误!");
						return false;
					}
					
					//发送验证码
					time(this);
				    $(this).removeClass('send-out-bg');
			    	$.ajax({
				    	type:"post",
				    	url:"<%=loginPath%>system/ajaxGetCode.do",
				    	data:{mobile:userMobile},
				    	dataType:"json",
				    	success:function(jsonstring){
				    		flag = true;
				    		var msg = jsonstring.success;
				    		alert(jsonstring.code);
				    	}
				    });
				}
		    });
			
			//登录
			var flag = true;
			$("#signBtn").bind('click',function(){
				if(flag){
					flag = false;
					//手机号
					var userMobile = $("#userMobile").val();
					var code = $("#code").val();
					var wechatId = $(".wechatId").val();
					var myreg = /^1[34578]\d{9}$/;
					if (!myreg.test(userMobile)) {
						flag = true;
						alert("手机号输入有误!");
						return false;
					}
					//登录
			    	$.ajax({
				    	type:"post",
				    	url:"<%=loginPath%>system/login.do",
				    	data:{userMobile:userMobile,code:code,wechatId:wechatId},
				    	dataType:"json",
				    	success:function(jsonstring){
				    		flag = true;
				    		var msg = jsonstring.success;
				    		if(msg){
				    			//登录成功，保存用户信息到cookie,有效期360天
				    			$.cookie('userMobile',userMobile, {expires : 360 });
				    			location.href="<%=loginPath%>/customer/index.do";
				    		}else{
				    			alert(jsonstring.error);
				    		}
				    	}
				    });
				}
		    });
		});
		
		//点击发送验证码倒计时
		var wait=90;  
		function time(o) {  
			//alert(wait);
	        if (wait == 0 || wait < 0 ) {  
	           // o.removeAttribute("disabled");            
	            o.innerHTML="获取验证码";  
	            wait = 90; 
	            $("#sendCode").addClass('send-out-bg');
	            $("#sendCode").bind('click',function(){
	            	time(this);
					$(this).removeClass('send-out-bg');
	            });
	        } else{  
	           // o.setAttribute("disabled", true);  
	           $("#sendCode").unbind("click");
	            o.innerHTML="重新发送(<span style='color:#ff280e;font-weight:700'>" + wait + "</span>)";
	            wait--;  
	            setTimeout(function() {  
	                time(o);
	            },  
	            1000);  
	        }  
	    }
				
		//加载用户手机号密码
		$(function(){
			var userMobile = $.cookie('userMobile');
			if(userMobile != ''){
				$("#userMobile").val(userMobile);
			}
		});
    </script>
</body>
</html>