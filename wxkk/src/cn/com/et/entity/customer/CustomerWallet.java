package cn.com.et.entity.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 用户钱包
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class CustomerWallet  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 用户id */
	private Integer customerId;
	
	/** 剩余金额 */
	private BigDecimal remainingPrice;
	
	/** 押金 */
	private BigDecimal depositPrice;
	
	/** 来源(1.微信 2.支付宝) */
	private Integer source;
	
	/** 来源账户 */
	private String sourceAccount;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getRemainingPrice() {
		return remainingPrice;
	}

	public void setRemainingPrice(BigDecimal remainingPrice) {
		this.remainingPrice = remainingPrice;
	}

	public BigDecimal getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(BigDecimal depositPrice) {
		this.depositPrice = depositPrice;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
