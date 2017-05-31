package cn.com.et.commons.weixin;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import cn.com.et.constant.GlobalConstant;
import cn.com.et.constant.WXConstants;
import cn.com.et.ident.JsapiTicket;
import cn.com.et.utils.DateUtil;
import cn.com.et.utils.StringUtil;
import cn.com.et.wx.notify.req.PayNotifyReq;
import cn.com.et.wx.notify.rsp.PayNotifyRsp;

import com.tencent.WXPay;
import com.tencent.common.BeanToMapUtil;
import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.common.XMLParser;
import com.tencent.protocol.pay_protocol.PubNoPayReqData;
import com.tencent.protocol.pay_protocol.PubNoPayResData;

public class WXPayUtil {
	private static Log logger  = LogFactory.getLog(GlobalConstant.WX_LOG_NAME);
	private static Lock lock = new ReentrantLock();//锁对象
	//支付通知缓存
	public static ConcurrentHashMap<String,Long> cacheNotify = new ConcurrentHashMap<String,Long>();
	public enum E_PayReqData{
		body,outTradeNo,totalFee,spBillCreateIP,openId
	}
	/**
	 * 初始化微信SDK接口
	 */
	public void initWXSDKConfiguration(){
		if(StringUtil.isEmpty(Configure.getKey())){
			String key = WXCfg.getBaseKey();
			String appID = WXCfg.getBaseAppid();
			String mchID = WXCfg.getBaseMchid();
			String sdbMchID = "";
			String certLocalPath = WXCfg.getBaseCertLoadPath();
			String certPassword = WXCfg.getBaseCertPassword();
			String notifyUrl = WXCfg.getBaseNotifyUrl();
			
			WXPay.initSDKConfiguration(key,appID,mchID,sdbMchID,certLocalPath,certPassword,notifyUrl);
		}
	}
	
	/**
	 * 获取微信支付请求对象
	 * @param map
	 * @return
	 */
	public PubNoPayReqData getPayReqData(Map<String,String> map){
		PubNoPayReqData payReq = null;
		String body = map.get(E_PayReqData.body.name()).toString();//商品描述
		String outTradeNo = map.get(E_PayReqData.outTradeNo.name()).toString();//商户订单号
		Double _totalFee = Double.valueOf(map.get(E_PayReqData.totalFee.name()).toString());//总金额
		Integer totalFee = new BigDecimal(Double.toString(_totalFee*100)).intValue();
		String spBillCreateIP = map.get(E_PayReqData.spBillCreateIP.name()).toString();//终端IP
		String openId = map.get(E_PayReqData.openId.name()).toString();//用户标识
		
		payReq = new PubNoPayReqData(body, outTradeNo, totalFee, spBillCreateIP, openId);
		return payReq;
	}
	
