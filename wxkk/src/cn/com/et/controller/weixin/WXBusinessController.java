
package cn.com.et.controller.weixin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.et.commons.weixin.WXCfg;
import cn.com.et.commons.weixin.WXCommon;
import cn.com.et.constant.GlobalConstant;
import cn.com.et.constant.WXConstants;
@Controller
@RequestMapping(value = "wx")
public class WXBusinessController {
	
	private static Log logger  = LogFactory.getLog(GlobalConstant.WX_LOG_NAME);
	
	/**
	 * 
	 * 功能描述：开始预约
	 *
	 * @param req
	 * @param rsp
	 * @param code
	 * @param state
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-5-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "bespeak", method = RequestMethod.GET)
	public ModelAndView bespeak(HttpServletRequest req,
			HttpServletResponse rsp,
			@RequestParam("code") String code,
			@RequestParam("state") String state){
		long startTime = System.currentTimeMillis();
		logger.info("code="+code+"  state="+state);
		
		ModelAndView mav = new ModelAndView();
		if(StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(state)){
			String openId = null;
			String appid = WXCfg.getBaseCfg().getProperty(WXCfg.E_BASE.BASE_APPID.name());
			String secret = WXCfg.getBaseCfg().getProperty(WXCfg.E_BASE.BASE_APPSECRET.name());
			//会话中不存在用户会话状态时要先获取到用户openid,并用WeixinUser封装后保存在会话中
			String requestUrl = WXConstants.GET_OAUTH2_OPENID_URL.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code.trim());
			//openId = "oJj0suELM4yR_DlwyflECL8zIsa2";//测试使用
			openId = WXCommon.getWxOpenId(requestUrl);//正式使用
			req.getSession().setAttribute(GlobalConstant.SESSION_USER_WECHATID,openId);
			logger.info("====== openId="+openId);
			mav.setViewName("redirect:/customer/index.do");
		}else{
			logger.info("未获取到请求的code与state");
			mav.addObject("errorMsg", "未获取到请求的code与state");
			mav.setViewName("error/error");
		}
		long endTime = System.currentTimeMillis();
		logger.info("WXBusinessController-->bespeak use time::"+(endTime-startTime));
		
		return mav;
	}
	
}
