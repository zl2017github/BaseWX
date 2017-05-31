package cn.com.et.constant;


public class GlobalConstant {
	/**
	 * 系统配置
	 */
	public static final String SESSION_QX = "QX";
	public static final String LOGIN = "/system/toLogin.do"; // 登录地址
	public static final String NO_INTERCEPTOR_PATH = ".*/*"; // 不对匹配该值的访问路径拦截（正则）
	public static final String WX_LOG_NAME = "et_log";
	
	/**
	 * 用户配置
	 */
	public static final String SESSION_USER = "SESSIONETUSER";//session
	public static final String USER_NAME = "ETUSERNAME";//用户名
	public static final String USER_PWD = "ETUSERPWD";//用户密码
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	
	/**
	 * 微信配置
	 */
	public static final String SESSION_USER_WECHATID = "SESSIONUSERWECHATID";//用户微信id
	
	/**
	 * 押金配置
	 */
	public static final Integer DEPOSIT = 99;
}
