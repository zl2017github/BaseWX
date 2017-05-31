package cn.com.et.controller.house;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.et.controller.base.BaseController;
import cn.com.et.plugin.page.PageData;
import cn.com.et.service.house.HouseInfoService;


@Controller
@RequestMapping(value = "/house")
public class HouseController extends BaseController {
	
	@Autowired
	private HouseInfoService houseInfoService;
	
	/**
	 * 
	 * 功能描述：房屋列表
	 *
	 * @param request
	 * @param response
	 * @param wechatId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-9
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/list")
	public ModelAndView houseInfoList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		try {
			List<PageData> houseList = houseInfoService.houseInfoList(null);
			mv.addObject("houseList", houseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("house/house_list");
		return mv;
	}
}
