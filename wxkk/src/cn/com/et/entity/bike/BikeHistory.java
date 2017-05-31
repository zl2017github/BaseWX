package cn.com.et.entity.bike;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 用车历史记录
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class BikeHistory implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 用户id*/
	private Integer customerId;
	
	/** 车辆id */
	private Integer bikeId;
	
	/** 开始坐标 */
	private String beginPoint;
	
	/** 开始位置 */
	private String beginLocation;
	
	/** 开始时间*/
	private Date beginTime;
	
	/** 结束坐标 */
	private String endPoint;
	
	/** 结束位置 */
	private String endLocation;
	
	/** 结束时间*/
	private Date endTime;
	
	/** 流水图片 */
	private String photo;
	
	/** 车牌号(冗余字段) */
	private String bikeNum;
	
	/** 消费金额 */
	private BigDecimal price;
	
	/** 是否已经抵扣(0. 已抵扣 1. 未抵扣) */
	private Integer isDeduction;
	
	/** 是否有效(0. 无效 1.有效) */
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

	public Integer getBikeId() {
		return bikeId;
	}

	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}

	public String getBeginPoint() {
		return beginPoint;
	}

	public void setBeginPoint(String beginPoint) {
		this.beginPoint = beginPoint;
	}

	public String getBeginLocation() {
		return beginLocation;
	}

	public void setBeginLocation(String beginLocation) {
		this.beginLocation = beginLocation;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBikeNum() {
		return bikeNum;
	}

	public void setBikeNum(String bikeNum) {
		this.bikeNum = bikeNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getIsDeduction() {
		return isDeduction;
	}

	public void setIsDeduction(Integer isDeduction) {
		this.isDeduction = isDeduction;
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

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
}
