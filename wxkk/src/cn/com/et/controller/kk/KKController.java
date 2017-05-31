package cn.com.et.controller.kk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.et.controller.base.BaseController;
import cn.com.et.plugin.page.Page;


@Controller
@RequestMapping(value = "/kk")
public class KKController extends BaseController {
	
	/**
	 * 
	 * 功能描述：跳到用车页面
	 *
	 * @param page
	 * @param openId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-23
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toAbout")
	public ModelAndView query(Page page,String openId) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("kk/kk_about");
		return mv;
	}
	
	
}
