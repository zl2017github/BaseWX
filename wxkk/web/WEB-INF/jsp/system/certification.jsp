<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String certificationPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>上传照片</title>
    <link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script></script>
	<script type="text/javascript" src="js/common.js"></script></script>
	
</head>
<body>
	<div class="wrapper">
		<form action="<%=certificationPath%>system/certification.do" method="post" id="cerForm" enctype="multipart/form-data">
			
			<!--header开始-->
			<div class="headerBox">
				<a onclick="history.go(-1)"><img src="images/backW.png" alt="" class="back_icon"/></a>
				<h1 class="head_tit">上传照片</h1>
			</div>
			<!--header结束-->	
			<div class="content">
				<p class="photoEx">照片示例</p>
				<div class="uploadBox">
					<input type="file" name="positivePhoto" id="positivePhoto" value="" onchange="upload(this)" class="upBtn"/>
					<a href="#" class="upAdd" >+</a>
					<p class="upWord">身份证正面照</p>
				</div>
				<div class="uploadBox">
					<input type="file" name="negativePhoto" id="negativePhoto" value="" onchange="upload(this)" class="upBtn"/>
					<a href="#" class="upAdd">+</a>
					<p class="upWord">身份证反面照</p>
				</div>
			</div>
			<div class="fixedBtnBox">
				<input type="button" onclick="sub()" class="comBtn" value="提交审核">
			</div>
		</form>
	</div>
	<!--wrapper结束-->
	<div class="cover"></div>
	<!--消息提示-->
	<div class="comAlert exampleAlert">
		<div class="comAlert_tit">
			<span class="comAlert_close exampleClose">X</span>
		</div>
		<div class="comAlertCon noticeCon">
			<img src="images/ex.png" alt="" class="exPhoto"/>
		</div>
		<div class="comAlertBtn_box">
			<a class="exampleClose alertAloneBtn">确认</a>
		</div>
	</div>	
	<script type="text/javascript">
		$(function(){
			$('.photoEx').on('click',function(){
				$('.cover').show();
				$('.exampleAlert').show();
			});
			$('.exampleClose').on('click',function(){
				$('.cover').hide();
				$('.exampleAlert').hide();
			});
		});
		
		//上传照片
		function upload(obj){
			//判断上传的图片
			var uploadfile = $(obj).val();
			var imgSize=$(obj).context.files[0].size/1024;//得到图片的大小（单位kb）
			
			if(uploadfile!=""){
				var types = ["jpg","jpeg","gif","png","JPG","JPEG","GIF","PNG"];
				var ext = uploadfile.substring(uploadfile.length-3).toLowerCase();
				var sing = false;
				for(var i=0; i<types.length;i++){
					if (ext==types[i]){
					sing = true; 
					}
				}
				if(!sing){
					$(obj).val("");
					alert("只允许上传图片(jpg,jpeg,gif,png)!");
					return false;
				}
				if(imgSize>1024*10){
					$(obj).val("");
					alert("只允许上传小于10M的图片!");
					return false;
				}if(imgSize<1){
				    $(obj).val("");
					alert("只允许上传大于1K的图片!");
					return false;
				}	
			}
		
			var objUrl = getObjectURL(obj.files[0]) ;
			if (objUrl) {
				var html = "<img src='"+objUrl+"' style='height:50%;width:50%;'/>";
				$(obj).next().html(html);
			}
		};
		//建立一个可存取到该file的url
		function getObjectURL(file) {
			var url = null ; 
			if (window.createObjectURL!=undefined) { // basic
				url = window.createObjectURL(file) ;
			} else if (window.URL!=undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file) ;
			} else if (window.webkitURL!=undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file) ;
			}
			return url ;
		}
		
		function sub(){
			var positivePhoto = $("#positivePhoto").val();
			var negativePhoto = $("#negativePhoto").val();
			if(positivePhoto == "" || negativePhoto == ""){
				alert("请上传身份证正反照!");
				return false;
			}
			$("#cerForm").submit();
		}	
		
    </script>
</body>
</html>