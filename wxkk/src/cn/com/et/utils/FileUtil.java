package cn.com.et.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	private static String DEF_ENCODING = "UTF-8";
	
	/**
	 * 保存对象到文件
	 * @param obj
	 * @param filePath
	 * @return
	 */
	public static boolean saveObject(Object obj,String filePath){
		boolean flag = false;
		// 对象的序列化流
		ObjectOutputStream out = null;
		try {
			logger.info("save Object in "+filePath);
			out = new ObjectOutputStream(new FileOutputStream(filePath));
			out.writeObject(obj);
			out.flush();
			out.close();
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out==null){
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	/**
	 * 读取文件为对象
	 * @param filePath
	 * @return
	 */
	public static Object readObject(String filePath){
		logger.info("read Object form" + filePath);
		Object _obj = null;
		FileInputStream in = null;
		ObjectInputStream objIn = null;
		
		File file =new File(filePath);
		if(!file.exists()){
			return null;
		}
		try {
			in = new FileInputStream(file);
			objIn = new ObjectInputStream(in);
			_obj = objIn.readObject();
			objIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(objIn != null){
				try {
					objIn.close();
					objIn=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return _obj;
	}
	
	/**
	 * 读取文件内容
	 * @param filePath
	 * @param encoding
	 * @return
	 */
	public static String readFile(String filePath,String encoding) {
		if(null==encoding)encoding=DEF_ENCODING;
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		StringBuilder sb = new StringBuilder();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				bufferedReader = new BufferedReader(read);
				String context = null;
				while ((context = bufferedReader.readLine()) != null) {
					sb.append(context);
				}
				bufferedReader.close();
				read.close();
			} else {
				logger.debug("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			logger.debug("读取文件内容出错:"+e.getMessage());
		}finally{
			if(null!=bufferedReader){
				try {
					bufferedReader.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(null!=read){
				try {
					read.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 读取properties文件
	 * 
	 * @return
	 */
	public static Properties readProperties(String filePath) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
				}
			}
		}
		return props;
	}
}
