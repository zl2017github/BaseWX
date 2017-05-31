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
    <title>首页</title>
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
			<!--<img src="images/f-page.jpg" alt="" class="logo"/>-->
			<h1 class="head_tit">E.T单车</h1>
			<a class="head_notice"><img src="images/notice2.png" class="noticeIcon noticeClick"/></a>
			
		</div>
		<!--header结束-->	
		<div class="content posiR">
			<c:if test="${customer.isPayDeposit != 1 || customer.isCertification != 1}">
				<div class="indRemind" onclick="javascript:location.href='<%=bikePath%>customer/toAuthChoice.do'"><img src="images/indRe.png" alt="" class="indRe_icon"/>请缴纳押金/认证后使用</div>
			</c:if>
			<img src="images/focus2.png" alt="" class="focusImg"/>
			<ul class="modulList">
				<li class="moduleBox mine" onclick="javascript:location.href='<%=bikePath%>customer/toMy.do'">
					<a>
						<img src="images/my.png" alt="我的" class="moduleImg"/>
						<img src="images/my3.png" alt=""  class="moduleBg"/>
						<p class="moduleWord">我的</p>
					</a>
				</li>
				<li class="moduleBox ride" onclick="used()">
					<a>
						<img src="images/bicycle.png" alt="我要骑车"  class="moduleImg"/>
						<img src="images/bicycle3.png" alt="我要骑车"  class="moduleBg"/>
						<p class="moduleWord">我要骑车</p>
					</a>
				</li>
				<li class="moduleBox repair" onclick="javascript:location.href='<%=bikePath%>house/list.do'">
					<a>
						<img src="images/houseInfo.png" alt="房屋信息"  class="moduleImg"/>
						<img src="images/houseInfo2.png" alt="房屋信息"  class="moduleBg"/>
						<p class="moduleWord">房屋信息</p>
					</a>
				</li>
				<li class="moduleBox aboutUs" onclick="javascript:location.href='<%=bikePath%>kk/toAbout.do'">
					<a>
						<img src="images/aboutUs.png" alt="关于我们"  class="moduleImg"/>
						<img src="images/aboutUs3.png" alt="我要骑车"  class="moduleBg"/>
						<p class="moduleWord">关于我们</p>
					</a>
				</li>
			</ul>
			<div class="noticeBox">
				消息：骑车可以抵房租啦！您现在有<span style="color:red">${deduction.notDeductionSum }</span>元可以抵扣房租！<a href="<%=bikePath%>customer/deduction/description.do" class="noticeRemind">点击显示详情</a>
			</div>
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
			骑车可以抵房租啦！骑的越多，抵扣的越多！
			<p class="noticeLink"><a href="<%=bikePath%>customer/deduction/description.do" class="noticeDet_link">详细内容</a></p>
		</div>
	</div>	
		
	
	<script src="js/swiper.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//显示公告
			//$('.cover').show();
			//$('.noticeAlert').show();
			
			$('.noticeClick').on('click',function(){
				$('.cover').show();
				$('.noticeAlert').show();
			});
			$('.noticeClose').on('click',function(){
				$('.cover').hide();
				$('.noticeAlert').hide();
			});	
		});
		
		//用车
		function used(){
			$.ajax({
		    	type:"post",
		    	url:"<%=bikePath%>bike/toIsCertification.do",
		    	dataType:"json",
		    	success:function(jsonstring){
		    		var flag = jsonstring.success;
		    		if(flag){
		    			location.href="<%=bikePath%>bike/toUsed.do";
		    		}else{
		    			alert(jsonstring.error);
		    		}
		    	}
		    });
		}
    </script>
</body>
</html>