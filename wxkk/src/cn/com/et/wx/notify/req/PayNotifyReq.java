package cn.com.et.wx.notify.req;

public class PayNotifyReq {
	// 众账号ID appid 是
	private String appid="";
	// 商户号 mch_id 是
	private String mch_id="";
	// 设备号 device_info 否
	private String device_info="";
	// 随机字符串 nonce_str 是
	private String nonce_str="";
	// 签名 sign 是
	private String sign="";
	// 业务结果 result_code 是
	private String result_code="";
	// 错误代码 err_code 否
	private String err_code="";
	// 错误代码描述 err_code_des 否
	private String err_code_des="";
	// 用户标识 openid 是
	private String openid="";
	// 是否关注公众账号 is_subscribe 是
	private String is_subscribe="";
	// 交易类型 trade_type 是
	private String trade_type="";
	// 付款银行 bank_type 是
	private String bank_type="";
	// 总金额 total_fee 是
	private Integer total_fee=0;
	// 货币种类 fee_type 否
	private String fee_type="";
	// 现金支付金额 cash_fee 是
	private Integer cash_fee=0;
	// 现金支付货币类型 cash_fee_type 否
	private String cash_fee_type=""; 
	// 代金券或立减优惠金额 coupon_fee 否
	private Integer coupon_fee=0;
	// 代金券或立减优惠使用数量 coupon_count 否
	private Integer coupon_count=0;
	// 代金券或立减优惠ID coupon_id_$n 否
	private String coupon_id_$n="";
	// coupon_fee_$n 否
	private Integer coupon_fee_$n=0;
	// 微信支付订单号 transaction_id 是
	private String transaction_id="";
	// 商户订单号 out_trade_no 是
	private String out_trade_no="";
	// 商家数据包 attach 否
	private String attach="";
	// 支付完成时间 time_end 是
	private String time_end="";
	//返回状态码
	private String return_code="";
	//返回信息
	private String return_msg="";
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mchId) {
		mch_id = mchId;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String resultCode) {
		result_code = resultCode;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String errCode) {
		err_code = errCode;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String errCodeDes) {
		err_code_des = errCodeDes;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String isSubscribe) {
		is_subscribe = isSubscribe;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String tradeType) {
		trade_type = tradeType;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bankType) {
		bank_type = bankType;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer totalFee) {
		total_fee = totalFee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String feeType) {
		fee_type = feeType;
	}
	public Integer getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(Integer cashFee) {
		cash_fee = cashFee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cashFeeType) {
		cash_fee_type = cashFeeType;
	}
	public Integer getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(Integer couponFee) {
		coupon_fee = couponFee;
	}
	public Integer getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(Integer couponCount) {
		coupon_count = couponCount;
	}
	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}
	public void setCoupon_id_$n(String couponId_$n) {
		coupon_id_$n = couponId_$n;
	}
	public Integer getCoupon_fee_$n() {
		return coupon_fee_$n;
	}
	public void setCoupon_fee_$n(Integer couponFee_$n) {
		coupon_fee_$n = couponFee_$n;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String timeEnd) {
		time_end = timeEnd;
	}
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
