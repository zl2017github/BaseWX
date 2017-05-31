package cn.com.et.commons;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.et.utils.FileUtil;
import cn.com.et.utils.PathUtil;
/**
 * 对象持久化操作
 * @author onetime
 *
 */
public class IdentUtil {
	private static Logger logger = LoggerFactory.getLogger(IdentUtil.class);
	//对象保存根目录
	public static final String USER_DIR = PathUtil.getEnvPath(PathUtil.PATH_TYPE.CLASSES.name());//正式环境
	//凭据类型
	public static enum TYPE {
        accessToken,jsapiTicket;
    }
	//凭据类对象保存的位置与名称
	private static final String CREDENTIALS_HOME = new StringBuilder(USER_DIR).append("datas/").toString();
	private static final String ACCESS_TOKEN_HOME = new StringBuilder(CREDENTIALS_HOME).append(TYPE.accessToken.name()).toString();
	private static final String ACCESS_JSAPITICKET_HOME = new StringBuilder(CREDENTIALS_HOME).append(TYPE.jsapiTicket.name()).toString();
	
	/**
	 * 保存凭据对象到文件
	 * @param obj
	 * @param type
	 * @return
	 */
	public static boolean saveIdent(Object obj,TYPE type){
		boolean flag = false;
		if(type.equals(TYPE.accessToken)){
			logger.info("saveIdent ACCESS_TOKEN_HOME="+ACCESS_TOKEN_HOME);
			flag = FileUtil.saveObject(obj, ACCESS_TOKEN_HOME);
		}else if(type.equals(TYPE.jsapiTicket)){
			logger.info("saveIdent ACCESS_JSAPITICKET_HOME="+ACCESS_JSAPITICKET_HOME);
			flag = FileUtil.saveObject(obj, ACCESS_JSAPITICKET_HOME);
		}else{
			logger.info("This identity type is not supported! saveIdent["+type.name()+"]");
		}
		return flag;
	}
	
	/**
	 * 获取凭据对象到文件
	 * @param type
	 * @return
	 * @throws IOException
	 */
	public static Object getIdent(TYPE type){
		Object obj = null;
		if(type.equals(TYPE.accessToken)){
			logger.info("getIdent ACCESS_TOKEN_HOME="+ACCESS_TOKEN_HOME);
			obj = FileUtil.readObject(ACCESS_TOKEN_HOME);
		}else if(type.equals(TYPE.jsapiTicket)){
			logger.info("getIdent ACCESS_JSAPITICKET_HOME="+ACCESS_JSAPITICKET_HOME);
			obj = FileUtil.readObject(ACCESS_JSAPITICKET_HOME);
		}else{
			logger.info("This identity type is not supported! getIdent["+type.name()+"]");
		}
		
		return obj;
	}
}
