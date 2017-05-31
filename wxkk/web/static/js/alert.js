function mian_tk(title,btn1,btn2){
				var cover = $('<div></div>'); //创建一个父DIV
			        //main.attr('id', 'parent'); //给父DIV设置ID
			        cover.addClass('cover'); //添加CSS样式
					cover.appendTo('body');
				var tk=$('<div></div>');
					tk.addClass('alertbox order-remind order-dele');
				var page_p=$('<p></p>');
					page_p.addClass('alert-p');
					page_p.html(title);
					page_p.appendTo(tk);
				var btnbox1=$('<p></p>');
					btnbox1.addClass('btnbox1');
					btnbox1.appendTo(tk);
				var cancelbtn1=$('<a></a>');
					cancelbtn1.addClass('cancelbtn1 order-cancel1');
					cancelbtn1.html(btn1);
					cancelbtn1.appendTo(btnbox1);
				var surebtn=$('<a></a>');
					surebtn.addClass('surebtn1 order-sure1');
					surebtn.html(btn2);
					surebtn.appendTo(btnbox1);
					tk.appendTo('body');
				close_ff(cancelbtn1);
				close_ff(surebtn);
				function close_ff(even){
					even.click(function(){
						cover.hide();
						tk.hide();	
					});
				}
			}
			