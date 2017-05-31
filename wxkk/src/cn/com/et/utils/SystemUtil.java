package cn.com.et.utils;

public class SystemUtil {
	private static final String W="Windows"; 
	
	/**
	 * 是否是windows平台
	 * @return
	 */
	public static boolean ifWindows(){
		String osName = System.getProperty("os.name");
		if(osName.contains(W)){
			return true;
		}else{
			return false;
		}
	}
}
