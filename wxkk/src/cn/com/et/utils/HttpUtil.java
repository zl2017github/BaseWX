package cn.com.et.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	/**
	 * Get方式提交请求返回JSON
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static JSONObject doGetToJson(String url, String encoding) {
		String result = doGetToStr(url, encoding);
		JSONObject jsonObject = JSON.parseObject(result);
		return jsonObject;
	}
	
	/**
	 * Get方式提交请求
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String doGetToStr(String url, String encoding) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		String result = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, encoding);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * post方式提交指定的内容信息
	 * @param url
	 * @param parms
	 * @param encoding 可以指定为null，默认为UTF-8编码
	 * @return
	 */
	public static String doPost(String url, String context, String encoding) {
		return doPost(url,context,null,encoding);
	}
	
	/**
	 * post方式提交指定的参数
	 * @param url
	 * @param parms
	 * @param encoding
	 * @return
	 */
    public static String doPost(String url,Map<String,String> parms,String encoding) {  
    	return doPost(url,null,parms,encoding);
    }
    
    /**
     * post方式提交指定的参数及内容信息
     * @param url
     * @param context
     * @param parms
     * @param encoding
     * @return
     */
	public static String doPost(String url, String context, Map<String,String> parms, String encoding) {
		//无编码参数，默认为UTF-8编码
		if(null == encoding)encoding = "UTF-8";
		//无内容信息，默认为空字符串
		if(null == context)context = "";
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost    
        HttpPost httpPost = new HttpPost(url);
        // 创建参数队列    
        List<BasicNameValuePair> nvp = null;
        if(null!=parms && !parms.isEmpty()){
        	nvp = new ArrayList<BasicNameValuePair>();  
        	for(Map.Entry<String, String> e : parms.entrySet()){
        		nvp.add(new BasicNameValuePair(e.getKey(), e.getValue()));  
        	}
        }
        UrlEncodedFormEntity uefEntity; 
		String result = "";
		try {
			if(nvp!=null){
				uefEntity = new UrlEncodedFormEntity(nvp, encoding);  
				httpPost.setEntity(uefEntity); 
			}
	            
			StringEntity entity = new StringEntity(context, encoding);
			entity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(entity);
			
			
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {  
                HttpEntity rspEntity = response.getEntity();  
                if (rspEntity != null) {  
                   result  = EntityUtils.toString(rspEntity, encoding);
                }  
            } finally {  
                response.close();  
            }  
		} catch (ClientProtocolException e) {  
	        e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return result;
	}
}
