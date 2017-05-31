<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String registerPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>注册</title>
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
			<a onclick="history.go(-1)"><img src="images/backW.png" alt="" class="back_icon"/></a>
			<h1 class="head_tit">注册</h1>
		</div>
		<!--header结束-->	
		<div class="content">
			<a><img src="images/loginImg.png" class="sign-img1"/></a>
			<div class="signbox">
				<div class="signIn-box account">
					<img src="images/accunt.png" class="sign-icon">
					<input type="number" placeholder="请输入手机号" id="register-phone" class="sign-inp phone"/>
					<span class="dele"></span>
				</div>
				<div class="signIn-box passW">
					<img src="images/pwd.png" class="sign-icon"/>
					<input type="number" placeholder="获取验证码" id="register-pword" class="sign-inp pword"/>
					<p id="sendCode" class="send-out">获取验证码</p>
				</div>
				<div class="sign-btn1">
					<input type="button" value="下一步" class="signbtn radius5 send-out-bg" id="signBtn" onclick="next()"/>
				</div>
				<div class="errorRemind">输入的验证码错误</div>
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
			//点击发送验证码倒计时
			var wait=5;  
			function time(o) {  
		        if (wait == 0 || wait < 0 ) {  
		           // o.removeAttribute("disabled");            
		            o.innerHTML="获取验证码";  
		            wait = 5; 
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
			 $("#sendCode").bind('click',function(){
		    	time(this);
				$(this).removeClass('send-out-bg');
		    });
			
		});
		
		/** 下一步，提交申请*/
		function next(){
			var mobile = $("#register-phone").val();
			var code = $("#register-pword").val();
			var myreg = /^1[34578]\d{9}$/;
			if (!myreg.test(mobile)) {
				alert("手机号输入有误!");
				return false;
			}
			//注册
	    	$.ajax({
		    	type:"post",
		    	url:"<%=registerPath%>system/toNextRegister.do",
		    	data:{mobile:mobile,code:code},
		    	dataType:"json",
		    	success:function(jsonstring){
		    		var success = jsonstring.success;
		    		if(success){
		    			location.href="<%=registerPath%>system/toCertification.do";
		    		}else{
		    			alert(jsonstring.error);
		    		}
		    	}
		    });
		}			
    </script>
</body>
</html>