package cn.com.et.commons.weixin;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.com.et.constant.GlobalConstant;
import cn.com.et.constant.WXConstants;
import cn.com.et.utils.HttpUtil;
import cn.com.et.wx.vo.menu.Button;
import cn.com.et.wx.vo.menu.Menu;
import cn.com.et.wx.vo.menu.ViewButton;


/**
 * 微信菜单设置
 * 
 * @author onetime
 */
public class WXMenu {
	private static Logger log = LoggerFactory.getLogger(GlobalConstant.WX_LOG_NAME);
	
	/**
	 * 根据指定格式的JSON创建微信菜单
	 * @param accessToken
	 * @param menu
	 * @return
	 */
	public static int createMenu(String accessToken,String menu){
		int result = 0;
		String url = WXConstants.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		String resMsg = HttpUtil.doPost(url, menu, "UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(resMsg);
		if(jsonObject!=null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	
	/**
	 * 生成微信菜单的JSON字符串形式
	 */
	public static String getMenuStr(){
		Menu m = new Menu();//菜单对象
		ViewButton vb = new ViewButton();
		vb.setName("开始预约");
		vb.setType("view");
		
		String baseDomain = WXCfg.getBaseDomain();
		String menuHomeUrl = WXCfg.getMenuHomeUri();
		String kakeIndexUrl = baseDomain+menuHomeUrl;
		
		String menuBespeakUrl = WXCfg.getMenuBespeakUrl();
		menuBespeakUrl = menuBespeakUrl.replace(WXCfg.E_BASE.BASE_APPID.name(), WXCfg.getBaseAppid());
		String url = menuBespeakUrl.replace(WXCfg.E_MENU.MENU_HOME_URI.name(), kakeIndexUrl);
		vb.setUrl(url);
		log.debug("kakeIndexUrl="+kakeIndexUrl);
		m.setButton(new Button[]{vb});
		Object json = JSON.toJSON(m);
		String jsonMenu = json.toString();
		return jsonMenu;
	}
}
