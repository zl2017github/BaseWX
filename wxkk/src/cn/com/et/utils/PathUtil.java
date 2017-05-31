package cn.com.et.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 路径处理类
 * @author Administrator
 *
 */
public class PathUtil {
	 private static Log logger  = LogFactory.getLog(PathUtil.class);

	/**
	 * 获取项目路径
	 * @param request
	 * @return  例如http://localhost:8080/et/
	 */
	public static String getProjectUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath(); 
		return request.getScheme()+"://"+request.getServerName()+":" + request.getServerPort()+contextPath+"/";
	}
	
	/**
	 * 获取请求的URL
	 * @param request
	 * @return
	 */
	public static String getReqUrl(HttpServletRequest request) {
		StringBuffer reqUrl = request.getRequestURL();
		if (request.getQueryString() != null) {
			reqUrl.append("?");
			reqUrl.append(request.getQueryString());
		}
		return reqUrl.toString();
	}
	
	public static enum PATH_TYPE {
        ROOT,WEBINF,CLASSES
    }
	
	/**
	 * 获取工程的WEB-INF或ROOT目录
	 * @param dirname
	 * @return
	 */
	public static String getEnvPath(String dirFlag){
		String fileSep = System.getProperty("file.separator");
		String windowsFlag = "Windows";
		
		String osName = System.getProperty("os.name");
		String path=null;
		if(PATH_TYPE.WEBINF.name().equals(dirFlag)){
			path=Thread.currentThread().getContextClassLoader().getResource("").getPath().concat("../").trim();
			logger.info("getEnvPath WEB-INF dirname::"+path);
		}if(PATH_TYPE.ROOT.name().equals(dirFlag)){
			path=Thread.currentThread().getContextClassLoader().getResource("").getPath().concat("../../").trim();
			logger.info("getEnvPath ROOT dirname::"+path);
		}if(PATH_TYPE.CLASSES.name().equals(dirFlag)){
			path=Thread.currentThread().getContextClassLoader().getResource("").getPath().trim();
			logger.info("getEnvPath CLASSES dirname::"+path);
		}
		if((path.startsWith(fileSep) || path.startsWith("/")) && osName.contains(windowsFlag)){
			path=path.substring(1);
		}
		try {
			//解决存在空格的问题
			path=URLDecoder.decode(path,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static void main(String[] args) {
		String p = getEnvPath(PATH_TYPE.WEBINF.name());
		System.err.println(p);
	}
}
