package cn.com.et.ident;

import java.io.Serializable;

public class JsapiTicket extends IdentBase implements Serializable{
	private static final long serialVersionUID = -324418085327129617L;
	private long errcode;
	private String errmsg;
	private String ticket;
	
	public long getErrcode() {
		return errcode;
	}
	public void setErrcode(long errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
}
