<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
	String namePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<title>姓名认证</title>
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
				<h1 class="head_tit">姓名认证</h1>
			</div>
			<!--header结束-->
			<div class="content">
				<div class="signIn-box account">
					<span class="authTit">姓名</span>
					<input type="text" placeholder="请输入姓名" id="name" class="sign-inp authInp"/>
					<span class="dele"></span>
				</div>
				<div class="signIn-box account">
					<span class="authTit">身份证号</span>
					<input type="text" placeholder="请输入身份证号" id="idNum" class="sign-inp authInp"/>
					<span class="dele"></span>
				</div>
			</div>
			<div class="fixedBtnBox">
				<input type="button" onclick="sub()" class="comBtn" value="提     交">
			</div>
		</div>
		
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
				});
			});
			
			
			/** 提交审核*/
			var flag = true;
			function sub(){
				if(flag){
					flag = false;
					//身份照号
					var identifyCard = $("#idNum").val();
					if(!identityCodeValid(identifyCard)){
						flag = true;
						alert("请填写正确的身份证号!");
						return false;
					}
					
					var sex,birthday;//性别，生日
					if (parseInt(identifyCard.substr(16, 1)) % 2 == 1) {
						//男
						sex = 1;
					} else {
						//女
						sex = 0;
					} 
					birthday = identifyCard.substring(6, 10) + "-" + identifyCard.substring(10, 12) + "-" + identifyCard.substring(12, 14); 
					//姓名
					var name = $("#name").val();
					
					$.ajax({
				    	type:"post",
				    	url:"<%=namePath%>customer/nameAuth.do",
				    	data:{name:name,sex:sex,birthday:birthday,idNumber:identifyCard},
				    	dataType:"json",
				    	success:function(jsonstring){
				    		flag = true;
				    		var success = jsonstring.success;
				    		if(success){
				    			location.href="<%=namePath%>customer/toAuthChoice.do";
				    		}else{
				    			alert(jsonstring.error);
				    		}
				    	}
				    });
				}
			}
			
			<%--   身份证检验（开始）      --%>
			//身份证号
			var identityCode = {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"海外"};
			function identityCodeValid(code) {
				code = code.toUpperCase();
				if(!isCardNo(code) || !checkProvince(code) || !checkBirthday(code) || !checkParity(code)){
					return false;
				}
				return true;
			};

			// 检查号码是否符合规范，包括长度，类型(身份证号)
			isCardNo = function(card) {
				// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
				var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
				if (reg.test(card) === false) {
					return false;
				}
				return true;
			};

			// 取身份证前两位,校验省份(身份证号)
			checkProvince = function(card) {
				var province = card.substr(0, 2);
				if (identityCode[province] == undefined) {
					return false;
				}
				return true;
			};

			// 检查生日是否正确(身份证号)
			checkBirthday = function(card) {
				var len = card.length;
				// 身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
				if (len == '15') {
					var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
					var arr_data = card.match(re_fifteen);
					var year = arr_data[2];
					var month = arr_data[3];
					var day = arr_data[4];
					var birthday = new Date('19' + year + '/' + month + '/' + day);
					return verifyBirthday('19' + year, month, day, birthday);
				}
				// 身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
				if (len == '18') {
					var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
					var arr_data = card.match(re_eighteen);
					var year = arr_data[2];
					var month = arr_data[3];
					var day = arr_data[4];
					var birthday = new Date(year + '/' + month + '/' + day);
					return verifyBirthday(year, month, day, birthday);
				}
				return false;
			};

			// 校验日期(身份证号)
			function verifyBirthday(year, month, day, birthday) {
				var now = new Date();
				var now_year = now.getFullYear();
				// 年月日是否合理
				if (birthday.getFullYear() == year && (birthday.getMonth() + 1) == month
						&& birthday.getDate() == day) {
					// 判断年份的范围（3岁到100岁之间)
					var time = now_year - year;
					if (time >= 3 && time <= 100) {
						return true;
					}
					return false;
				}
				return false;
			};

			// 校验位的检测(身份证号)
			function checkParity(card) {
				// 15位转18位
				card = changeFivteenToEighteen(card);
				var len = card.length;
				if (len == '18') {
					var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8,4, 2);
					var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3','2');
					var cardTemp = 0, i, valnum;
					for (i = 0; i < 17; i++) {
						cardTemp += card.substr(i, 1) * arrInt[i];
					}
					valnum = arrCh[cardTemp % 11];
					if (valnum == card.substr(17, 1)) {
						return true;
					}
					return false;
				}
				return false;
			};

			// 15位转18位身份证号(身份证号)
			function changeFivteenToEighteen(card) {
				if (card.length == '15') {
					var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8,4, 2);
					var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3','2');
					var cardTemp = 0, i;
					card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
					for (i = 0; i < 17; i++) {
						cardTemp += card.substr(i, 1) * arrInt[i];
					}
					card += arrCh[cardTemp % 11];
					return card;
				}
				return card;
			};
			<%--   身份证检验（结束）      --%>
			
		</script>
	</body>
</html>
