package cn.com.et.controller.system;

import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.et.commons.message.MessageTemplateUtil;
import cn.com.et.commons.session.SessionUtil;
import cn.com.et.constant.GlobalConstant;
import cn.com.et.controller.base.BaseController;
import cn.com.et.entity.customer.Customer;
import cn.com.et.service.customer.CustomerService;
import cn.com.et.service.message.MessageService;

@Controller
@RequestMapping(value = "/system")
public class SystemController extends BaseController  {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MessageService messageService;
	
	
	/**
	 * 
	 * 功能描述：跳到登录界面
	 *
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-2
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/login");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：用车首页
	 *
	 * @param page
	 * @param wechatId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-23
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/login",method ={ RequestMethod.GET,RequestMethod.POST})
	public void login(HttpServletRequest request,HttpServletResponse response,String userMobile,String code) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		String error = null;//错误信息
		boolean success = false;//登录成功
		try {
			//读取验证码
			String code_ = request.getSession().getAttribute(GlobalConstant.SESSION_SECURITY_CODE)+"";
			
			//判断验证码
			if(code_.equals(code)){
				if(userMobile == null || "".equals(userMobile)){
					error = "手机号输入有误！";
				}else{
					Customer customer = customerService.getByMobile(userMobile);
					
					/**
					 * 判断用户是否注册
					 * 		若没有注册，系统自动帮忙注册
					 * 		若已注册，直接登录
					 */
					String wechatId = request.getSession().getAttribute(GlobalConstant.SESSION_USER_WECHATID)+"";
					if(customer == null){
						customer = new Customer();
						customer.setMobile(userMobile);
						customer.setWechatId(wechatId);
						customer.setIsUsed(0);
						customer.setIsValid(0);
						customer.setIsPayDeposit(0);
						customer.setCreateTime(new Date());
						customer.setIsRent(0);
						customer.setIsCertification(0);
						
						customerService.save(customer);
					}else{
						/**
						 * 判断登录的wechatId是否等于系统中的wechantId
						 * 		相等：跳过
						 * 	      不相等：更新系统中的wechatId
						 */
						if(!wechatId.equals(customer.getWechatId())){
							//移除之前登录的用户session
							SessionUtil.removeSessionUser(request, customer.getWechatId(), userMobile);
							
							Customer customer2 = new Customer();
							customer2.setWechatId(wechatId);
							customer2.setId(customer.getId());
							customerService.updateWechatId(customer2);
							//更新当前查询的customer的wechatId
							customer.setWechatId(wechatId);
						}
						
					}
					
					//移除验证码
					request.getSession().removeAttribute(GlobalConstant.SESSION_SECURITY_CODE);
					/**
					 * 登录成功
					 * 保存用户信息到cookie
					 * 同时将用户信息放到session
					 */
					Cookie cookie = new Cookie(GlobalConstant.USER_NAME, userMobile);
					cookie.setMaxAge(365*24*60*60);//cookie过期时间
					String path = request.getContextPath();
					cookie.setPath(path);//cookie路径
					response.addCookie(cookie);//设置cookie
					//保存session
					SessionUtil.addSessionUser(request, wechatId, userMobile, customer);
					request.getSession().setAttribute(GlobalConstant.SESSION_USER_WECHATID,wechatId);
					object.put("wechatId", wechatId);
					success = true;
				}
			}else{
				error = "验证码输入有误！";
			}
			
			object.put("success", success);
			object.put("error", error);
			response.setContentType("text/Xml;charset=utf-8");
			out = response.getWriter();  
		    out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * 
	 * 功能描述：退出登录
	 *
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-2
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/loginOut")
	public void loginOut(HttpServletRequest request,HttpServletResponse response) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = false;//登录成功
		try {
			//得到用户信息
			Customer customer = SessionUtil.getSessionUser(request);
			object.put("wechatId", customer.getWechatId());
			
			/**
			 * 移除session信息
			 * 移除cookie信息
			 */
			SessionUtil.removeSessionUser(request, customer.getWechatId(),customer.getMobile());
			
			Cookie cookie = new Cookie(GlobalConstant.USER_NAME, "");
			cookie.setMaxAge(0);//cookie过期时间
			String path = request.getContextPath();
			cookie.setPath(path);//cookie路径
			response.addCookie(cookie);//设置cookie
			success = true;
			
			object.put("success", success);
			response.setContentType("text/Xml;charset=utf-8");
			out = response.getWriter();  
		    out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * 
	 * 功能描述：获取验证码
	 *
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-2
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/ajaxGetCode")
	public void ajaxGetCode(HttpServletRequest request,HttpServletResponse response,String mobile) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = false;//登录成功
		try {
			int code = (int)(Math.random()*(9999-1000+1))+1000;
			
			System.out.println(code);
			
			//将验证码放入session
			request.getSession().setAttribute(GlobalConstant.SESSION_SECURITY_CODE, code);
			//时长5分钟
			request.getSession().setMaxInactiveInterval(5*60);
			//获取发送短信内容
			String content = MessageTemplateUtil.getRegisterMsg(code);
			//发送短信
//			Message message = MessageUtil.sendMessage(mobile, content);
			//保存短信
//			messageService.save(message);
			
			success = true;
			object.put("code", code);
			object.put("success", success);
			response.setContentType("text/Xml;charset=utf-8");
			out = response.getWriter();  
		    out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * 
	 * 功能描述：跳到注册页
	 *
	 * @param request
	 * @param response
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-15
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toRegister")
	public ModelAndView toRegister(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/register");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：注册手机号
	 *
	 * @param request
	 * @param response
	 * @param mobile 手机号
	 * @param code 验证码
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-15
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toNextRegister")
	public void toNextRegister(HttpServletRequest request,HttpServletResponse response,String mobile,String code) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = false;//注册成功
		String error = "";
		Customer customer = null;
		try {
			String syscode = request.getSession().getAttribute(GlobalConstant.SESSION_SECURITY_CODE)+"";
			if(syscode.equals(code)){
				//判断用户是否存在，存在说明已经注册
				customer = customerService.getByMobile(mobile);
				
				if(customer == null){
					customer = new Customer();
					customer.setMobile(mobile);
					String wechatId = request.getSession().getAttribute(GlobalConstant.SESSION_USER_WECHATID)+"";
					customer.setWechatId(wechatId);
					customer.setIsUsed(0);
					customer.setIsValid(0);
					customer.setIsPayDeposit(0);
					customer.setCreateTime(new Date());
					customer.setIsRent(0);
					customer.setIsCertification(0);
					
					customerService.save(customer);
					/**
					 * 注册成功
					 * 保存用户信息到cookie
					 * 同时将用户信息放到session
					 */
					Cookie cookie = new Cookie(GlobalConstant.USER_NAME, mobile);
					cookie.setMaxAge(365*24*60*60);//cookie过期时间
					String path = request.getContextPath();
					cookie.setPath(path);//cookie路径
					response.addCookie(cookie);//设置cookie
					SessionUtil.addSessionUser(request, wechatId, mobile, customer);
					
					success = true;
				}else{
					error = "该手机号已经注册，可直接登录！";
				}
			}else{
				error = "验证码输入错误，请重新输入！";
			}
			
			object.put("success", success);
			object.put("error", error);
			response.setContentType("text/Xml;charset=utf-8");
			out = response.getWriter();  
		    out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
}
