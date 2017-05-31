package cn.com.et.entity.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 用户押金退还申请
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class CustomerDeposit  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 用户id */
	private Integer customerId;
	
	/** 钱包id */
	private Integer walletId;
	
	/** 押金 */
	private BigDecimal depositPrice;
	
	/** 1.申请退还押金  2.已退还押金 */
	private Integer type;
	
	/** 申请退款时间 */
	private Date applyTime;
	
	/** 退还押金时间 */
	private Date refundTime;
	
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

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public BigDecimal getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(BigDecimal depositPrice) {
		this.depositPrice = depositPrice;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
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
