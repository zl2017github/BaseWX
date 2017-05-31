package cn.com.et.controller.bike;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.et.commons.price.CalculatePrice;
import cn.com.et.commons.session.SessionUtil;
import cn.com.et.controller.base.BaseController;
import cn.com.et.entity.bike.BikeHistory;
import cn.com.et.entity.bike.BikeInfo;
import cn.com.et.entity.bike.BikeProcessFlow;
import cn.com.et.entity.bike.BikeReport;
import cn.com.et.entity.customer.Customer;
import cn.com.et.entity.customer.CustomerDeduction;
import cn.com.et.entity.customer.CustomerWallet;
import cn.com.et.service.bike.BikeHistoryService;
import cn.com.et.service.bike.BikeProcessFlowService;
import cn.com.et.service.bike.BikeReportService;
import cn.com.et.service.bike.BikeService;
import cn.com.et.service.customer.CustomerDeductionService;
import cn.com.et.service.customer.CustomerService;
import cn.com.et.service.customer.CustomerWalletService;


@Controller
@RequestMapping(value = "/bike")
public class BikeController extends BaseController {
	
	@Autowired
	private BikeService bikeService;
	
	@Autowired
	private BikeHistoryService bikeHistoryService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerWalletService customerWalletService;
	
	@Autowired
	private CustomerDeductionService customerDeductionService;
	
	@Autowired
	private BikeProcessFlowService bikeProcessFlowService;
	
	@Autowired
	private BikeReportService bikeReportService;
	
