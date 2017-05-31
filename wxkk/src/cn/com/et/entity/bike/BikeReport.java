package cn.com.et.entity.bike;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 车辆举报信息
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class BikeReport implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 用户id */
	private Integer bikeId;
	
	/** 用户id */
	private Integer customerId;
	
	/** 类型(1.私锁私用 2.车辆损坏 3.违规乱停 4.其他) */
	private String type;
	
	/** 地址 */
	private String location;
	
	/** 图片地址 */
	private String photo;
	
	/** 车牌号 */
	private String bikeNum;
	
	/** 备注 */
	private String note;
	
	/** 举报是否有效(0.无效 1.有效) 有效奖励用户钱  */
	private Integer isValid;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date update_time;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getBikeId() {
		return bikeId;
	}

	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}

}
