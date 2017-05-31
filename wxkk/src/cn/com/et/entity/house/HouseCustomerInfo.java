package cn.com.et.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 用户房屋
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class HouseCustomerInfo  implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 房屋id*/
	private Integer houseId;
	
	/** 用户id */
	private Integer customerId;
	
	/** 租户手机号*/
	private String customerMobile;
	
	/**　租户名字*/
	private String customerName;
	
	/**　租户上班地*/
	private String customerWorkerAddress;
	
	/** 交房租日期 */
	private String rentDate;
	
	/** 下次交房租日期*/
	private String nextRentDate;
	
	/** 实际房租*/
	private BigDecimal actualPrice;
	
	/** 备注*/
	private String customerNote;
	
	/** 是否注册KAKE(0,否 1.是)*/
	private Integer isRegister;
	
	/** 是否有效（0.否 1.是）*/
	private Integer isValid;
	
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

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRentDate() {
		return rentDate;
	}

	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

	public String getNextRentDate() {
		return nextRentDate;
	}

	public void setNextRentDate(String nextRentDate) {
		this.nextRentDate = nextRentDate;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Integer getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(Integer isRegister) {
		this.isRegister = isRegister;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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

	public String getCustomerNote() {
		return customerNote;
	}

	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}

	public String getCustomerWorkerAddress() {
		return customerWorkerAddress;
	}

	public void setCustomerWorkerAddress(String customerWorkerAddress) {
		this.customerWorkerAddress = customerWorkerAddress;
	}

}
