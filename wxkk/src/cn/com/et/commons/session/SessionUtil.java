package cn.com.et.commons.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.com.et.constant.GlobalConstant;
import cn.com.et.entity.customer.Customer;
import cn.com.et.service.customer.CustomerService;

public class SessionUtil {

	/**
	 * 
	 * 功能描述：移除用户信息
	 *
	 * @param request
	 * @param wechatId  
	 * @param userMobile 用户电话
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-23
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void removeSessionUser(HttpServletRequest request,String wechatId,String userMobile){
		String key = GlobalConstant.SESSION_USER+"_"+wechatId+"_"+userMobile;
		request.getSession().removeAttribute(key);
	}
	
	/**
	 * 
	 * 功能描述：添加用户信息
	 *
	 * @param request
	 * @param wechatId  
	 * @param userMobile 用户电话
	 * @param customer 用户信息
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-23
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void addSessionUser(HttpServletRequest request,String wechatId,String userMobile,Customer customer){
		String key = GlobalConstant.SESSION_USER+"_"+wechatId+"_"+userMobile;
		request.getSession().setAttribute(key, customer);
	}
	
	/**
	 * 
	 * 功能描述：获取用户信息
	 *
	 * @param request
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-23
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Customer getSessionUser(HttpServletRequest request){
		Cookie cookies[]=request.getCookies();//声明一个cookie对象
		String userMobile = null;
		String wechatId = request.getSession().getAttribute(GlobalConstant.SESSION_USER_WECHATID)+"";
		if(cookies != null){
			for (int i = 0; i < cookies.length; i++){  
			    if(cookies[i].getName().equals(GlobalConstant.USER_NAME)){
			    	userMobile = cookies[i].getValue();
			    }
			}
			String key = GlobalConstant.SESSION_USER+"_"+wechatId+"_"+userMobile;
			return (Customer)request.getSession().getAttribute(key);
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * 功能描述：修改用户用车状态或不用车状态
	 *
	 * @param request
	 * @param isUsed
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void updateSessionUserisUsed(HttpServletRequest request,Integer isUsed,CustomerService customerService) throws Exception{
		//更新用户的用车状态
		Customer customer = getSessionUser(request);
		customer.setIsUsed(isUsed);
		customerService.updateCustomerIsUsed(customer);
		//修改sessionUser信息
		String key = GlobalConstant.SESSION_USER+"_"+customer.getWechatId()+"_"+customer.getMobile();
		request.getSession().setAttribute(key, customer);
	}
}
