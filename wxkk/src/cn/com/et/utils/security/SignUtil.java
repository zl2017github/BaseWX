package cn.com.et.utils.security;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.com.et.utils.ByteUtil;

/** 
 * 请求校验工具类 
 *  
 * @author onetime
 * 
 */ 
public class SignUtil {
	private static final String SHA1 = "SHA-1";
	private static final String UTF8 = "UTF-8";
	
    /**
     * 对字符串签名
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String getSignatureBySha1(String skey){
    	String signature="";
    	MessageDigest crypt;
		try {
			crypt = MessageDigest.getInstance(SHA1);
			crypt.reset();
			crypt.update(skey.getBytes(UTF8));
			signature = ByteUtil.byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return signature;
    }
    
    /**
     * 对字符串按指定算法进行签名
     * @param sKey
     * @param algorithm
     * @return
     */
    public static String getSignatureForStr(String sKey,String algorithm) {
        String ciphertext = "";
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] digest = md.digest(sKey.toString().getBytes());
			ciphertext = ByteUtil.byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return ciphertext.toLowerCase();
    }
    
    /**
     * 对字符串按指定算法进行签名
     * @param sKey
     * @param algorithm
     * @return
     */
    public static byte[] getSignatureForBytes(String sKey,String algorithm) {
    	byte[] digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			digest = md.digest(sKey.toString().getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return digest;
    }
}
