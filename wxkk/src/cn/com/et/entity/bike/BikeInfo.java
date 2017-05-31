package cn.com.et.entity.bike;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 车辆表
 *
 * @author 郝凯枫
 *
 * @version 
 *
 * @since 2017-3-10
 */
@SuppressWarnings("serial")
public class BikeInfo implements Serializable{

	/** 主键 */
	private Integer id;
	
	/** 车牌号 */
	private String bikeNum;
	
	/** 车密码 */
	private Integer bikePwd;
	
	/** 备注 */
	private String note;
	
	/** 是否有效（0. 无效 1. 有效） */
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

	public String getBikeNum() {
		return bikeNum;
	}

	public void setBikeNum(String bikeNum) {
		this.bikeNum = bikeNum;
	}

	public Integer getBikePwd() {
		return bikePwd;
	}

	public void setBikePwd(Integer bikePwd) {
		this.bikePwd = bikePwd;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
