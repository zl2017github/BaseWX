	$(function(){
		//点击右上角，显示提示信息
		$('.noticeClick').on('click',function(){
			$('.cover').show();
			$('.noticeAlert').show();
		})
		$('.noticeClose').on('click',function(){
			$('.cover').hide();
			$('.noticeAlert').hide();
		})	
	})

	//限制宽高一致的方法
		function SizeLimit(obj){
			
			var objW=obj.width();
				obj.height(objW);
		}


//判断手机号和验证码信息是否有误
function judge(name,code,error){
	var phone1 = name.val();
	var pword1 = code.val();
	var regexName=/^(13[0-9]|14[5|7]|15[0-9]|17[0-9]|18[0-9])\d{8}$/,
		regexPas=/^\d{6}$/;
	if(phone1==""){
		error.slideDown();
		error.text("请输入正确的手机号！");
		return ;
	}else if(!regexName.test(phone1)){
		 error.slideDown();
		 error.text("输入的手机号不正确！");
		 return ;
	} 
	if(pword1==''){
		error.slideDown();
		error.text("请输入收货地址！");
		return ;
	}
}