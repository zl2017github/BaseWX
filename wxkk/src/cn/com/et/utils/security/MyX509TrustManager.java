package cn.com.et.utils.security;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;




/**
 * X509信任管理
 * @author Administrator
 *
 */
public class MyX509TrustManager extends X509ExtendedTrustManager {

	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType,
			Socket socket) throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType,
			SSLEngine engine) throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType,
			Socket socket) throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType,
			SSLEngine engine) throws CertificateException {
		// TODO Auto-generated method stub
		
	}
}