	/**
	 * 
	 * 功能描述：判断用户是否认证
	 * 		      判断用户的余额是否够实用车辆
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
	@RequestMapping(value = "/toIsCertification")
	public void toIsCertification(HttpServletRequest request,HttpServletResponse response) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		try {
			boolean success = true;
			String error = "";
			//得到session的用户信息
			Customer customer = SessionUtil.getSessionUser(request);
			//判断用户是否缴纳押金
			if(1 != customer.getIsPayDeposit()){
				success = false;
				error = "请缴纳押金后在使用KAKE单车!";
			}
			//判断用户是否认证
			if(1 != customer.getIsCertification()){
				success = false;
				error = "请认证后在使用KAKE单车";
			}
			
			/**
			 * 得到用户的钱包信息
			 * 判断用户的钱包是否有钱
			 * 钱小于1块时，不可使用车辆
			 */
			CustomerWallet wallet = customerWalletService.getByCustomerId(customer.getId());
			if(wallet.getRemainingPrice().compareTo(new BigDecimal(1)) < 0){
				success = false;
				error = "您的余额不足，请充值后在进行使用";
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
	 * 功能描述：跳到用车
	 *
	 * @param page
	 * @param openId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-29
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toUsed")
	public ModelAndView toUsed(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("bike/bike_used");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：使用车辆
	 *
	 * @param request
	 * @param response
	 * @param bikeNum
	 * @param address
	 * @param longitude
	 * @param latitude
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-11
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/usedBike",method ={ RequestMethod.GET,RequestMethod.POST})
	public void usedBike(HttpServletRequest request,HttpServletResponse response,String bikeNum) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		try {
			
			/**
			 * 根据车号牌判断是否能够查到车辆
			 * 	能够查到车牌号，返回成功
			 * 	否则，返回失败
			 */
			BikeInfo bike = bikeService.getByBikeNum(bikeNum);
			if(bike == null){
				object.put("success", false);
				object.put("error", "车牌号输入有误");
			}else{
				object.put("success", true);
			}
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
	 * 功能描述：跳到密码界面
	 *
	 * @param page
	 * @param openId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-31
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toBikePwd")
	public ModelAndView toBikePwd(HttpServletRequest request,HttpServletResponse response,String bikeNum,String address,String longitude,String latitude) {
		ModelAndView mv = this.getModelAndView();
		try {
			//根据车牌号得到车辆信息
			BikeInfo bike = bikeService.getByBikeNum(bikeNum);
			//得到session的用户信息
			Customer customer = SessionUtil.getSessionUser(request);
			/**
			 * 插入车辆历史行程
			 */
			BikeHistory bikeHistory = new BikeHistory();
			bikeHistory.setBikeId(bike.getId());//车id
			bikeHistory.setCustomerId(customer.getId());//用户id
			String beginPoint = "("+longitude+","+latitude+")";
			bikeHistory.setBeginPoint(beginPoint);//开始坐标
			bikeHistory.setBeginLocation(address);//开始位置
			bikeHistory.setBeginTime(new Date());//开始时间
			bikeHistory.setBikeNum(bikeNum);//车牌号
			bikeHistory.setIsValid(1);//有效
			bikeHistory.setCreateTime(new Date());//创建时间
			bikeHistoryService.save(bikeHistory);
			mv.addObject("historyId",bikeHistory.getId());
			
			//更新用户成用车状态
			SessionUtil.updateSessionUserisUsed(request, 1, customerService);
			
			mv.addObject("bike",bike);
			String[] bikepwd = bike.getBikePwd().toString().split("");
			mv.addObject("bikepwd", bikepwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("bike/bike_pwd");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到报修页面
	 *
	 * @param page
	 * @param openId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-29
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toReport")
	public ModelAndView toReport(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("bike/bike_report");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：保存报修页面
	 *
	 * @param page
	 * @param openId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-29
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/report")
	public void report(HttpServletRequest request,HttpServletResponse response,String types,String address) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = true;
		try{
			Customer customer = SessionUtil.getSessionUser(request);
			
			/**
			 * 保存用户的报修信息
			 */
			BikeReport report = new BikeReport();
			report.setCustomerId(customer.getId());
			report.setType(types);
			report.setLocation(address);
			report.setIsValid(0);
			report.setCreateTime(new Date());
			bikeReportService.save(report);
			
			/**
			 * 判断用户是否在用车状态
			 * 		是--> 修改用户成不用车状态
			 * 			   将行程置为无效状态
			 */
			if(customer.getIsUsed() == 1){
				SessionUtil.updateSessionUserisUsed(request, 0, customerService);
				bikeHistoryService.updateToIsNotvalid(customer.getId());
			}
			response.setContentType("text/Xml;charset=utf-8");
			out = response.getWriter();  
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}finally{
			object.put("success", success);
			out.println(object);
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * 
	 * 功能描述：跳到用车完成页面
	 *
	 * @param request
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-31
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toBikeFinish")
	public ModelAndView toBikeFinish(HttpServletRequest request,Integer historyId) {
		ModelAndView mv = this.getModelAndView();
		try {
			BikeHistory bikeHistory = null;
			if(historyId == null){
				Customer customer = SessionUtil.getSessionUser(request);
				List<BikeHistory> historylist = bikeHistoryService.getByCustomerId(customer.getId());
				if(historylist.size() > 0){
					bikeHistory = historylist.get(0);
				}
			}else{
				bikeHistory = bikeHistoryService.getById(historyId);
			}
			mv.addObject("bikeHistory", bikeHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("bike/bike_finish");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：结束骑行
	 *
	 * @param request
	 * @param response
	 * @param historyId 车辆历史id
	 * @param address  结束地址
	 * @param longitude  经度
	 * @param latitude  纬度
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-4-12
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/finishBike",method ={ RequestMethod.GET,RequestMethod.POST})
	public void finishBike(HttpServletRequest request,HttpServletResponse response,Integer historyId,String address,String longitude,String latitude) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = true;
		String error = "";
		try {
			Customer customer = SessionUtil.getSessionUser(request);
			/**
			 * 计算价格
			 */
			Date endDate = new Date();
			BikeHistory bikeHistory2 = bikeHistoryService.getById(historyId);
			Double price = CalculatePrice.beginAndEndCalculatePrice(bikeHistory2.getBeginTime(), endDate);
			BigDecimal price_ = new BigDecimal(price);
			object.put("price", price);
			
			/**
			 * 更新车辆历史记录
			 */
			BikeHistory bikeHistory = new BikeHistory();
			bikeHistory.setId(historyId);
			bikeHistory.setEndTime(endDate);
			bikeHistory.setEndLocation(address);
			String endPoint = "("+longitude+","+latitude+")";
			bikeHistory.setEndPoint(endPoint);
			bikeHistory.setPrice(price_);
			bikeHistory.setIsDeduction(1);//抵扣信息
			bikeHistoryService.updateFinishBike(bikeHistory);
			
			/**
			 * 更新用户钱包
			 */
			CustomerWallet wallet = customerWalletService.getByCustomerId(customer.getId());
			BigDecimal remainPrice = wallet.getRemainingPrice().subtract(price_);
			wallet.setRemainingPrice(remainPrice);
			customerWalletService.updateCustomerPrice(wallet);
			
			/**
			 * 更新用户的抵扣信息
			 */
			CustomerDeduction deduction = customerDeductionService.getByCustomerId(customer.getId());
			if(deduction == null){
				deduction = new CustomerDeduction();
				deduction.setCustomerId(customer.getId());
				deduction.setConsumptionSum(price_);
				deduction.setDeductionSum(new BigDecimal(0));
				deduction.setNotDeductionSum(price_);
				deduction.setCreateTime(new Date());
				customerDeductionService.save(deduction);
			}else{
				BigDecimal consumptionSum = deduction.getConsumptionSum().add(price_);
				BigDecimal notDeductionSum = deduction.getNotDeductionSum().add(price_);
				deduction.setConsumptionSum(consumptionSum);
				deduction.setNotDeductionSum(notDeductionSum);
				customerDeductionService.updatePrice(deduction);
			}
			
			/**
			 * 插入消费明细
			 */
			BikeProcessFlow flow = new BikeProcessFlow();
			flow.setBikeId(bikeHistory2.getBikeId());
			flow.setCustomerId(customer.getId());
			flow.setType(1);
			flow.setPrice(price_);
			flow.setNote(customer.getMobile()+"进行了"+address+"骑行！");
			flow.setCreateTime(new Date());
			bikeProcessFlowService.save(flow);
			
			/**
			 * 将用户置为无用车状态
			 */
			SessionUtil.updateSessionUserisUsed(request, 0, customerService);
			
			response.setContentType("text/Xml;charset=utf-8");
			out = response.getWriter();  
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			error = "系统出错，请联系管理员！13592684467";
		}finally{
			object.put("success", success);
			object.put("error", error);
			out.println(object);
			if(out != null){
				out.close();
			}
		}
	}
}
