package cn.com.et.controller.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.et.commons.weixin.WXCfg;
import cn.com.et.commons.weixin.WXCommon;
import cn.com.et.commons.weixin.WXXmlUtil;
import cn.com.et.constant.GlobalConstant;
import cn.com.et.constant.WXConstants;
import cn.com.et.utils.FileUtil;
import cn.com.et.utils.PathUtil;
import cn.com.et.utils.StringUtil;
import cn.com.et.wx.vo.rsp.Article;
import cn.com.et.wx.vo.rsp.NewsMessage;
import cn.com.et.wx.vo.rsp.TextMessage;

/**
 * 微信入口
 * 
 * @author onetime
 * 
 */
@Controller
@RequestMapping(value = "wx")
public class WeiXinController {
	 private static Log logger  = LogFactory.getLog(GlobalConstant.WX_LOG_NAME);
	 private static Properties messageKeys = new Properties();
	 private static Properties subscribeCfg = new Properties();
		
	/**
	 * 微信访问接口（微信访问用）
	 * 
	 * @param weixinToken
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "checkUrlAndToken")
	@ResponseBody
	public String checkUrlAndToken(
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		boolean isGet = request.getMethod().toLowerCase().equals("get");

		String signature = request.getParameter("signature"); // 微信加密签名
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		String echostr = request.getParameter("echostr"); // 随机字符串
		logger.debug("signature="+signature+";timestamp="+timestamp+";nonce="+nonce+";echostr="+echostr);
		
		if (isGet && !StringUtil.isEmpty(echostr)) { // 微信服务器验证
			String wxToken = WXCfg.getBaseCfg().getProperty("WX_TOKEN");
			logger.debug("wxToken"+wxToken);
			
			logger.debug(signature+"|"+timestamp+"|"+nonce);
			boolean bln = WXCommon.checkSignature(wxToken,signature,timestamp,nonce);
			if(bln){
				logger.debug("checkSignature SUCCESS");
			}else{
				logger.debug("checkSignature FAIL");
			}
		} else {
			logger.debug("2"+signature+"|"+timestamp+"|"+nonce);
			String respMessage = this.processRequest(request);
			logger.debug("respMessage--"+respMessage);
			PrintWriter pw = response.getWriter();
			try {
				pw.write(respMessage);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				logger.debug(e.getStackTrace().toString());
			}finally{
				if(pw != null){
					pw.close();
					pw = null;
				}
			}
		}
		logger.debug("echostr"+echostr);
		return echostr;
	}
	
	/**
	 * 
	 * 功能描述：跳转到错误action
	 *
	 * @param errorMsg
	 * @return
	 * 
	 * @author onetimezl
	 *
	 * @since 2015-9-16
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "doError")
	public ModelAndView doError(){
		logger.info("WeixinController-->doError---->start");
		ModelAndView mav = new ModelAndView();
		String errorMsg = "非法用户登录";
 		mav.addObject("errorMsg", errorMsg);
		mav.setViewName("error/error");
		logger.info("WeixinController-->doError---->end");
		return mav;
	}

	/**
	 * 处理微信请求
	 * 
	 * @param request
	 * @param mobile
	 * @return
	 */
	private String processRequest(HttpServletRequest request) {
		String respMessage = null;
		String projectUrl = PathUtil.getProjectUrl(request);
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = WXXmlUtil.parseXml(request);
			requestMap.put(WXConstants.PROJECT_URL, projectUrl);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get(WXConstants.FROM_USER_NAME);
			// 公众帐号
			String toUserName = requestMap.get(WXConstants.TO_USER_NAME);
			// 消息类型
			String msgType = requestMap.get(WXConstants.MSG_TYPE);
			logger.info("fromUserName::"+fromUserName+" toUserName::"+toUserName+" msgType::"+msgType);
	
			if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
				//return processTextMessage(requestMap);
			}else if(msgType.equals(WXConstants.REQ_MESSAGE_TYPE_IMAGE)){ //图片
				
			}else if(msgType.equals(WXConstants.RESP_MESSAGE_TYPE_NEWS)){ //图文消息
				
			}else if(msgType.equals(WXConstants.REQ_MESSAGE_TYPE_LINK)){ //链接
				
			} else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
				//return processEvent(requestMap,request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	
	/**
	 * 处理文本消息
	 * @param requestMap
	 * @return
	 */
	public String processTextMessage(Map<String, String> requestMap){
		String returnMsg = "";
		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(requestMap.get(WXConstants.FROM_USER_NAME));
		textMessage.setFromUserName(requestMap.get(WXConstants.TO_USER_NAME));
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WXConstants.REQ_MESSAGE_TYPE_TEXT);
		String content = requestMap.get("Content");
		//关键字处理
		messageKeys = WXCfg.getKeyMessageCfg();
		//此处从messageKeys中判断是否有关键字，如果存在获取关键字相应的消息
		if(messageKeys.contains(content)){
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(requestMap.get(WXConstants.FROM_USER_NAME));
			newsMessage.setFromUserName(requestMap.get(WXConstants.TO_USER_NAME));
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(WXConstants.RESP_MESSAGE_TYPE_NEWS);
			List<Article> articleList = new ArrayList<Article>();
			Article article = new Article();
			
			/*样例
			article.setTitle(subscribeCfg.getProperty());
			article.setPicUrl(subscribeCfg.getProperty());
			article.setDescription(subscribeCfg.getProperty());
			String url = subscribeCfg.getProperty();
			article.setUrl(url);
			articleList.add(article);*/
			
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			returnMsg = WXXmlUtil.newsMessageToXml(newsMessage);
		}
		logger.info(returnMsg);
		return returnMsg;
	}
	
	/**
	 * 处理图文消息
	 * @param requestMap
	 * @return
	 */
	public String processNewsMessage(Map<String, String> requestMap){
		String returnMsg = "";
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(requestMap.get(WXConstants.FROM_USER_NAME));
		newsMessage.setFromUserName(requestMap.get(WXConstants.TO_USER_NAME));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WXConstants.RESP_MESSAGE_TYPE_NEWS);
		List<Article> articleList = new ArrayList<Article>();
		
		//subscribeCfg = WXCfg.getSubscribeCfg();
		//第一个图文
		/*Article article = new Article();
		article.setTitle(requestMap.get(subscribeCfg.get()));
		article.setPicUrl(requestMap.get()));
		article.setDescription();
		String url = subscribeCfg.getProperty();//登录的url
		article.setUrl(url);*/
		/*
		//第二个图文
		Article article1 = new Article();
		article1.setTitle(Constants.GUGU_VIEW1_TITLE);
		article1.setPicUrl(requestMap.get(Constants.PROJECT_URL) + Constants.GUGU_VIEW1_PICURL);
		article1.setDescription(Constants.GUGU_VIEW1_DESCRIPTION);
		article1.setUrl(Constants.GUGU_VIEW1_URL);
		
		//第三个图文
		Article article3 = new Article();
		article3.setTitle(Constants.GUGU_VIEW3_TITLE);
		article3.setPicUrl(requestMap.get(Constants.PROJECT_URL) + Constants.GUGU_VIEW3_PICURL);
		article3.setDescription(Constants.GUGU_VIEW3_DESCRIPTION);
		article3.setUrl(Constants.GUGU_VIEW3_URL);
		
		
		articleList.add(article);
		articleList.add(article1);
		articleList.add(article2);
		articleList.add(article3);
		*/
		//articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		returnMsg = WXXmlUtil.newsMessageToXml(newsMessage);
		logger.info("processNewsMessage-->respMessage::"+returnMsg);
		return returnMsg;
	}
	
	/**
	 * 处理EVENT
	 * @param requestMap
	 * @return
	 */
	public String processEvent(Map<String, String> requestMap,HttpServletRequest request){
		String returnMsg = "";
		// 事件类型
		String eventType = requestMap.get("Event");
		logger.debug("WeiXinController-->processEvent eventType::"+eventType);
		logger.info("eventType::"+eventType);
		// 关注
		if (eventType.equals(WXConstants.EVENT_TYPE_SUBSCRIBE)) { 
			logger.debug("WeiXinController-->processEvent EVENT_TYPE_SUBSCRIBE");
			// 发送图文消息
			return processNewsMessage(requestMap);
		// 取消关注
		} else if (eventType.equals(WXConstants.EVENT_TYPE_UNSUBSCRIBE)) {
			
		//点击事情
		}else if(eventType.equals(WXConstants.EVENT_TYPE_CLICK)){
			String eventKey = requestMap.get("EventKey");
			logger.info("eventKey::"+eventKey);
			logger.info("processEvent-->respMessage::"+returnMsg);
		}
		return returnMsg;
	}
}