	/**
	 * 获取微信JSSDK的config信息
	 * @param urlStr
	 * @return
	 */
    public Map<String,Object> getJSSDKConfig(String urlStr) {  
		/*
		wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '', // 必填，公众号的唯一标识
		    timestamp: , // 必填，生成签名的时间戳
		    nonceStr: '', // 必填，生成签名的随机串
		    signature: '',// 必填，签名，见附录1
		    jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		}); 
		*/
		Map<String,Object> wxConfig = new HashMap<String,Object>();
		JsapiTicket ticketObj = WXCommon.getJsapiTicket();
		String ticket = ticketObj.getTicket();
		String nonceStr = RandomStringGenerator.getRandomStringByLength(32);
		String timeStamp = DateUtil.getTimeStamp();
		String url = urlStr;
		String signatureVal = "";
		try {
			signatureVal = WXCommon.getJssdkSignature(ticket, nonceStr, timeStamp, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   wxConfig.put("ticket", ticket);
	   wxConfig.put("url", url);
       wxConfig.put("debug", new Boolean(true));
       String appID = WXCfg.getBaseCfg().getProperty(WXCfg.E_BASE.BASE_APPID.name());
       wxConfig.put("appId", appID);
       wxConfig.put("timestamp", timeStamp);
       wxConfig.put("nonceStr", nonceStr);
       wxConfig.put("signature", signatureVal);
       
       return wxConfig;
    }
	
	/**
	 * 获取微信支付的调用参数
	 * @param pubNoPayReq
	 * @return
	 */
    public Map<String,Object> getWXPayConfig(PubNoPayReqData pubNoPayReq) {
    	//商品描述    	body
    	//商户订单号  	out_trade_no
    	//总金额 		total_fee
    	//终端IP 	spbill_create_ip
    	//用户标识 	openid
    	//公众账号	appid
		//商户号		mch_id
		//随机字符串	nonce_str
		//通知地址	notify_url
		//签名		sign
		//交易类型	trade_type
    	
    	//通过以上参数获取prepay_id;
    	PubNoPayReqData pubNoPayReqData = pubNoPayReq;
		
		String rspResult = "";
		PubNoPayResData rsp = null;
		Map<String,Object> payConfigs = new HashMap<String,Object>();
		try {
			rspResult = WXPay.requestPayService(pubNoPayReqData);
			Util.log("API，返回的数据是：\n"+rspResult);
			
			rsp = (PubNoPayResData)Util.getObjectFromXML(rspResult,PubNoPayResData.class);
			//判断字段在return_code 和result_code都为SUCCESS的时候有返回
			if(WXConstants.WX_SUCCESS.equals(rsp.getReturn_code()) && WXConstants.WX_SUCCESS.equals(rsp.getResult_code())){
				//能获取到prepay_id
				logger.debug("成功获取到prepay_id:"+rsp.getPrepay_id());
				//构建支付对象参数
				payConfigs.put("appId", rsp.getAppid());
				payConfigs.put("timeStamp",DateUtil.getTimeStamp());
				payConfigs.put("nonceStr",RandomStringGenerator.getRandomStringByLength(32));
				String prepayId = "prepay_id="+rsp.getPrepay_id();
				payConfigs.put("package",prepayId);
				payConfigs.put("signType", "MD5");
				String signVal = Signature.getSign(payConfigs);
				payConfigs.put("paySign",signVal);
				payConfigs.put("packageval",prepayId);
			}else if(WXConstants.WX_SUCCESS.equals(rsp.getReturn_code())){//判断return_code为SUCCESS的时候有返回
				//未返回prepay_id,返回错误信息
				logger.debug("错误代码"+rsp.getErr_code());
				logger.debug("错误代码描述"+rsp.getErr_code_des());
				payConfigs.put("RETURN_CODE",rsp.getReturn_code());
				payConfigs.put("ERR_CODE", rsp.getErr_code());
				payConfigs.put("ERR_CODE_DES", rsp.getErr_code_des());
			}else if(WXConstants.WX_FAIL.equals(rsp.getReturn_code())){//判断return_code为SUCCESS的时候有返回
				//未返回prepay_id,返回错误信息
				logger.debug("错误代码"+rsp.getErr_code());
				logger.debug("错误代码描述"+rsp.getErr_code_des());
				payConfigs.put("RETURN_CODE",rsp.getReturn_code());
				payConfigs.put("ERR_CODE", rsp.getErr_code());
				payConfigs.put("ERR_CODE_DES", rsp.getErr_code_des());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return payConfigs;
    }
	
	/**
     * 处理微信服务器返的订单通知
     * @return
     */
    public Map<String,Object> handlerWxPayNotify(String receiveXml) {
    	Map<String,Object> rspMap = null;
    	try{
			logger.info("handlerWxPayNotify::开始");
			
			initWXSDKConfiguration();
			 try {
				 rspMap = XMLParser.getMapFromXML(receiveXml);
				 String oldSignVal = (String)rspMap.get("sign");
				 logger.debug("oldSignVal::"+oldSignVal);
				 //true:处理  false:不处理
				 boolean isDoNotify = checkedNotify(oldSignVal);
				 PayNotifyRsp rspState = new PayNotifyRsp();
				 if(isDoNotify){
					 //做验签名检验
					 String newSignVal = Signature.getSign(rspMap);//正式使用
					 //String newSignVal = oldSignVal;//测试使用
					 logger.debug("newSignVal::"+newSignVal);
					 if(oldSignVal.equals(newSignVal)){
						 //通知请求业务处理
						 String returnCode = (String)rspMap.get("return_code");
						 
						 //微信端通知支付成功消息处理
						 if(WXConstants.WX_SUCCESS.equalsIgnoreCase(returnCode)){
							 PayNotifyReq wxOrder = getPayNotifyReq(rspMap);
							 rspMap.put("RspSuccessObj", wxOrder);
							 rspMap.put("cacheKey", newSignVal);
							 
							 rspState.setReturn_code(WXConstants.WX_SUCCESS);
							 rspState.setReturn_msg("OK");
							 rspMap.put("RspState", rspState);
							 
							 logger.info("handlerWxPayNotify::微信端通知支付成功消息处理");
							 
						 }else{//微信端通知支付失败消息处理
							 String returnMsg = (String)rspMap.get("return_msg");
							 logger.debug("returnMsg="+returnMsg);
							 rspState.setReturn_code(WXConstants.WX_FAIL);
							 rspState.setReturn_msg("参数格式校验错误");
							 rspMap.put("RspState", rspState);
							 logger.info("handlerWxPayNotify::微信端通知支付失败消息处理");
						 }
					 }else{
						 rspState.setReturn_code(WXConstants.WX_FAIL);
						 rspState.setReturn_msg("签名失败");
						 rspMap.put("RspState", rspState);
						 logger.info("handlerWxPayNotify::微信端通知数据验签名失败");
					 }
				 }else{
					 //通知已处理过的不再处理
					 rspState.setReturn_code(WXConstants.WX_SUCCESS);
					 rspState.setReturn_msg("OK");
					 rspMap.put("RspState", rspState);
				 }
				 logger.info("handlerWxPayNotify::微信支付通知处理后的结果：\n"+rspMap);
			 }finally{
				 //lock.unlock();
			 }
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		logger.info("handlerWxPayNotify::结束");
		return rspMap;
    }
    
    private boolean checkedNotify(String key) {
    	Long curTime = (System.currentTimeMillis())/(1000*60);
    	boolean curState = false;
		if(!cacheNotify.containsKey(key)){
			addCacheData(key, curTime);
			curState = true;
			logger.debug("cacheNotify::"+cacheNotify);
		}
		
		//清理缓存中失效的数据,1个半小时内未处理的会在下一次做清除
		for(Map.Entry<String, Long> entry : cacheNotify.entrySet()){
			Long saveTime = entry.getValue();
			Long t = curTime-saveTime;
			if(t>90){
				removeCacheData(entry.getKey());
			}
		}
    	return curState;
    }
    
    private static void removeCacheData(String key) {
    	lock.lock();
    	cacheNotify.remove(key);
		lock.unlock();
    }
    
    private static void addCacheData(String key,Long val) {
    	lock.lock();
    	cacheNotify.put(key,val);
		lock.unlock();
    }
    
    /**
     * 将微信通知的XML形式的消息转换为对象
     * @param map
     * @return
     */
    private PayNotifyReq getPayNotifyReq(Map<String,Object> map){
    	PayNotifyReq notifyObj = null;
    	try {
    		notifyObj = (PayNotifyReq)BeanToMapUtil.convertMap(PayNotifyReq.class, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return notifyObj;
    }
}
