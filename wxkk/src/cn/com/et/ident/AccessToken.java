package cn.com.et.ident;

import java.io.Serializable;

public class AccessToken extends IdentBase implements Serializable{
	private static final long serialVersionUID = 6129663829957433448L;
	private String token;
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
