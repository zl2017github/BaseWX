package cn.com.et.entity.bike;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 车辆金额流水表
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class BikeProcessFlow implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 车辆id */
	private Integer bikeId;
	
	/** 用户id */
	private Integer customerId;
	
	/** 用途(1.每次消费 2.用户充值 3.抵扣金额流水 4.奖励金额流水 5.缴纳押金流水) */
	private Integer type;
	
	/** 金额 */
	private BigDecimal price;
	
	/** 备注 */
	private String note;
	
	/** 创建时间 */
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBikeId() {
		return bikeId;
	}

	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

}
