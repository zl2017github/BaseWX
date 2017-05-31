package cn.com.et.entity.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 用户抵扣
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class CustomerDeduction  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 用户id */
	private Integer customerId;
	
	/** 消费总额 */
	private BigDecimal consumptionSum;
	
	/** 已抵扣总额 */
	private BigDecimal deductionSum;
	
	/** 可抵扣总额 */
	private BigDecimal notDeductionSum;
	
	/** 备注 */
	private String note;
	
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

	public BigDecimal getConsumptionSum() {
		return consumptionSum;
	}

	public void setConsumptionSum(BigDecimal consumptionSum) {
		this.consumptionSum = consumptionSum;
	}

	public BigDecimal getDeductionSum() {
		return deductionSum;
	}

	public void setDeductionSum(BigDecimal deductionSum) {
		this.deductionSum = deductionSum;
	}

	public BigDecimal getNotDeductionSum() {
		return notDeductionSum;
	}

	public void setNotDeductionSum(BigDecimal notDeductionSum) {
		this.notDeductionSum = notDeductionSum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
