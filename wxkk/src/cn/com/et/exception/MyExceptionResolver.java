package cn.com.et.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.com.et.utils.DateUtil;
/**
 * 
* 类名称：MyExceptionResolver.java
* 类描述： 
* @author jianglq
* 作者单位： 
* 联系方式：
* @version 1.0
 */
public class MyExceptionResolver implements HandlerExceptionResolver{

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		System.out.println(DateUtil.getTime()+"==============异常开始=============");
		ex.printStackTrace();
		System.out.println(DateUtil.getTime()+"==============异常结束=============");
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}

}
