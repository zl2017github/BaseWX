package cn.com.et.commons.weixin;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.com.et.commons.IdentUtil;
import cn.com.et.commons.IdentUtil.TYPE;
import cn.com.et.constant.WXConstants;
import cn.com.et.ident.AccessToken;
import cn.com.et.ident.JsapiTicket;
import cn.com.et.utils.HttpUtil;
import cn.com.et.utils.security.SignUtil;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

public class WXCommon {
	private static Log logger  = LogFactory.getLog(WXCommon.class);
	/**
	 * 获取accessToken 先从本地获取，如果本地未获取到或本地的已失效再去微信平台获取
	 * 
	 * @return
	 */
	public static AccessToken getAccessToken() {
		AccessToken token = getAccessTokenFromLocal();
		if (null == token) {
			token = getAccessTokenFromWX();
			if (null != token) {
				token.setCreateTime(System.currentTimeMillis());
				//缓存accessToken
				IdentUtil.saveIdent(token,IdentUtil.TYPE.accessToken);
			}
		}
		return token;
	}

	/**
	 * 本地获取accessToken
	 * 
	 * @return
	 */
	public static AccessToken getAccessTokenFromLocal() {
		logger.debug("getAccessTokenFromLocal");
		AccessToken token = null;
		Object obj = IdentUtil.getIdent(IdentUtil.TYPE.accessToken);
		if (null != obj) {
			token = (AccessToken) obj;
			boolean statu = token.ifValidate(System.currentTimeMillis(), 1800);
			if (statu) {
				return token;
			}
			token = null;
			logger.debug("access token Invalid");
		}
		return token;
	}

	/**
	 * 微信端获取accessToken
	 * 
	 * @return
	 */
	public static AccessToken getAccessTokenFromWX() {
		logger.debug("getAccessTokenFromWX");
		AccessToken token = new AccessToken();
		Properties baseCfg = WXCfg.getBaseCfg();
		String url = WXConstants.ACCESS_TOKEN_URL
				.replace("APPID",WXCfg.getBaseAppid())
				.replace("APPSECRET", WXCfg.getBaseAppsecret());
		JSONObject jsonObject = HttpUtil.doGetToJson(url, "UTF-8");
		if (jsonObject != null) {
			token.setToken(jsonObject.getString("access_token"));
			token.setExpires(jsonObject.getLong("expires_in"));
		}
		return token;
	}

	/**
	 * 获取jsapi_ticket 先从本地获取，如果本地未获取到或本地的已失效再去微信平台获取
	 * 
	 * @return
	 */
	public static JsapiTicket getJsapiTicket() {
		JsapiTicket ticket = getJsapiTicketFromLocal();
		if (null == ticket) {
			ticket = getJsapiTicketFromWX();
			if (null != ticket) {
				ticket.setCreateTime(System.currentTimeMillis());
				IdentUtil.saveIdent(ticket,IdentUtil.TYPE.jsapiTicket);
			}
		}
		return ticket;
	}

	/**
	 * 从本地获取jsapi_ticket
	 * 
	 * @return
	 */
	public static JsapiTicket getJsapiTicketFromLocal() {
		logger.debug("getJsapiTicketFromLocal");
		JsapiTicket ticket = null;
		Object obj = IdentUtil.getIdent(IdentUtil.TYPE.jsapiTicket);
		if (null != obj) {
			ticket = (JsapiTicket) obj;
			boolean statu = ticket.ifValidate(System.currentTimeMillis(), 1800);
			if (statu) {
				return ticket;
			}
			ticket = null;
			logger.debug("jsapi ticket Invalid");
		}
		return ticket;
	}

	/**
	 * 从微信端获取jsapi_ticket
	 * 
	 * @return
	 */
	public static JsapiTicket getJsapiTicketFromWX() {
		AccessToken token = getAccessToken();
		if (null == token) {
			return null;
		}
		String url = WXConstants.WX_JSSDK_TOKEN_URL.replace("ACCESS_TOKEN",token.getToken());
		JSONObject jsonObject = HttpUtil.doGetToJson(url, "UTF-8");
		/**
		 * 返的结果 "errcode":0, "errmsg":"ok", "ticket":
		 * "bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA"
		 * , "expires_in":7200
		 */
		JsapiTicket ticket = null;
		if (jsonObject != null) {
			ticket = new JsapiTicket();
			long errcode = jsonObject.getLong("errcode");
			if (errcode != 0L) {
				return null;
			}
			ticket.setErrmsg(jsonObject.getString("errmsg"));
			ticket.setErrcode(errcode);
			ticket.setTicket(jsonObject.getString("ticket"));
			ticket.setExpires(jsonObject.getLong("expires_in"));
		}
		return ticket;
	}

	// 微信版本是否为5.0或以上版本，此来判断是否支持微信支付功能
	public static boolean ifWX5(String userAgen) {
		boolean flag = false;
		final String flagVal = "MicroMessenger";
		if (StringUtils.isEmpty(userAgen) || !userAgen.contains(flagVal)) {
			return flag;
		}
		int index = userAgen.indexOf(flagVal);
		if (index >= 0) {
			String s1 = userAgen.substring(index, userAgen.length());
			int point = s1.indexOf("/");
			String val = s1.substring(point + 1, point + 2);
			try {
				int intVal = Integer.parseInt(val);
				if (intVal >= 5) {
					flag = true;
					return flag;
				}
			} catch (Exception e) {
				return flag;
			}
		} else {
			return flag;
		}
		return flag;
	}
	
	/**
	 * 微信公众平台验证签名
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
    public static boolean checkSignature(String token,String signature, String timestamp, String nonce) {  
        String[] arr = new String[] {token, timestamp, nonce };  
        // 将token、timestamp、nonce三个参数进行字典序排序  
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        MessageDigest md = null;  
        String tmpStr = SignUtil.getSignatureForStr(content.toString(), "SHA-1");
  
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
        return tmpStr != null ? tmpStr.toUpperCase().equals(signature.toUpperCase()) : false;  
    }  
    
    /**
     * 获得微信平台链接的签名
     * @param ticket
     * @param nonceStr
     * @param timeStamp
     * @param url
     * @return
     * @throws Exception
     */
    public static String getJssdkSignature(String ticket, String nonceStr, String timeStamp, String url) throws Exception {
        String sKey = new StringBuilder("jsapi_ticket=").append(ticket)
        	.append("&noncestr=").append(nonceStr)
        	.append("&timestamp=").append(timeStamp)
        	.append("&url=").append(url)
        	.toString();
       logger.info("getJssdkSignature sKey::"+sKey);
        return SignUtil.getSignatureBySha1(sKey);
    }
    
    /**
	 * 获取微信的openId
	 * 
	 * @param url
	 * @return
	 */
	public static String getWxOpenId(String url) {
		/*
		 * 正确时返回的JSON数据包如下： { "access_token":"ACCESS_TOKEN", "expires_in":7200,
		 * "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE",
		 * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL" }
		 * 
		 * 错误时微信会返回JSON数据包如下（示例为Code无效错误）:
		 * {"errcode":40029,"errmsg":"invalid code"}
		 */
		JSONObject json = HttpUtil.doGetToJson(url, "UTF-8");
		Integer errorCode = (Integer) json.get("errcode");
		String openId = "";
		if (null == errorCode) {
			openId = json.getString("openid");
		} else {
			logger.debug("errorCode::" + errorCode);
		}
		return openId;
	}
}
