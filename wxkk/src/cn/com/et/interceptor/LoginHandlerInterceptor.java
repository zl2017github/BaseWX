package cn.com.et.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.et.constant.GlobalConstant;
import cn.com.et.entity.customer.Customer;

/**
 * 
 * 类名称：LoginHandlerInterceptor.java 类描述：
 * 
 * @author ADMIN 作者单位： 联系方式： 创建时间：2015年1月1日
 * @version 1.6
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if (path.matches(GlobalConstant.NO_INTERCEPTOR_PATH)) {
			return true;
		} else {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Customer user = (Customer) session.getAttribute(GlobalConstant.SESSION_USER);
			if (user != null) {

				// 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
				/**
				 * 根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面
				 * 根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
				 */
				Boolean b = true;
				path = path.substring(1, path.length());
				return true;
			} else {
//				if (path.endsWith("favicon.ico")||path.endsWith("robots.txt")) {
//					return true;
//				}
				// 登陆过滤
				response.sendRedirect(request.getContextPath() + GlobalConstant.LOGIN);
				return false;
				// return true;
			}
		}
	}

}
