package cn.com.et.commons.weixin;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.et.utils.FileUtil;
import cn.com.et.utils.PathUtil;

/**
 * 微信配置
 * @author Administrator
 *
 */
public class WXCfg {
	private static Log logger  = LogFactory.getLog(WXCfg.class);
	//配置文件根目录
	private static final String CONFIG_PATH = PathUtil.getEnvPath(PathUtil.PATH_TYPE.CLASSES.name()).concat("config/"); 
	//初始化属性对象
	private static Properties subscribeCfg = null;
	private static Properties keyMessageCfg = null;
	private static Properties baseCfg = null;
	private static Properties menuCfg = null;
	//基本信息
	private static final String base = "base.properties";
	public enum E_BASE{
		BASE_DOMAIN,BASE_TOKEN,BASE_APPID,BASE_APPSECRET,BASE_KEY,BASE_MCHID,BASE_CERT_LOAD_PATH,BASE_CERT_PASSWORD,BASE_NOTIFY_URL
	}
	//菜单
	private static final String menu = "menu.properties";
	public enum E_MENU{
		MENU_HOME_URI,MENU_BESPEAK_URL
	}
	//关注
	private static final String subscribe = "subscribe.properties";
	public enum E_SUBSCRIBE{
		
	}
	//关键字
	private static final String keyMessage = "textMessage.properties";
	public enum E_KEYMESSAGE{
		
	}
	
	/**
	 * 初始化微信配置信息
	 */
	private static void initBase(){
		if(null==baseCfg){
			baseCfg = FileUtil.readProperties(CONFIG_PATH.concat(base));
		}
	}
	
	/**
	 * 获取微信配置信息
	 * @return
	 */
	public static Properties getBaseCfg(){
		initBase();
		logger.debug(CONFIG_PATH.concat(base));
		return baseCfg;
	}
	
	/**
	 * 初始化微信配置信息
	 */
	private static void initMenu(){
		if(null==menuCfg){
			menuCfg = FileUtil.readProperties(CONFIG_PATH.concat(menu));
		}
	}
	
	/**
	 * 获取微信配置信息
	 * @return
	 */
	public static Properties getMenuCfg(){
		initMenu();
		logger.debug(CONFIG_PATH.concat(menu));
		return menuCfg;
	}
	
	/**
	 * 初始化微信图文配置
	 */
	private static void initSubscribeCfg(){
		if(null==subscribeCfg){
			subscribeCfg = FileUtil.readProperties(CONFIG_PATH.concat(subscribe));
		}
	}
	
	/**
	 * 获取微信图文配置
	 * @return
	 */
	public static Properties getSubscribeCfg(){
		initSubscribeCfg();
		logger.debug(CONFIG_PATH.concat(subscribe));
		return subscribeCfg;
	}
	
	/**
	 * 初始化微信关键字回复
	 */
	private static void initKeyMessageCfg(){
		if(null==keyMessageCfg){
			keyMessageCfg = FileUtil.readProperties(CONFIG_PATH.concat(keyMessage));
		}
	}
	
	/**
	 * 获取微信关键字回复
	 * @return
	 */
	public static Properties getKeyMessageCfg(){
		initKeyMessageCfg();
		logger.debug(CONFIG_PATH.concat(keyMessage));
		return keyMessageCfg;
	}
	
	//BASE_DOMAIN,BASE_TOKEN,BASE_APPID,BASE_APPSECRET,BASE_KEY,
	//BASE_MCHID,BASE_CERT_LOAD_PATH,BASE_CERT_PASSWORD,BASE_NOTIFY_URL
	public static String getBaseDomain(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_DOMAIN.name());
	}
	
	public static String getBaseToken(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_TOKEN.name());
	}
	
	public static String getBaseAppid(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_APPID.name());
	}
	
	public static String getBaseAppsecret(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_APPSECRET.name());
	}
	
	public static String getBaseKey(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_KEY.name());
	}
	
	public static String getBaseMchid(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_MCHID.name());
	}
	
	public static String getBaseCertLoadPath(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_CERT_LOAD_PATH.name());
	}
	
	public static String getBaseCertPassword(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_CERT_PASSWORD.name());
	}
	
	public static String getBaseNotifyUrl(){
		if(null==baseCfg)initBase();
		return baseCfg.getProperty(E_BASE.BASE_NOTIFY_URL.name());
	}
	
	//MENU_HOME_URI,MENU_BESPEAK_URL
	public static String getMenuHomeUri(){
		if(null==menuCfg)initMenu();
		return menuCfg.getProperty(E_MENU.MENU_HOME_URI.name());
	}
	
	public static String getMenuBespeakUrl(){
		if(null==menuCfg)initMenu();
		return menuCfg.getProperty(E_MENU.MENU_BESPEAK_URL.name());
	}
}
