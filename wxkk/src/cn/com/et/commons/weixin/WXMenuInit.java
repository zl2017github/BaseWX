package cn.com.et.commons.weixin;

import cn.com.et.ident.AccessToken;


public class WXMenuInit {
	
	public static void main(String[] args) {
		initMenu();
	}
	
	public static void initMenu(){
		AccessToken token = WXCommon.getAccessToken();
		int result = WXMenu.createMenu(token.getToken(),WXMenu.getMenuStr());
		if(result==0)
			System.out.println("菜单创建成功");
		else
			System.out.println("菜单创建失败 errcode::"+result);
	}
}
