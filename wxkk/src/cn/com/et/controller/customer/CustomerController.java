package cn.com.et.controller.customer;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.com.et.commons.session.SessionUtil;
import cn.com.et.constant.GlobalConstant;
import cn.com.et.controller.base.BaseController;
import cn.com.et.entity.bike.BikeHistory;
import cn.com.et.entity.customer.Customer;
import cn.com.et.entity.customer.CustomerDeduction;
import cn.com.et.entity.customer.CustomerDeposit;
import cn.com.et.entity.customer.CustomerFeedBack;
import cn.com.et.entity.customer.CustomerOtherInfo;
import cn.com.et.entity.customer.CustomerWallet;
import cn.com.et.service.bike.BikeHistoryService;
import cn.com.et.service.customer.CustomerDeductionService;
import cn.com.et.service.customer.CustomerDepositService;
import cn.com.et.service.customer.CustomerFeedBackService;
import cn.com.et.service.customer.CustomerOtherInfoService;
import cn.com.et.service.customer.CustomerService;
import cn.com.et.service.customer.CustomerWalletService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController  {
	private Log logger  = LogFactory.getLog(this.getClass());
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerDeductionService customerDeductionService;
	
	@Autowired
	private CustomerWalletService customerWalletService;
	
	@Autowired
	private BikeHistoryService bikeHistoryService;
	
	@Autowired
	private CustomerFeedBackService customerFeedBackService;
	
	@Autowired
	private CustomerOtherInfoService customerOtherInfoService;
	
	@Autowired
	private CustomerDepositService customerDepositService;
	
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
	@RequestMapping(value = "/index")
	public ModelAndView query(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		String url = "";
		String wechatId = null;
		try {
			/**
			 * 如果没有传入wechatId,从session里面去取
			 */
			if(wechatId == null){
				wechatId = request.getSession().getAttribute(GlobalConstant.SESSION_USER_WECHATID)+"";
				if("null".equals(wechatId)){
					wechatId = "18501002130";
					request.getSession().setAttribute(GlobalConstant.SESSION_USER_WECHATID,wechatId);
				}
			}
			boolean login = loadCustomerInfo(request,response,wechatId);
			if(login){
				Customer customer = customerService.getByWechatId(wechatId);
				/**
				 * 判断用户是否在用车状态，要是在用车状态，直接跳到结束界面
				 */
				if(customer.getIsUsed() == 1){
					mv.addObject("customer", customer);
					url = "redirect:../bike/toBikeFinish.do";
				}else{
					if(customer != null){
						CustomerDeduction deduction = customerDeductionService.getByCustomerId(customer.getId());
						mv.addObject("deduction", deduction);
					}
					mv.addObject("customer", customer);
					url = "index";
				}
			}else{
				url = "redirect:../"+GlobalConstant.LOGIN;
				mv.addObject("wechatId", wechatId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName(url);
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：加载用户信息
	 *
	 * @param request
	 * @param response
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-30
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public boolean loadCustomerInfo(HttpServletRequest request,HttpServletResponse response,String wechatId){
		Cookie cookies[]=request.getCookies();//声明一个cookie对象
		String userMobile=null;//登录的用户名 
		boolean flag = false;
		try {
			if(cookies != null){
				for (int i = 0; i < cookies.length; i++){  
				    if(cookies[i].getName().equals(GlobalConstant.USER_NAME)){
				    	userMobile = cookies[i].getValue();
				    }
				}
				/**
				 * 根据用户名和wechatId判断跟系统中是否一样
				 * 		是：登录成功
				 * 		否：调到登录页
				 */
				Customer customer = new Customer();
				customer.setMobile(userMobile);
				customer.setWechatId(wechatId);
				customer = customerService.login(customer);
				
				if(customer != null){
					SessionUtil.addSessionUser(request, wechatId, userMobile, customer);
					request.getSession().setAttribute(GlobalConstant.SESSION_USER_WECHATID,wechatId);
					flag = true;
				}else{
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * 功能描述：查看抵扣详情
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
	@RequestMapping(value = "/deduction/description")
	public ModelAndView deductionDescription() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("customer/customer_deduction_description");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到我的
	 *
	 * @param request
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-28
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toMy")
	public ModelAndView toMy(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView();
		try {
			Customer customer = SessionUtil.getSessionUser(request);
			if(customer != null){
				//抵扣券
				CustomerDeduction deduction = customerDeductionService.getByCustomerId(customer.getId());
				//钱包
				CustomerWallet wallet = customerWalletService.getByCustomerId(customer.getId());
				mv.addObject("deduction", deduction);
				mv.addObject("wallet", wallet);
				mv.addObject("customer", customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("customer/customer_my");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到我的行程页
	 *
	 * @param request
	 * @param customerId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-28
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toMyTrip")
	public ModelAndView toMyTrip(HttpServletRequest request,Integer customerId) {
		ModelAndView mv = this.getModelAndView();
		try {
			List<BikeHistory> historyList = bikeHistoryService.getByCustomerId(customerId);
			mv.addObject("historyList", historyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("customer/customer_trip");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到我的钱包
	 *
	 * @param request
	 * @param customerId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-28
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toMyWallet")
	public ModelAndView toMyWallet(HttpServletRequest request,Integer customerId) {
		ModelAndView mv = this.getModelAndView();
		try {
			//钱包
			CustomerWallet wallet = customerWalletService.getByCustomerId(customerId);
			mv.addObject("wallet", wallet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("customer/customer_wallet");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到抵扣券列表
	 *
	 * @param request
	 * @param customerId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-28
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toMyDeduction")
	public ModelAndView toMyDeduction(HttpServletRequest request,Integer customerId) {
		ModelAndView mv = this.getModelAndView();
		try {
			CustomerDeduction deduction = customerDeductionService.getByCustomerId(customerId);
			mv.addObject("deduction", deduction);
			List<BikeHistory> historyList = bikeHistoryService.getByCustomerId(customerId);
			mv.addObject("historyList", historyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("customer/customer_deduction_list");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到我的消息列表
	 *
	 * @param request
	 * @param customerId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-28
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toMyNews")
	public ModelAndView toMyNews(HttpServletRequest request,Integer customerId) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("customer/customer_news");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到用户须知
	 *
	 * @param request
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-29
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toMyInstructions")
	public ModelAndView toMyInstructions(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("customer/customer_agreement");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到意见反馈页面
	 *
	 * @param request
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-29
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toFeedBack")
	public ModelAndView toFeedBack(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("customer/customer_feedback");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：意见反馈
	 *
	 * @param request
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-29
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/feedBack")
	public void feedBack(HttpServletRequest request,HttpServletResponse response,String text) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = true;
		try{
			Customer customer = SessionUtil.getSessionUser(request);
			
			/**
			 * 保存用户的反馈意见
			 */
			CustomerFeedBack back = new CustomerFeedBack();
			back.setCustomerId(customer.getId());
			back.setText(text);
			back.setCreateTime(new Date());
			back.setIsValid(0);
			customerFeedBackService.save(back);
			
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
	 * 功能描述：跳到充值页面
	 *
	 * @param request
	 * @param customerId
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2017-3-31
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/toMyWalletRecharge")
	public ModelAndView toMyWalletRecharge(HttpServletRequest request,Integer customerId) {
		ModelAndView mv = this.getModelAndView();
		try {
			mv.addObject("customerId", customerId);
			//钱包
			CustomerWallet wallet = customerWalletService.getByCustomerId(customerId);
			mv.addObject("wallet", wallet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("customer/customer_recharge");
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：跳到
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
	@RequestMapping(value = "/toAuthChoice")
	public ModelAndView toAuthChoice(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView();
		String url = "";
		//获取用户
		Customer customer = SessionUtil.getSessionUser(request);
		/**
		 *  判断用户是否认证，已认证直接跳到缴纳押金页面
		 *  若没有认证，先认证后在缴纳押金
		 */
		if(customer.getIsCertification() != 1){
			url = "system/nameAuth";
		}else if(customer.getIsPayDeposit() != 1){
			mv.addObject("deposit", GlobalConstant.DEPOSIT);
			url = "system/depositPayment";
		}
		mv.setViewName(url);
		return mv;
	}
	
	/**
	 * 
	 * 功能描述：名字认证
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
	@RequestMapping(value = "/nameAuth",method=RequestMethod.POST)
	public void nameAuth(HttpServletRequest request,HttpServletResponse response,CustomerOtherInfo info) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = false;//注册成功
		String error = "";
		try {
			/**
			 * 根据身份证判断身份证号是否已经存在
			 */
			CustomerOtherInfo oinfo = customerOtherInfoService.getByIdNum(info.getIdNumber());
			if(oinfo != null){
				success = false;
				error = "该身份证号已经存在，请重新输入!";
			}else{
				Customer customer = SessionUtil.getSessionUser(request);
				String mobile = customer.getMobile();
				
				info.settCustomerId(customer.getId());
				info.setType(1);
				info.setCreateTime(new Date());
				customerOtherInfoService.save(info);
				
				//移除session
				SessionUtil.removeSessionUser(request, customer.getWechatId(), customer.getMobile());
				
				//customer.setIsCertification(2);//待认证
				customer.setIsCertification(1);//已认证
				customerService.updateCustomerIsCertification(customer);
				//更新session用户信息
				SessionUtil.addSessionUser(request, customer.getWechatId(), mobile, customer);
				success = true;
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
	 * 功能描述：认证
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
	@RequestMapping(value = "/certification",method=RequestMethod.POST)
	public ModelAndView certification(HttpServletRequest request,HttpServletResponse response,MultipartFile positivePhoto,MultipartFile negativePhoto) {
		ModelAndView mv = this.getModelAndView();
		try {
			Customer customer = SessionUtil.getSessionUser(request);
			String mobile = customer.getMobile();
			String url = "";//图片位置
			//身份证正面
			if (!positivePhoto.isEmpty()) {
				String basePath = request.getRealPath("picture")+"/"+mobile;
				//获得文件名
		        String fileUrl= positivePhoto.getOriginalFilename();
		        //在某些操作系统上,item.getName()方法会返回文件的完整名称,即包括路径
		        String fileType = fileUrl.substring(fileUrl.lastIndexOf(".")); //截取文件格式
		        //自定义方式产生文件名
		        String serialName = String.valueOf(System.currentTimeMillis());
		        String filePath = basePath+"/"+serialName + fileType;
		        //转存文件
		        File file = new File(filePath);
		        if (!file.exists()) {  
	                file.mkdirs();  
	            }  
		        positivePhoto.transferTo(file);
		        url = filePath;
	        }
			//身份证反面
			if (!negativePhoto.isEmpty()) {
				String basePath = request.getRealPath("picture")+"/"+mobile;
				//获得文件名
		        String fileUrl= positivePhoto.getOriginalFilename();
		        //在某些操作系统上,item.getName()方法会返回文件的完整名称,即包括路径
		        String fileType = fileUrl.substring(fileUrl.lastIndexOf(".")); //截取文件格式
		        //自定义方式产生文件名
		        String serialName = String.valueOf(System.currentTimeMillis());
		        String filePath = basePath+"/"+serialName + fileType;
		        //转存文件
		        File file = new File(filePath);
		        if (!file.exists()) {  
	                file.mkdirs();  
	            }  
		        negativePhoto.transferTo(file);
		        url = url+";"+filePath;
	        }
	        
			//保存图片
			CustomerOtherInfo info = new CustomerOtherInfo();
			info.settCustomerId(customer.getId());
			info.setType(2);
			info.setCerPicture(url);
			info.setCreateTime(new Date());
			customerOtherInfoService.save(info);
			
			//移除session
			SessionUtil.removeSessionUser(request, customer.getWechatId(), customer.getMobile());
			
			customer.setIsCertification(2);//待认证
			SessionUtil.addSessionUser(request, customer.getWechatId(), mobile, customer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("system/depositPayment");
		return mv;
	}
	
	
	/**
	 * 
	 * 功能描述：缴纳押金
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
	@RequestMapping(value = "/depositPayment",method=RequestMethod.POST)
	public void depositPayment(HttpServletRequest request,HttpServletResponse response) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = false;//缴纳失败
		String error = "";
		try {
			/**
			 * 更新用户信息
			 */
			Customer customer = SessionUtil.getSessionUser(request);
			customer.setIsPayDeposit(1);//已缴纳押金
			customerService.updateCustomerIsPayDeposit(customer);
			//移除session
			SessionUtil.removeSessionUser(request, customer.getWechatId(), customer.getMobile());
			//更新session用户信息
			SessionUtil.addSessionUser(request, customer.getWechatId(), customer.getMobile(), customer);
			
			
			/** 
			 * 判断用户钱包是否存在
			 *   不存在生成用户钱包
			 */
			CustomerWallet wallet = customerWalletService.getByCustomerId(customer.getId());
			if(wallet == null){
				wallet = new CustomerWallet();
				wallet.setCustomerId(customer.getId());
				wallet.setRemainingPrice(new BigDecimal(0));
				wallet.setDepositPrice(new BigDecimal(GlobalConstant.DEPOSIT));
				wallet.setSource(1);
				wallet.setSourceAccount(null);
				wallet.setCreateTime(null);
				customerWalletService.save(wallet);
				success = true;
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
	 * 功能描述：退还押金
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
	@RequestMapping(value = "/exitDeposit",method=RequestMethod.POST)
	public void exitDeposit(HttpServletRequest request,HttpServletResponse response) {
		JSONObject object = new JSONObject();
		PrintWriter out =null;
		boolean success = false;//退还失败
		String error = "";
		try {
			/**
			 * 更新用户信息
			 */
			Customer customer = SessionUtil.getSessionUser(request);
			if(customer.getIsPayDeposit() == 1){
				customer.setIsPayDeposit(0);//未缴纳押金
				customerService.updateCustomerIsPayDeposit(customer);
				//移除session
				SessionUtil.removeSessionUser(request, customer.getWechatId(), customer.getMobile());
				//更新session用户信息
				SessionUtil.addSessionUser(request, customer.getWechatId(), customer.getMobile(), customer);
				
				/** 
				 * 提交用户退还押金申请
				 */
				CustomerWallet wallet = customerWalletService.getByCustomerId(customer.getId());
				CustomerDeposit deposit = new CustomerDeposit();
				deposit.setCustomerId(customer.getId());
				deposit.setWalletId(wallet.getId());
				deposit.setDepositPrice(new BigDecimal(GlobalConstant.DEPOSIT));
				deposit.setType(1);
				deposit.setApplyTime(new Date());
				deposit.setCreateTime(new Date());
				customerDepositService.save(deposit);
				success = true;
			}else{
				success = false;
				error = "您未缴纳押金";
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
