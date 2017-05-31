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
    <title>协议</title>
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
					<h1 class="explainTit">KAKE单车服务协议</h1>
					<p>
						本协议根据相关法律规定，在协议双方本着公开、公平、平等的原则基础上约定以下服务协议内容。<br/>
						服务商：郑州凯克网络技术有限公司（以下简称KAKE）<br/>
						客服电话：<a href="tel:13592684467" style="color:#ef3c3c;text-decoration:none">13592684467</a><br/>
						地址：郑州市高新区升龙又一城D区8号楼2单元18-270<br/>
						网址：www.zzkake.com<br/>
						租车用户：符合本协议第一条规定的KAKE服务对象（以下简称用户）<br/>
					</p>
					<h1 class="explainTit">一.服务对象</h1>
					<p>
						本协议通过用户注册时通过电子形式签署，，选择“注册即同意《服务协议》”并点击【登录】，签署后，即认为同意本协议以下全部条款的约定，并一致遵照执行。<br/>
						用户应符合以下条件方能成为KAKE的服务对象：<br/>
							1) 用户注册时，KAKE会向用户填写的手机号码发送短信验证码，验证码通过后，该手机号码的登记人（即机主）成为本协议用户；<br/>
							2) 通过注册及电子签署本协议租用KAKE单车的实际使用人和注册手机号码的所有人（机主）不是同一人的，机主和实际使用人则共同成为本协议用户；<br/>
							3) 本条约定的上述机主和实际使用人必须为年龄在16周岁—70周岁（含16周岁和70周岁）之间的完全民事行为能力人，否则其监护人自然成为本协议用户；并且身体健康，无影响骑行安全的疾病，其中影响骑行安全的疾病包括但不限于癫痫、心脏病、高血压、暂时性眩晕、视力障碍、听觉障碍等。<br/>
					</p>
					<h1 class="explainTit">二.服务内容</h1>
					<p>
						KAKE提供用户进行线上预约、下单、租用和还车结算等服务项目，在生活社区内、公共交通枢纽、商场商圈、办公园区及其它可停放自行车场所内，放置KAKE单车供用户使用。用户需按照本协议约定完成租赁行为并且支付租赁费用。<br/>
						用户同意KAKE向其发送消息及商业性短信息，用以通知、提醒、确认KAKE服务使用的相关内容及优惠信息等。<br/>
					</p>
					<h1 class="explainTit">三.履约保证金（押金）</h1>
					<p>
						履约保证金用于用户出现下列违约行为（包括但不限于）时，抵扣KAKE经济损失，保证金一旦被扣除，用户想要再次进行租车时，需要补充保证金至200元：<br/>
							1) 用户已经完成还车行为，但未及时支付租车费用且超过24小时的，系统自动扣除相应金额的保证金；<br/>
							2) 用户在禁止还车地点进行还车，导致车辆无法继续正常出租，经核实后每次扣除保证金50元，且KAKE有权向用户进行索赔；<br/>
							3) 因用户原因造成车辆及智能硬件损坏或灭失，经核实后按照实际价值扣除相应保证金，如保证金不足以赔付，KAKE有权向用户进一步索赔；<br/>
							4) 用户将车辆私自上锁导致车辆无法正常出租，经核实后扣除保证金99元；<br/>
							5) 因用户违规骑行，造成车辆损失或被交警查处、扣车的，经核实后扣除保证金99元。<br/>
						用户用车结束且结算完毕后可随时申请返还履约保证金，申请后账户内剩余履约保证金将于7个工作日内返还至用户指定账户<br/>
					</p>
					<h1 class="explainTit">四.使用方法</h1>
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
					<h1 class="explainTit">五.使用规则</h1>
					<p>
						1) 用户在租赁或解锁骑行前应仔细检查车辆状况，如发现车辆存在问题影响安全骑行应进行车辆报修并取消下单，或者租用其它车况良好的车辆。若用户开锁提车，即表明已认同所订车辆本身的安全性和完整性。此时使用相关服务过程中发生任何意外或伤害事故，用户须自行承担。<br/>
						2) 用户在骑行时，应自觉遵守《道路交通安全法》等法律、法规，服从交警的指挥和处理。雨雪等恶劣天气时，应考虑安全因素，适时选择骑行；本公司也可根据情况酌情作出暂停服务的措施，并通过媒体和相关途径提醒用户。如发生车辆损坏用户应承担相应的赔偿责任。因用户原因产生的故障救助，用户应承担相应的费用。<br/>
						3) 如用户在骑行中发生交通事故，由用户自行承担相关责任，与KAKE无关；如因为交通事故而导致车辆损坏，则用户应先行联系KAKE客服，向KAKE支付车辆损失赔偿金额；如与第三方发生纠纷应由纠纷双方自行解决，本公司不承担任何赔偿义务。
						4) 用户不得利用KAKE车辆从事任何违法、违规行为，所产生的任何后果由用户自行承担，与KAKE无关，由此发生的车辆损失用户应及时赔偿。<br/>
						5) 用户账号内有未支付订单，则本账号不可再次进行租车行为，直至订单完成支付。<br/>
						6) 在已经规划停车点附近区域，建议将车辆停放在停车点，并摆放整齐；在没有停车点的区域，用户应在各种适宜地点（交通法规许可停放自行车且利于他人继续租用的地点）还车。下列地点（包括但不限于）不能成为还车地点：<br/>
							一.道路中间;<br/>
							二.楼房过道;<br/>
							三.消防通道;<br/>
							四.室内;<br/>
							五.封闭院落;<br/>
							六.建筑内部;<br/>
							七.其他妨碍他人继续租用的地点;<br/>
						7) 用户所预订并提取的自行车仅限该用户自己使用，严禁转租或转借于他人使用，禁止骑车载人，否则由此造成人身伤害或车辆损失均由用户承担。用户在使用KAKE服务期间不得产生以下行为;<br/>
						一.将车辆用于出租或取酬;<br/>
						二.在受酒精或药物影响下使用车辆;<br/>
						三.携带任何危险，有毒或易燃材料；<br/>
						四.超过规定乘坐人数（自行车1人）；<br/>
						五.出售、出租、抵押、变卖等非法处分车辆，或拆换、出售、毁坏车辆任何部件；<br/>
						六.盗窃和非法占用车辆;<br/>
						七.将车辆停在妨碍交通或影响他人通行或使用的位置<br/>；
						如用户违反本协议，KAKE有权采取相关处理措施，包括但不限于：信息删除、屏蔽处理、限制使用、终止服务、查封或注销账号等。如造成KAKE损失，用户还应赔偿KAKE直接经济损失及商誉损失、对外垫付或赔偿款项、和解或调解款项、律师费、差旅费、诉讼费用、行政罚款等间接经济损失。<br/>
					</p>
					<h1 class="explainTit">六.服务变更与终止</h1>
					<p>
						KAKE可以通过官网、手机app、微信、微博、短信息等方式对本规则内容的发布、修改、增加、废止等作出公布或通知，也可以对某些功能进行限制或终止使用，用户应当随时关注KAKE公示信息。公示内容一经公布或通知，即替代原内容发生效力。如用户不同意发布或变更的内容，应立即停止使用KAKE的相关服务；若用户继续使用KAKE服务，则视为同意发布或变更的事项。<br/>
						KAKE在限制或终止提供用户服务后，仍享有下列权利：<br/>
							1) 继续保存用户的注册信息及服务使用信息；<br/>
							2) 就用户的违法或违约行为追究法律责任。<br/>
					</p>
					<h1 class="explainTit">七.服务纠纷</h1>
					<p>
						本协议之效力、解释、变更、执行与争议解决均适用中华人民共和国大陆地区法律，如无相关法律规定的，则应参照通用商业惯例和（或）行业惯例。
						因使用KAKE服务产生争议，应本着友好协商原则处理；若协商不成，以KAKE即北京KAKE科技发展有限公司所在地区人民法院为管辖法院。<br/>
					</p>
					<h1 class="explainTit">八.声明</h1>
					<p>
						因不可抗力因素导致KAKE服务被迫中止，KAKE不承担用户相关经济损失。
						KAKE所有通过官网、微信公众号、官方微博等发布的实施细则、公告、通知、产品或流程说明等均视为本协议不可分割的组成部分，与本协议具有同等法律效力。
						用户在KAKE注册并点击“登录”时本协议即生效。<br/>
					</p>
					<p style="text-align:right">
						郑州凯克网络技术有限公司<br/>
						2017年4月13日
					</p>
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