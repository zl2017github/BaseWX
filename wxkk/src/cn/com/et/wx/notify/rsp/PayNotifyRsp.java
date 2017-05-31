package cn.com.et.wx.notify.rsp;

public class PayNotifyRsp {
	/*转换成XML格式
	<xml>
	   <return_code><![CDATA[SUCCESS]]></return_code>
	   <return_msg><![CDATA[OK]]></return_msg>
	</xml>
	*/
	
	//返回状态码
	private String return_code;
	//返回信息
	private String return_msg;
	
	
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String returnCode) {
		return_code = returnCode;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String returnMsg) {
		return_msg = returnMsg;
	}
}
