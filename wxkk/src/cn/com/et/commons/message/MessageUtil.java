package cn.com.et.commons.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.com.et.commons.SSLClient;
import cn.com.et.entity.message.Message;
import cn.com.et.service.message.MessageService;
import cn.com.et.service.message.impl.MessageServiceImpl;
import cn.com.et.utils.security.MD5;

/**
 * 
 * 短信工具类
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-5-11
 */
public class MessageUtil{

	//短信url
	private static String MESSAGE_URL = "https://dx.ipyy.net/sms.aspx";
	//账号
	private static String ACCOUNT = "AE00289";
	//平台密码
	private static String PLATFORM_PASSWORD = "AE0028902";
	//接口密码
	private static String INTERFACE_PASSWORD = "AE0028955";
	
	private static HttpClient httpclient;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 
	 * 功能描述：发送短信
	 *
	 * @param mobile
	 * @param content
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-5-11
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Message sendMessage(String mobile,String content){
		Message msg = new Message();
		try {
			httpclient = new SSLClient();
			
			HttpPost post = new HttpPost(MESSAGE_URL);
			post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("action","send"));
			nvps.add(new BasicNameValuePair("userid", ""));
			nvps.add(new BasicNameValuePair("account", ACCOUNT)); 	
			nvps.add(new BasicNameValuePair("password", MD5.md5(INTERFACE_PASSWORD).toUpperCase()));//MD5大写		
			nvps.add(new BasicNameValuePair("mobile", mobile));		//多个手机号用逗号分隔
			nvps.add(new BasicNameValuePair("content", content));
			nvps.add(new BasicNameValuePair("sendTime", ""));
			nvps.add(new BasicNameValuePair("extno", ""));

			post.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
				
			HttpResponse response = httpclient.execute(post);
			
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			// 将字符转化为XML
			String returnString=EntityUtils.toString(entity, "UTF-8");
			Document doc = DocumentHelper.parseText(returnString);
			// 获取根节点
			Element rootElt = doc.getRootElement();
			// 获取根节点下的子节点的值
			String returnstatus = rootElt.elementText("returnstatus").trim();
			String message = rootElt.elementText("message").trim();
			String remainpoint = rootElt.elementText("remainpoint").trim();
			String taskID = rootElt.elementText("taskID").trim();
			String successCounts = rootElt.elementText("successCounts").trim();
			
			//保存
			msg.setMobile(mobile);
			msg.setContent(content);
			msg.setReturnStatus(returnstatus);
			msg.setMessage(message);
			msg.setRemainPoint(remainpoint);
			msg.setTaskId(taskID);
			msg.setSuccessCounts(successCounts);
			msg.setReturnString(returnString);
			msg.setCreateTime(new Date());
			
//			System.out.println(returnString);
//			System.out.println("返回状态为：" + returnstatus);
//			System.out.println("返回信息提示：" + message);
//			System.out.println("返回余额：" + remainpoint);
//			System.out.println("返回任务批次：" + taskID);
//			System.out.println("返回成功条数：" + successCounts);
			EntityUtils.consume(entity);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return msg;
	}
	
	public static void main(String[] args) {
		String content="尊敬的用户，您的验证码:1234，五分钟内有效，工作人员不会向您索取，请勿泄露。【KAKE生活圈】";
		MessageUtil mu = new MessageUtil();
		mu.sendMessage("18501002130",content);
//		System.out.println(is);
		
	}
}
