package cn.com.et.constant;


/**
 * 微信常量定义  
 * @author Administrator
 *
 */
public class WXConstants {
    /** 
     * 返回消息类型：音乐 
     */  
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";  
  
    /** 
     * 返回消息类型：图文 
     */  
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";  
  
    /** 
     * 请求消息类型：文本 
     */  
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 请求消息类型：图片 
     */  
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  
  
    /** 
     * 请求消息类型：链接 
     */  
    public static final String REQ_MESSAGE_TYPE_LINK = "link";  
  
    /** 
     * 请求消息类型：地理位置 
     */  
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";  
  
    /** 
     * 请求消息类型：音频 
     */  
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";  
  
    /** 
     * 请求消息类型：推送 
     */  
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
  
    /** 
     * 事件类型：subscribe(订阅) 
     */  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    /** 
     * 事件类型：unsubscribe(取消订阅) 
     */  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
  
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */  
    public static final String EVENT_TYPE_CLICK = "CLICK";  
    
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */  
    public static final String EVENT_TYPE_VIEW = "VIEW";  
    
    /**************************************************************************************************************/
    
    /**
     * 微信官网URL
     */
    public static final String WEIXIN_WEBSITE="https://mp.weixin.qq.com";
    
    /**
     *  菜单创建（POST） 限100（次/天）   
     */
    public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    
    /**
     * 网页授权OAuth2.0获取用户的Openid的定义
     */
    public static final String GET_OAUTH2_OPENID_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    
    /**
     *  获取access_token的接口地址（GET） 限200（次/天）  
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
   
	/**
	 * JS-SDK
	 */
	public static final String WX_JSSDK_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/**
	 * 状态值
	 */
	public static final String WX_SUCCESS="SUCCESS";
	public static final String WX_FAIL="FAIL";
	public static final String WX_OK="OK";
	
	//常量值Key
	public static final String FROM_USER_NAME = "FromUserName";
	public static final String TO_USER_NAME = "ToUserName";
	public static final String MSG_TYPE = "MsgType";
	public static final String PROJECT_URL="PROJECT_URL";
	
}
