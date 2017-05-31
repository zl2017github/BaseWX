package cn.com.et.commons.message;

public class MessageTemplateUtil {

	/**
	 * 
	 * 功能描述：注册时该发送的短信内容
	 * 
	 * @param code
	 * @return
	 * 
	 * @author haokf
	 * 
	 * @since 2017-5-13
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getRegisterMsg(int code) {
		StringBuilder sb = new StringBuilder();
		sb.append("尊敬的用户,您的验证码：").append(code).append(",五分钟内有效,工作人员不会向您索取,请勿泄露。【KAKE生活圈】");
		return sb.toString();
	}
}
